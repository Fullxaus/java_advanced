package ru.mentee.power.streams;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class StreamCollectorsTask {

    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("O1", "Alice", "Laptop", 1, 1200.0, OrderStatus.DELIVERED),
                new Order("O2", "Bob", "Mouse", 2, 25.0, OrderStatus.SHIPPED),
                new Order("O3", "Alice", "Keyboard", 1, 75.0, OrderStatus.PROCESSING),
                new Order("O4", "Charlie", "Monitor", 1, 300.0, OrderStatus.DELIVERED),
                new Order("O5", "Bob", "Webcam", 1, 50.0, OrderStatus.DELIVERED),
                new Order("O6", "Alice", "Mouse", 1, 25.0, OrderStatus.CANCELLED),
                new Order("O7", "Charlie", "Keyboard", 1, 80.0, OrderStatus.NEW),
                new Order("O8", "Dave", "Speakers", 2, 30.0, OrderStatus.DELIVERED),
                new Order("O9", "Eve", "Headphones", 1, 100.0, OrderStatus.PROCESSING),
                new Order("O10", "Bob", "Mouse", 3, 25.0, OrderStatus.SHIPPED) // Duplicate product for testing
        );

        System.out.println("--- Исходные заказы ---");
        orders.forEach(System.out::println);

        System.out.println("\n--- Уникальные названия товаров ---");
        Set<String> uniqueProductNames = orders.stream()
                .map(Order::getProductName)
                .collect(Collectors.toSet());
        System.out.println(uniqueProductNames);


        System.out.println("\n--- Заказы в виде Map<orderId, Order> ---");
        Map<String, Order> orderMap = orders.stream()
                .collect(Collectors.toMap(Order::getOrderId, order -> order, (oldValue, newValue) -> oldValue));
        orderMap.forEach((id, order) -> System.out.println(id + ": " + order.getProductName()));

        System.out.println("\n--- Общая сумма всех заказов ---");
        double totalSum = orders.stream()
                .mapToDouble(Order::getTotalPrice)
                .sum();
        System.out.println("Общая сумма: " + totalSum);


        System.out.println("\n--- Группировка заказов по статусу --- ");
        Map<OrderStatus, List<Order>> ordersByStatus = orders.stream()
                .collect(Collectors.groupingBy(Order::getStatus));
        ordersByStatus.forEach((status, orderList) -> {
            System.out.println(status + ":");
            orderList.forEach(o -> System.out.println("  " + o));
        });

        System.out.println("\n--- Сумма заказов по покупателям ---");
        Map<String, Double> totalAmountByCustomer = orders.stream()
                .collect(Collectors.groupingBy(Order::getCustomerName, Collectors.summingDouble(Order::getTotalPrice)));
        totalAmountByCustomer.forEach((customer, amount) -> System.out.printf("%s: %.2f\n", customer, amount));

        System.out.println("\n--- Разделение на выполненные и невыполненные ---");
        Map<Boolean, List<Order>> partitionedOrders = orders.stream()
                .collect(Collectors.partitioningBy(order -> order.getStatus() == OrderStatus.DELIVERED));
        System.out.println("Выполненные:");
        partitionedOrders.getOrDefault(true, List.of()).forEach(o -> System.out.println("  " + o));
        System.out.println("Невыполненные:");
        partitionedOrders.getOrDefault(false, List.of()).forEach(o -> System.out.println("  " + o));


        System.out.println("\n--- Список ID заказов через запятую ---");
        String orderIdsString = orders.stream()
                .map(Order::getOrderId)
                .collect(Collectors.joining(", "));
        System.out.println(orderIdsString);
    }
}