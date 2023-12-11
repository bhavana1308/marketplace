package org.launchcode.marketplace.model;

public class BuyerInfoResponse {
    private String buyerName;
    private int loyaltyPoints;


    public BuyerInfoResponse(String buyerName, int loyaltyPoints) {
        this.buyerName = buyerName;
        this.loyaltyPoints = loyaltyPoints;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }
}
