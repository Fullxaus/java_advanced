package ru.mentee.power.patterns.strategy.impl;

import ru.mentee.power.patterns.strategy.Order;
import ru.mentee.power.patterns.strategy.ShippingStrategy;

public class FlatRateShipping implements ShippingStrategy {
    private final double flatRate;
    public FlatRateShipping(double flatRate) {
        this.flatRate = flatRate;
    }

    @Override
    public double calculate(Order order) {
        return flatRate;
    }

}
