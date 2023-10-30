package org.launchcode.marketplace.controller;

import org.launchcode.marketplace.model.CartList;
import org.launchcode.marketplace.model.Product;
import org.launchcode.marketplace.mybatis.CartListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CartListController {

    @Autowired
    private final CartListMapper cartListMapper;

    public CartListController(CartListMapper cartListMapper) {
        this.cartListMapper = cartListMapper;
    }

    @GetMapping("/cart")
    public String viewCart(Model model) {
        List<CartList> cartItems = cartListMapper.getCartItemsWithProducts();
        double totalPrice = calculateTotalPrice(cartItems);
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalPrice", totalPrice);
        return "cartlist";
    }

    @PostMapping("/cart/add")
    public String addToCart(@RequestParam("productId") int productId, @RequestParam("quantity") int quantity) {
        CartList cartItem = new CartList(productId, quantity);
        cartListMapper.insertCartList(cartItem);
        return "redirect:/cart";
    }

    @PostMapping("/cart/delete")
    public String deleteCartItem(@RequestParam("cartItemId") int cartItemId) {
        cartListMapper.deleteByCartListId(cartItemId);
        return "redirect:/cart";
    }

    private double calculateTotalPrice(List<CartList> cartItems) {
        double totalPrice = 0.0;
        for (CartList cartItem : cartItems) {
            Product product = cartItem.getProduct();
            totalPrice += cartItem.getProduct().getPrice() * cartItem.getQuantity();
        }
        return totalPrice;
    }

    @PostMapping("/cart/checkout")
    public String checkout(Model model) {
        model.addAttribute("message", "message");
        return "checkout";
    }


}
