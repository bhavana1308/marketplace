package org.launchcode.marketplace.controller;

import jakarta.servlet.http.HttpSession;
import org.launchcode.marketplace.model.PurchaseOrder;
import org.launchcode.marketplace.mybatis.PurchaseOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RequestMapping("api/purchase-orders")
public class PurchaseOrderApiController {

    @Autowired
    private final PurchaseOrderMapper purchaseOrderMapper;

    public PurchaseOrderApiController(PurchaseOrderMapper purchaseOrderMapper) {
        this.purchaseOrderMapper = purchaseOrderMapper;
    }

    @GetMapping("/list")
    public List<PurchaseOrder> listPurchaseOrders(HttpSession session) {
        Integer buyerId = (Integer) session.getAttribute("buyerId");

        if (buyerId != null) {
            session.setAttribute("buyerId", buyerId);
            return purchaseOrderMapper.getPurchaseOrdersByBuyerId(buyerId);
        } else return null;
    }
}
