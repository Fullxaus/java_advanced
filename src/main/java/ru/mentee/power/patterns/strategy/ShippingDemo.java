package ru.mentee.power.patterns.strategy;

import ru.mentee.power.patterns.strategy.impl.FlatRateShipping;
import ru.mentee.power.patterns.strategy.impl.RegionBasedShipping;
import ru.mentee.power.patterns.strategy.impl.WeightBasedShipping;

public class ShippingDemo {

    public static void main(String[] args) {

        Order testOrder = new Order(5.0, "Центр", 150.0);
        System.out.println("Тестовый заказ: " + testOrder);

        ShippingContext context = new ShippingContext();

        context.setStrategy(new WeightBasedShipping(1.5));
        double cost1 = context.calculateShippingCost(testOrder);
        System.out.println("Стоимость по весу (1.5/кг): " + cost1);

        context.setStrategy(new FlatRateShipping(10.0));
        double cost2 = context.calculateShippingCost(testOrder);
        System.out.println("Фиксированная ставка (10.0): " + cost2);

        context.setStrategy(new RegionBasedShipping());
        double cost3 = context.calculateShippingCost(testOrder);
        System.out.println("Стоимость для региона 'Центр': " + cost3);

        Order orderNorth = new Order(3.0, "Север", 50.0);
        double cost4 = context.calculateShippingCost(orderNorth);
        System.out.println("Стоимость для региона 'Север': " + cost4);


        context.setStrategy(order -> order.getTotalAmount() * 0.05);
        double cost5 = context.calculateShippingCost(testOrder);
        System.out.println("Стоимость как 5% от суммы заказа: " + cost5);
    }

}

