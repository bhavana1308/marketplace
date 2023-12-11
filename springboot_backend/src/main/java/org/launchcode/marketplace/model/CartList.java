package org.launchcode.marketplace.model;

public class CartList {

    private int productId;
    private int cartId;
    private int quantity;
    private Product product;
    private int orderId;

    private double totalProductPrice;

    private PurchaseOrder purchaseOrder;

    public CartList(int productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public CartList() {

    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getTotalProductPrice() {
        return totalProductPrice;
    }

    public void setTotalProductPrice(double totalProductPrice) {
        this.totalProductPrice = totalProductPrice;
    }

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    @Override
    public String toString() {
        return "CartList{" +
                "cartId=" + cartId +
                ", quantity=" + quantity +
                '}';
    }
}
