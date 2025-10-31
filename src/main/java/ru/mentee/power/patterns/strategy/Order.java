package ru.mentee.power.patterns.strategy;

public class Order {
    private final double totalWeight;
    private final String destinationRegion;
    private final double totalAmount;
    public Order(double totalWeight, String destinationRegion, double totalAmount) {
        this.totalWeight = totalWeight;
        this.destinationRegion = destinationRegion;
        this.totalAmount = totalAmount;
    }

    public double getTotalWeight() { return totalWeight; }
    public String getDestinationRegion() { return destinationRegion; }
    public double getTotalAmount() { return totalAmount; }

    @Override
    public String toString() {
        return "Order{weight=" + totalWeight + ", region='" + destinationRegion + '\'' + ", amount=" + totalAmount + '}';
    }

}
