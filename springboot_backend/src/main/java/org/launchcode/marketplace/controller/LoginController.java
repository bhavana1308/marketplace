package org.launchcode.marketplace.controller;

import jakarta.servlet.http.HttpSession;
import org.launchcode.marketplace.mybatis.BuyerMapper;
import org.launchcode.marketplace.mybatis.ProductsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
@CrossOrigin
public class LoginController {

    @Autowired
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
    public String performLogin(@RequestParam String email, @RequestParam String password, HttpSession session) {

        Integer buyerId  = buyerMapper.getBuyerByEmailAndPassword(email, password);

        if ( buyerId!= null && buyerId.intValue() > 0 ) {
            session.setAttribute("buyerId", buyerId);
            return "redirect:/productList";
        } else {
            return "redirect:/login/loginError";
        }
    }


}
