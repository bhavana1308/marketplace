package org.launchcode.marketplace.controller;

import jakarta.servlet.http.HttpSession;
import org.launchcode.marketplace.model.CartList;
import org.launchcode.marketplace.model.Product;
import org.launchcode.marketplace.mybatis.BuyerMapper;
import org.launchcode.marketplace.mybatis.CartListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class CartApiController {

    @Autowired
    private CartListMapper cartListMapper;

    @Autowired
    private BuyerMapper buyerMapper;


    @GetMapping("/cart/list")
    public List<Object> viewCart(HttpSession session) {
        Integer buyerId = (Integer) session.getAttribute("buyerId");

        List<Object> response = new ArrayList<>();

        if (buyerId != null) {
            List<CartList> cartItems = cartListMapper.getCartItemsWithProducts();


            for (CartList cartItem : cartItems) {
                double totalProductPrice = cartItem.getProduct().getPrice() * cartItem.getQuantity();
                cartItem.setTotalProductPrice(totalProductPrice);
                Map<String, Object> productInfo = new HashMap<>();
                productInfo.put("cartId", cartItem.getCartId());
                productInfo.put("productName", cartItem.getProduct().getProductName());
                productInfo.put("price", cartItem.getProduct().getPrice());
                productInfo.put("quantity", cartItem.getQuantity());
                productInfo.put("totalProductPrice", totalProductPrice);

                response.add(productInfo);
                System.out.println("Cart Items: " + productInfo);
            }


            double totalCartPrice = calculateTotalPrice(cartItems);
            response.add(totalCartPrice);

            System.out.println("Total Price: " + totalCartPrice);
        }

        return response;
    }

    @PostMapping("/cart/add")
    public ResponseEntity<String> addToCart(@RequestParam("productId") int productId, @RequestParam("quantity") int quantity) {
        CartList cartItem = new CartList(productId, quantity);

        cartListMapper.insertCartList(cartItem);

        return ResponseEntity.ok("Item added to the cart");
    }

    @PostMapping("/cart/delete")
    public ResponseEntity<String> deleteCartItem(@RequestParam("cartItemId") int cartItemId) {

        cartListMapper.deleteByCartListId(cartItemId);

        return ResponseEntity.ok("Cart item deleted successfully");

    }


    private double calculateTotalPrice(List<CartList> cartItems) {
        double totalPrice = 0.0;
        for (CartList cartItem : cartItems) {
            Product product = cartItem.getProduct();
            totalPrice += cartItem.getProduct().getPrice() * cartItem.getQuantity();
        }
        return totalPrice;
    }
}
