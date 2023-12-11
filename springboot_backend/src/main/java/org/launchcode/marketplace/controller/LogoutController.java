package org.launchcode.marketplace.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LogoutController {

    @GetMapping("/logout")
    public String logoutPage() {
        return "logout";
    }

    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
    @ResponseBody
    @PostMapping("/api/logout")
    public ResponseEntity<String> logout(HttpSession session) {

        session.invalidate();
        return ResponseEntity.ok("Log out successfull");
    }


}
