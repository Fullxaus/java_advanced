package ru.mentee.power.streams;

public class Order {
    private String orderId;
    private String customerName;
    private String productName;
    private int quantity;
    private double pricePerUnit;
    private OrderStatus status;

    public Order(String orderId, String customerName, String productName, int quantity, double pricePerUnit, OrderStatus status) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.productName = productName;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public double getTotalPrice() {
        return quantity * pricePerUnit;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", pricePerUnit=" + pricePerUnit +
                ", status=" + status +
                ", totalPrice=" + getTotalPrice() +
                '}';
    }
}
