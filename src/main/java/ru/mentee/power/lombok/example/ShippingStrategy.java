package ru.mentee.power.lombok.example;

@FunctionalInterface
public interface ShippingStrategy {
    double calculate(Order_Lombok orderLombok);
}
