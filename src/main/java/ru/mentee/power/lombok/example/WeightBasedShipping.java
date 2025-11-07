package ru.mentee.power.lombok.example;

public class WeightBasedShipping implements ShippingStrategy {
    private final double ratePerKg;

    public WeightBasedShipping(double ratePerKg) {
        this.ratePerKg = ratePerKg;
    }

    @Override
    public double calculate(Order_Lombok orderLombok) {
        return orderLombok.getTotalWeight() * ratePerKg;
    }
}
