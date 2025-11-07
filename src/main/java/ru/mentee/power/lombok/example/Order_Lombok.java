package ru.mentee.power.lombok.example;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@AllArgsConstructor
@Builder
public class Order_Lombok {
    private double totalAmount;
    private String orderId;
    private String customerName;
    private String productName;
    private int quantity;
    private double pricePerUnit;
    private OrderStatus status;
    private String destinationRegion;
    private double totalWeight;



    public double getTotalPrice() {
        return quantity * pricePerUnit;
    }

    public Order_Lombok(double totalWeight, String destinationRegion, double totalAmount) {
        this.totalWeight = totalWeight;
        this.destinationRegion = destinationRegion;
        this.totalAmount = totalAmount;
    }

    public Order_Lombok(String orderId, String customerName, String productName, int quantity, double pricePerUnit, OrderStatus status) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.productName = productName;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
        this.status = status;
    }
}