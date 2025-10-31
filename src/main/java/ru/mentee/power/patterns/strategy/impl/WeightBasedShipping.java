package ru.mentee.power.patterns.strategy.impl;

import ru.mentee.power.patterns.strategy.Order;
import ru.mentee.power.patterns.strategy.ShippingStrategy;

public class WeightBasedShipping implements ShippingStrategy {
    private final double ratePerKg;

    public WeightBasedShipping(double ratePerKg) {
        this.ratePerKg = ratePerKg;
    }

    @Override
    public double calculate(Order order) {
        return order.getTotalWeight() * ratePerKg;
    }
}
