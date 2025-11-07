package ru.mentee.power.lombok.example;

public class ShippingDemo {

    public static void main(String[] args) {

        Order_Lombok testOrderLombok = new Order_Lombok(5.0, "Центр", 150.0);
        System.out.println("Тестовый заказ: " + testOrderLombok);

        ShippingContext context = new ShippingContext();

        context.setStrategy(new WeightBasedShipping(1.5));
        double cost1 = context.calculateShippingCost(testOrderLombok);
        System.out.println("Стоимость по весу (1.5/кг): " + cost1);

        context.setStrategy(new FlatRateShipping(10.0));
        double cost2 = context.calculateShippingCost(testOrderLombok);
        System.out.println("Фиксированная ставка (10.0): " + cost2);

        context.setStrategy(new RegionBasedShipping());
        double cost3 = context.calculateShippingCost(testOrderLombok);
        System.out.println("Стоимость для региона 'Центр': " + cost3);

        Order_Lombok orderLombokNorth = new Order_Lombok(3.0, "Север", 50.0);
        double cost4 = context.calculateShippingCost(orderLombokNorth);
        System.out.println("Стоимость для региона 'Север': " + cost4);

        context.setStrategy(order -> order.getTotalAmount() * 0.05);
        double cost5 = context.calculateShippingCost(testOrderLombok);
        System.out.println("Стоимость как 5% от суммы заказа: " + cost5);
    }

}
