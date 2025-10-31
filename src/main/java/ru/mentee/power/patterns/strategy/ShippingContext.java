package ru.mentee.power.patterns.strategy;

public class ShippingContext {

    private ShippingStrategy strategy;

    public void setStrategy(ShippingStrategy strategy) {
        this.strategy = strategy;
    }

    public double calculateShippingCost(Order order) {
        if (strategy == null) {
            return 0.0;
        }
        return strategy.calculate(order);
    }

}
