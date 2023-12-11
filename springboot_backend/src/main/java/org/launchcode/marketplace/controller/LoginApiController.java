package org.launchcode.marketplace.controller;

import jakarta.servlet.http.HttpSession;
import org.launchcode.marketplace.mybatis.BuyerMapper;
import org.launchcode.marketplace.mybatis.ProductsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/login")
@CrossOrigin(origins = "http://localhost:3000")
public class LoginApiController {

    @Autowired
    private final BuyerMapper buyerMapper;

    @Autowired
    public LoginApiController(BuyerMapper buyerMapper, ProductsMapper productsMapper) {
        this.buyerMapper = buyerMapper;
    }

    @PostMapping
    public Map<String, Object> performLogin(@RequestParam String email, @RequestParam String password, HttpSession session) {
        Map<String, Object> response = new HashMap<>();

        Integer buyerId = buyerMapper.getBuyerByEmailAndPassword(email, password);

        if (buyerId != null && buyerId.intValue() > 0) {
            session.setAttribute("buyerId", buyerId);
            response.put("success", true);
            response.put("buyerId", buyerId);

        } else {
            response.put("success", false);
            response.put("message", "Invalid credentials");
        }

        return response;
    }
}

