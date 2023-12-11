package org.launchcode.marketplace.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import org.launchcode.marketplace.model.Buyer;
import org.launchcode.marketplace.model.CartList;
import org.launchcode.marketplace.model.OrderDetail;
import org.launchcode.marketplace.model.PurchaseOrder;
import org.launchcode.marketplace.mybatis.BuyerMapper;
import org.launchcode.marketplace.mybatis.CartListMapper;
import org.launchcode.marketplace.mybatis.OrderDetailMapper;
import org.launchcode.marketplace.mybatis.PurchaseOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RequestMapping("/api")
public class CheckoutApiController {

    private final BuyerMapper buyerMapper;
    private final PurchaseOrderMapper purchaseOrderMapper;
    private final CartListMapper cartListMapper;
    private final OrderDetailMapper orderDetailMapper;

    @Autowired
    public CheckoutApiController(
            BuyerMapper buyerMapper,
            PurchaseOrderMapper purchaseOrderMapper,
            CartListMapper cartListMapper, OrderDetailMapper orderDetailMapper) {
        this.buyerMapper = buyerMapper;
        this.purchaseOrderMapper = purchaseOrderMapper;
        this.cartListMapper = cartListMapper;
        this.orderDetailMapper = orderDetailMapper;
    }

    @GetMapping("/form")
    public Map<String, Object> checkoutForm(HttpSession session) {
        Integer buyerId = (Integer) session.getAttribute("buyerId");

        if (buyerId != null) {
            List<CartList> cartItems = cartListMapper.getCartItemsWithProducts();
            Buyer buyer = buyerMapper.getBuyerById(buyerId);

            double purchaseAmount = (double) Math.round(calculatePurchaseAmount(cartItems) * 100) / 100;
            int loyaltyPointsEarned = calculateLoyaltyPoints(purchaseAmount);

            Map<String, Object> response = new HashMap<>();
            response.put("purchaseAmount", purchaseAmount);
            response.put("loyaltyPointsEarned", loyaltyPointsEarned);

            return response;
        }

        return null;
    }

    @PostMapping
    public Map<String, Object> processCheckout(HttpSession session) {
        Integer buyerId = (Integer) session.getAttribute("buyerId");

        if (buyerId != null) {
            List<CartList> cartItems = cartListMapper.getCartItemsWithProducts();

            if (!cartItems.isEmpty()) {
                Map<String, Object> response = processCheckoutLogic(buyerId, cartItems);
                return response;
            }
        }

        return null;
    }

    @GetMapping("/process")
    public Map<String, Object> processCheckout1(HttpSession session) {
        Integer buyerId = (Integer) session.getAttribute("buyerId");

        if (buyerId != null) {
            List<CartList> cartItems = cartListMapper.getCartItemsWithProducts();

            if (!cartItems.isEmpty()) {
                Map<String, Object> response = processCheckoutLogic(buyerId, cartItems);
                return response;
            }
        }

        return null;
    }

    private Map<String, Object> processCheckoutLogic(int buyerId, List<CartList> cartItems) {
        Buyer buyer = buyerMapper.getBuyerById(buyerId);

        double purchaseAmount = (double) Math.round(calculatePurchaseAmount(cartItems) * 100) / 100;
        int loyaltyPointsEarned = calculateLoyaltyPoints(purchaseAmount);

        buyerMapper.updateBuyerLoyaltyPoints(buyerId, loyaltyPointsEarned);

        PurchaseOrder purchaseOrder = createPurchaseOrder(buyerId);

        if (!cartItems.isEmpty()) {
            purchaseOrderMapper.insertPurchaseOrder(purchaseOrder);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("orderNumber", purchaseOrder.getOrderNumber());
        response.put("buyerName", buyer.getFirstName());
        response.put("loyaltyPoints", loyaltyPointsEarned + buyer.getLoyaltyPoints());

        // cartItems.forEach(cartItem -> cartListMapper.deleteByCartListId(cartItem.getCartId()));

        return response;
    }


    private int calculateLoyaltyPoints(double purchaseAmount) {
        return (int) (purchaseAmount * 2);
    }

    private double calculatePurchaseAmount(List<CartList> cartItems) {
        double purchaseAmount = 0.0;
        for (CartList cartItem : cartItems) {
            double subtotal = cartItem.getProduct().getPrice() * cartItem.getQuantity();
            purchaseAmount += subtotal;
        }
        return purchaseAmount;
    }

    private PurchaseOrder createPurchaseOrder(int buyerId) {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        int orderId = purchaseOrder.getOrderId();
        Buyer buyer = buyerMapper.getBuyerById(buyerId);
        purchaseOrder.setBuyer(buyer);
        purchaseOrder.setOrderId(orderId);
        purchaseOrder.setOrderDate(LocalDate.now());
        purchaseOrder.generateOrderNumber();
        return purchaseOrder;
    }


    @GetMapping("/orderDetails/{orderNumber}")
    public Map<String, Object> getOrderDetails(@PathVariable String orderNumber) {
        Map<String, Object> orderDetails = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();

        try {

            OrderDetail existingOrderDetail = orderDetailMapper.getOrderDetailByOrderNumber(orderNumber);

            if (existingOrderDetail == null) {

                PurchaseOrder purchaseOrder = purchaseOrderMapper.getOrderByOrderNumber(orderNumber);


                List<CartList> cartItems = cartListMapper.getCartItemsByOrderNumber(orderNumber);
                List<Map<String, Object>> orderItems = new ArrayList<>();
                double totalOrderPrice = 0.0;

                for (CartList cartItem : cartItems) {
                    double totalProductPrice = cartItem.getProduct().getPrice() * cartItem.getQuantity();
                    totalOrderPrice += totalProductPrice;

                    Map<String, Object> productInfo = new HashMap<>();
                    productInfo.put("productName", cartItem.getProduct().getProductName());
                    productInfo.put("price", cartItem.getProduct().getPrice());
                    productInfo.put("quantity", cartItem.getQuantity());
                    productInfo.put("totalProductPrice", totalProductPrice);
                    orderItems.add(productInfo);
                }


                String orderItemsJson = objectMapper.writeValueAsString(orderItems);


                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrderNumber(orderNumber);
                orderDetail.setOrderDate(purchaseOrder.getOrderDate());
                orderDetail.setOrderItems(orderItemsJson);
                orderDetail.setTotalOrderPrice(totalOrderPrice);
                orderDetailMapper.insertOrderDetail(orderDetail);


                for (CartList cartItem : cartItems) {
                    cartListMapper.deleteByCartListId(cartItem.getCartId());
                }

                orderDetails.put("orderNumber", orderNumber);
                orderDetails.put("orderDate", purchaseOrder.getOrderDate());
                orderDetails.put("orderItems", orderItems);
                orderDetails.put("totalOrderPrice", totalOrderPrice);
            } else {


                orderDetails.put("orderNumber", orderNumber);
                orderDetails.put("orderDate", existingOrderDetail.getOrderDate());
                orderDetails.put("orderItems", objectMapper.readValue(existingOrderDetail.getOrderItems(), List.class));
                orderDetails.put("totalOrderPrice", existingOrderDetail.getTotalOrderPrice());
            }

        } catch (Exception e) {
            e.printStackTrace();

        }

        return orderDetails;
    }
}