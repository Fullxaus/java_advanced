package ru.mentee.power.streams;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StreamCollectorsTaskUtilTest {

    @Test
    void testGetUniqueProductNames() {

        List<Order> orders = List.of(
                new Order("O1", "Alice", "Laptop", 1, 1200.0, OrderStatus.DELIVERED),
                new Order("O2", "Bob", "Mouse", 2, 25.0, OrderStatus.SHIPPED),
                new Order("O3", "Alice", "Keyboard", 1, 75.0, OrderStatus.PROCESSING),
                new Order("O4", "Charlie", "Monitor", 1, 300.0, OrderStatus.DELIVERED),
                new Order("O5", "Bob", "Webcam", 1, 50.0, OrderStatus.DELIVERED)
        );


        Set<String> uniqueProductNames = StreamCollectorsTaskUtil.getUniqueProductNames(orders);

        assertEquals(5, uniqueProductNames.size());
        assertTrue(uniqueProductNames.contains("Laptop"));
        assertTrue(uniqueProductNames.contains("Mouse"));
        assertTrue(uniqueProductNames.contains("Keyboard"));
        assertTrue(uniqueProductNames.contains("Monitor"));
        assertTrue(uniqueProductNames.contains("Webcam"));
    }

    @Test
    void testGetOrderMap() {

        List<Order> orders = List.of(
                new Order("O1", "Alice", "Laptop", 1, 1200.0, OrderStatus.DELIVERED),
                new Order("O2", "Bob", "Mouse", 2, 25.0, OrderStatus.SHIPPED),
                new Order("O3", "Alice", "Keyboard", 1, 75.0, OrderStatus.PROCESSING)
        );

        Map<String, Order> orderMap = StreamCollectorsTaskUtil.getOrderMap(orders);

        assertEquals(3, orderMap.size());
        assertNotNull(orderMap.get("O1"));
        assertNotNull(orderMap.get("O2"));
        assertNotNull(orderMap.get("O3"));
    }

    @Test
    void testGetTotalSum() {

        List<Order> orders = List.of(
                new Order("O1", "Alice", "Laptop", 1, 1200.0, OrderStatus.DELIVERED),
                new Order("O2", "Bob", "Mouse", 2, 25.0, OrderStatus.SHIPPED),
                new Order("O3", "Alice", "Keyboard", 1, 75.0, OrderStatus.PROCESSING)
        );

        double totalSum = StreamCollectorsTaskUtil.getTotalSum(orders);

        assertEquals(1325.0, totalSum, 0.01);
    }

    @Test
    void testGetOrdersByStatus() {

        List<Order> orders = List.of(
                new Order("O1", "Alice", "Laptop", 1, 1200.0, OrderStatus.DELIVERED),
                new Order("O2", "Bob", "Mouse", 2, 25.0, OrderStatus.SHIPPED),
                new Order("O3", "Alice", "Keyboard", 1, 75.0, OrderStatus.PROCESSING),
                new Order("O4", "Charlie", "Monitor", 1, 300.0, OrderStatus.DELIVERED)
        );

        Map<OrderStatus, List<Order>> ordersByStatus = StreamCollectorsTaskUtil.getOrdersByStatus(orders);

        assertEquals(3, ordersByStatus.size());
        assertEquals(2, ordersByStatus.get(OrderStatus.DELIVERED).size());
        assertEquals(1, ordersByStatus.get(OrderStatus.SHIPPED).size());
        assertEquals(1, ordersByStatus.get(OrderStatus.PROCESSING).size());
    }

    @Test
    void testGetUniqueProductNames_EmptyList() {

        List<Order> orders = List.of();

        Set<String> uniqueProductNames = StreamCollectorsTaskUtil.getUniqueProductNames(orders);

        assertTrue(uniqueProductNames.isEmpty());
    }

    @Test
    void testGetOrderMap_EmptyList() {

        List<Order> orders = List.of();

        Map<String, Order> orderMap = StreamCollectorsTaskUtil.getOrderMap(orders);

        assertTrue(orderMap.isEmpty());
    }

    @Test
    void testGetTotalSum_EmptyList() {

        List<Order> orders = List.of();

        double totalSum = StreamCollectorsTaskUtil.getTotalSum(orders);

        assertEquals(0.0, totalSum, 0.01);
    }

    @Test
    void testGetOrdersByStatus_EmptyList() {

        List<Order> orders = List.of();

        Map<OrderStatus, List<Order>> ordersByStatus = StreamCollectorsTaskUtil.getOrdersByStatus(orders);


        assertTrue(ordersByStatus.isEmpty());
    }

    @Test
    void testGetTotalAmountByCustomer_EmptyList() {

        List<Order> orders = List.of();

        Map<String, Double> totalAmountByCustomer = StreamCollectorsTaskUtil.getTotalAmountByCustomer(orders);

        assertTrue(totalAmountByCustomer.isEmpty());
    }

    @Test
    void testPartitionOrdersByStatus_EmptyList() {

        List<Order> orders = List.of();


        Map<Boolean, List<Order>> partitionedOrders = StreamCollectorsTaskUtil.partitionOrdersByStatus(orders);


        assertTrue(partitionedOrders.get(true).isEmpty());
        assertTrue(partitionedOrders.get(false).isEmpty());
    }

    @Test
    void testGetOrderIdsString_EmptyList() {

        List<Order> orders = List.of();


        String orderIdsString = StreamCollectorsTaskUtil.getOrderIdsString(orders);


        assertEquals("", orderIdsString);
    }

    @Test
    void testGetOrderIdsString_DuplicateOrderIds() {

        List<Order> orders = List.of(
                new Order("O1", "Alice", "Laptop", 1, 1200.0, OrderStatus.DELIVERED),
                new Order("O1", "Bob", "Mouse", 2, 25.0, OrderStatus.SHIPPED),
                new Order("O3", "Alice", "Keyboard", 1, 75.0, OrderStatus.PROCESSING)
        );

        String orderIdsString = StreamCollectorsTaskUtil.getOrderIdsString(orders);

        assertEquals("O1, O1, O3", orderIdsString);
    }

    }
