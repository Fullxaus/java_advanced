package ru.mentee.power.streams;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class StreamCollectorsTaskUtil {

    public static Set<String> getUniqueProductNames(List<Order> orders) {
        return orders.stream()
                .map(Order::getProductName)
                .collect(Collectors.toSet());
    }

    public static Map<String, Order> getOrderMap(List<Order> orders) {
        return orders.stream()
                .collect(Collectors.toMap(Order::getOrderId, order -> order, (oldValue, newValue) -> oldValue));
    }

    public static double getTotalSum(List<Order> orders) {
        return orders.stream()
                .mapToDouble(Order::getTotalPrice)
                .sum();
    }

    public static Map<OrderStatus, List<Order>> getOrdersByStatus(List<Order> orders) {
        return orders.stream()
                .collect(Collectors.groupingBy(Order::getStatus));
    }

    public static Map<String, Double> getTotalAmountByCustomer(List<Order> orders) {
        return orders.stream()
                .collect(Collectors.groupingBy(Order::getCustomerName, Collectors.summingDouble(Order::getTotalPrice)));
    }

    public static Map<Boolean, List<Order>> partitionOrdersByStatus(List<Order> orders) {
        return orders.stream()
                .collect(Collectors.partitioningBy(order -> order.getStatus() == OrderStatus.DELIVERED));
    }

    public static String getOrderIdsString(List<Order> orders) {
        return orders.stream()
                .map(Order::getOrderId)
                .collect(Collectors.joining(", "));
    }
}
