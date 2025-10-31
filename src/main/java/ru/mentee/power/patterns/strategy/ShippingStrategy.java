package ru.mentee.power.patterns.strategy;

@FunctionalInterface
public interface ShippingStrategy {
    /**
     * Рассчитывает стоимость доставки для заказа.
     * @param order Заказ
     * @return Стоимость доставки
     */
    double calculate(Order order);
}
