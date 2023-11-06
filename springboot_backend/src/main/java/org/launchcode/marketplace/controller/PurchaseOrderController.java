package org.launchcode.marketplace.controller;


import org.launchcode.marketplace.model.PurchaseOrder;
import org.launchcode.marketplace.mybatis.BuyerMapper;
import org.launchcode.marketplace.mybatis.CartListMapper;
import org.launchcode.marketplace.mybatis.PurchaseOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/purchase-orders")
public class PurchaseOrderController {

    @Autowired
    private final PurchaseOrderMapper purchaseOrderMapper;
    @Autowired
    private final BuyerMapper buyerMapper;
    @Autowired
    private final CartListMapper cartListMapper;

    @Autowired
    public PurchaseOrderController(
            PurchaseOrderMapper purchaseOrderMapper,
            BuyerMapper buyerMapper,
            CartListMapper cartListMapper) {
        this.purchaseOrderMapper = purchaseOrderMapper;
        this.buyerMapper = buyerMapper;
        this.cartListMapper = cartListMapper;
    }

    @GetMapping("/list")
    public String listPurchaseOrders(Model model) {
        List<PurchaseOrder> purchaseOrders = purchaseOrderMapper.getAllPurchaseOrders();
        model.addAttribute("purchaseOrders", purchaseOrders);
        //System.out.println("Purchase Orders: " + purchaseOrders);
        return "purchase-orders/list";
    }

}


