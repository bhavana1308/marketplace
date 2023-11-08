package org.launchcode.marketplace.model;


import java.util.Date;

public class PurchaseOrder {

    private int orderId;

    private Buyer buyer;

    private String orderNumber;


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



