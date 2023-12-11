package org.launchcode.marketplace.model;


import java.time.LocalDate;
import java.util.Date;


public class PurchaseOrder {

    private int orderId;

    private Buyer buyer;

    private String orderNumber;

    private LocalDate orderDate;


    public PurchaseOrder() {
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public void generateOrderNumber() {
        long timestamp = new Date().getTime();
        String orderNumber = String.valueOf(timestamp);
        this.setOrderNumber(orderNumber);

    }
}



