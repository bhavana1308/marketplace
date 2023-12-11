package org.launchcode.marketplace.controller;

import jakarta.servlet.http.HttpSession;
import org.launchcode.marketplace.model.Buyer;
import org.launchcode.marketplace.model.BuyerInfoResponse;
import org.launchcode.marketplace.mybatis.BuyerMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RequestMapping("/api/buyers")
public class BuyerApiController {

    private final BuyerMapper buyerMapper;

    public BuyerApiController(BuyerMapper buyerMapper) {
        this.buyerMapper = buyerMapper;
    }

    @GetMapping("/list")
    public List<Buyer> listBuyers() {
        return buyerMapper.getAllFromBuyer();
    }

    @GetMapping("/buyer-info")
    public BuyerInfoResponse getBuyerInfo(HttpSession session) {
        Integer buyerId = (Integer) session.getAttribute("buyerId");
        if (buyerId != null) {
            Buyer buyer = buyerMapper.getBuyerById(buyerId);

            if (buyer != null) {
                return new BuyerInfoResponse(
                        buyer.getFirstName(),
                        buyer.getLoyaltyPoints()
                );
            }
        }

        return null;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addBuyer(@RequestBody Buyer buyer) {
        buyerMapper.insertBuyer(buyer);
        return ResponseEntity.ok("Buyer added successfully");
    }

    @PostMapping("/edit/{buyerId}")
    public ResponseEntity<String> editBuyer(@PathVariable int buyerId, @RequestBody Buyer updatedBuyer) {
        updatedBuyer.setBuyerId(buyerId);
        int rowsUpdated = buyerMapper.updateBuyer(updatedBuyer);
        if (rowsUpdated > 0) {
            return ResponseEntity.ok("Buyer added successfully");
        } else {
            return ResponseEntity.ok("error");
        }
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


    @PostMapping("/delete")
    public ResponseEntity<String> deleteBuyer(@RequestParam("buyerId") int buyerId) {
        buyerMapper.deleteBuyer(buyerId);
        return ResponseEntity.ok("Buyer deleted successfully");

    }
}


