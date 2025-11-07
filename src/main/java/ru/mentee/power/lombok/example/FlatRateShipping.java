package ru.mentee.power.lombok.example;

public class FlatRateShipping implements ShippingStrategy {
    private final double flatRate;
    public FlatRateShipping(double flatRate) {
        this.flatRate = flatRate;
    }

    @Override
    public double calculate(Order_Lombok orderLombok) {
        return flatRate;
    }
}
