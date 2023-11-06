package org.launchcode.marketplace.controller;

import org.launchcode.marketplace.model.Buyer;
import org.launchcode.marketplace.mybatis.BuyerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/buyers")
public class BuyerController {

    @Autowired
    private final BuyerMapper buyerMapper;

    @Autowired
    public BuyerController(BuyerMapper buyerMapper) {
        this.buyerMapper = buyerMapper;
    }

    @GetMapping("/list")
    public String listBuyers(Model model) {
        List<Buyer> buyers = buyerMapper.getAllFromBuyer();
        model.addAttribute("buyers", buyers);
        return "buyer/list";
    }

    @GetMapping("/add")
    public String showAddBuyerForm(Model model) {
        model.addAttribute("buyer", new Buyer());
        return "buyer/add";
    }

    @GetMapping("/edit")
    public String showEditBuyerForm(Model model) {
        model.addAttribute("buyer", new Buyer());
        return "buyer/edit";
    }

    @PostMapping("/add")
    public String addBuyer(@ModelAttribute Buyer buyer) {
        buyerMapper.insertBuyer(buyer);
        return "redirect:/buyers/list";
    }


    @PostMapping("/edit")
    public String editBuyer(@RequestParam("buyerId") int buyerId, @ModelAttribute Buyer updatedBuyer) {
        updatedBuyer.setBuyerId(buyerId);
        int rowsUpdated = buyerMapper.updateBuyer(updatedBuyer);
        if (rowsUpdated > 0) {
            return "redirect:/buyers/list";
        } else {
            return "buyers/error";

        }
    }

    @PostMapping("/delete")
    public String deleteBuyer(@RequestParam("buyerId") int buyerId) {
        buyerMapper.deleteBuyer(buyerId);
        return "redirect:/buyers/list";
    }


}





