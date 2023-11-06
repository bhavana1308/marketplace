package org.launchcode.marketplace.model;


public class Buyer {

    private int buyerId;
    private String email;
    private String fName;
    private String lName;
    private String password;
    private int loyaltyPoints;


    public Buyer(int buyerId, String email, String fName, String lName, String password, int loyaltyPoints) {
        this.buyerId = buyerId;
        this.email = email;
        this.fName = fName;
        this.lName = lName;
        this.password = password;
        this.loyaltyPoints = loyaltyPoints;
    }

    public Buyer(int buyerId) {
        this.buyerId = buyerId;
    }

    public Buyer() {

    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return fName;
    }

    public void setFirstName(String fName) {
        this.fName = fName;
    }

    public String getLastName() {
        return lName;
    }

    public void setLastName(String lName) {
        this.lName = lName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    @Override
    public String toString() {
        return "Buyer{" +
                "buyerId=" + buyerId +
                ", email='" + email + '\'' +
                ", firstName='" + fName + '\'' +
                ", lastName='" + lName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
