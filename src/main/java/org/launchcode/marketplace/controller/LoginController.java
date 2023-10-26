package org.launchcode.marketplace.controller;

import org.launchcode.marketplace.model.Buyer;
import org.launchcode.marketplace.mybatis.BuyerMapper;
import org.launchcode.marketplace.mybatis.ProductsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final BuyerMapper buyerMapper;


    @Autowired
    public LoginController(BuyerMapper buyerMapper, ProductsMapper productsMapper) {
        this.buyerMapper = buyerMapper;

    }

    @GetMapping
    public String login() {
        return "login";
    }

    @GetMapping("/loginError")
    public String loginError() {
        return "loginError";
    }


    @PostMapping
    public String loginDetails(@RequestParam String email, @RequestParam String password) {
        Buyer buyer = buyerMapper.getBuyerByEmailAndPassword(email, password);
        if (buyer != null) {

            System.out.println("Authentication successful. Redirecting to productList.");
            return "redirect:/productList";
        } else {

            System.out.println("Authentication failed. Redirecting to loginerror.");
            return "redirect:/login/loginError";
        }
    }


}
