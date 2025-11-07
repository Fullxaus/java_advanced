package ru.mentee.power.lombok.example;

public class ShippingContext {

    private ShippingStrategy strategy;

    public void setStrategy(ShippingStrategy strategy) {
        this.strategy = strategy;
    }

    public double calculateShippingCost(Order_Lombok orderLombok) {
        if (strategy == null) {
            return 0.0;
        }
        return strategy.calculate(orderLombok);
    }

}
