package org.launchcode.marketplace.controller;


import jakarta.servlet.http.HttpSession;
import org.launchcode.marketplace.model.Buyer;
import org.launchcode.marketplace.model.CartList;
import org.launchcode.marketplace.model.PurchaseOrder;
import org.launchcode.marketplace.mybatis.BuyerMapper;
import org.launchcode.marketplace.mybatis.CartListMapper;
import org.launchcode.marketplace.mybatis.PurchaseOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CheckoutController {

    @Autowired
    private final BuyerMapper buyerMapper;

    @Autowired
    private final PurchaseOrderMapper purchaseOrderMapper;

    @Autowired
    private final CartListMapper cartListMapper;

    @Autowired
    public CheckoutController(
            BuyerMapper buyerMapper,
            PurchaseOrderMapper purchaseOrderMapper,
            CartListMapper cartListMapper) {
        this.buyerMapper = buyerMapper;
        this.purchaseOrderMapper = purchaseOrderMapper;
        this.cartListMapper = cartListMapper;
    }

    @GetMapping("/checkout/form")
    public String checkoutForm(Model model, HttpSession session) {

        Integer buyerId = (Integer) session.getAttribute("buyerId");

        if (buyerId != null) {

            List<CartList> cartItems = cartListMapper.getCartItemsWithProducts();
            Buyer buyer = buyerMapper.getBuyerById(buyerId);

            double purchaseAmount = (double) Math.round(calculatePurchaseAmount(cartItems) * 100) / 100;
            int loyaltyPointsEarned = calculateLoyaltyPoints(purchaseAmount);

            model.addAttribute("purchaseAmount", purchaseAmount);
            model.addAttribute("loyaltyPointsEarned", loyaltyPointsEarned);
            model.addAttribute("buyerName", buyer.getFirstName());
            model.addAttribute("loyaltyPoints", buyer.getLoyaltyPoints());

            return "checkout-form";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/checkout/process")
    public String processCheckout(Model model, HttpSession session) {

        Integer buyerId = (Integer) session.getAttribute("buyerId");

        if (buyerId != null) {

            List<CartList> cartItems = cartListMapper.getCartItemsWithProducts();
            Buyer buyer = buyerMapper.getBuyerById(buyerId);

            double purchaseAmount = (double) Math.round(calculatePurchaseAmount(cartItems) * 100) / 100;
            int loyaltyPointsEarned = calculateLoyaltyPoints(purchaseAmount);

            buyerMapper.updateBuyerLoyaltyPoints(buyerId, loyaltyPointsEarned);

            PurchaseOrder purchaseOrder = createPurchaseOrder(buyerId);

            purchaseOrderMapper.insertPurchaseOrder(purchaseOrder);
            model.addAttribute("orderNumber", purchaseOrder.getOrderNumber());

            for (CartList cartItem : cartItems) {
                cartListMapper.deleteByCartListId(cartItem.getCartId());
            }

            model.addAttribute("buyerName", buyer.getFirstName());
            model.addAttribute("loyaltyPoints", loyaltyPointsEarned + buyer.getLoyaltyPoints());

            return "purchase-orders/result";
        } else {
            return "redirect:/login";
        }
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
        Buyer buyer = buyerMapper.getBuyerById(buyerId);
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setBuyer(buyer);
        purchaseOrder.generateOrderNumber();
        return purchaseOrder;
    }
}

