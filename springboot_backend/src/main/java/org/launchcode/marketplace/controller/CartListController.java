package org.launchcode.marketplace.controller;

import jakarta.servlet.http.HttpSession;
import org.launchcode.marketplace.model.Buyer;
import org.launchcode.marketplace.model.CartList;
import org.launchcode.marketplace.model.Product;
import org.launchcode.marketplace.mybatis.BuyerMapper;
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

    @Autowired
    private final BuyerMapper buyerMapper;

    @Autowired
    public CartListController(CartListMapper cartListMapper, BuyerMapper buyerMapper) {
        this.cartListMapper = cartListMapper;
        this.buyerMapper = buyerMapper;
    }

//    @GetMapping("/cart")
//    public String viewCart(Model model) {
//        List<CartList> cartItems = cartListMapper.getCartItemsWithProducts();
//        double totalPrice = calculateTotalPrice(cartItems);
//        model.addAttribute("cartItems", cartItems);
//        model.addAttribute("totalPrice", totalPrice);
//        return "cartlist";
//    }

    @GetMapping("/cart")
    public String viewCart(Model model, HttpSession session) {
        Integer buyerId = (Integer) session.getAttribute("buyerId");

        if (buyerId != null) {

            List<CartList> cartItems = cartListMapper.getCartItemsWithProducts();
            Buyer buyer = buyerMapper.getBuyerById(buyerId);

            double totalPrice = (double) Math.round(calculateTotalPrice(cartItems) * 100) / 100;

            model.addAttribute("buyerName", buyer.getFirstName());
            model.addAttribute("loyaltyPoints", buyer.getLoyaltyPoints());
            model.addAttribute("cartItems", cartItems);
            model.addAttribute("totalPrice", totalPrice);

            return "cartlist";
        } else {
            return "redirect:/login";
        }
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


}