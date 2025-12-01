package ru.mentee.power.technicalInterview;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderTest {

    @Test
    @DisplayName("Тест группировки заказов по категориям")
    void testGroupOrdersByCategory() {
        List<Order> orders = Arrays.asList(
                new Order("iPhone 15", "Electronics", "Иван Петров", 89999.0, LocalDate.of(2024, 1, 15)),
                new Order("MacBook Pro", "Electronics", "Анна Смирнова", 199999.0, LocalDate.of(2024, 1, 20))
        );
        //Вызываем функцию группировки заказов по категориям
        Map<String, Double> categorySum = Order.groupOrdersByCategory(orders);
        assertEquals(1, categorySum.size());
    }

    @Test
    @DisplayName("Тест нахождения 3 самых дорогих заказов")
    void testGetTop3Orders() {
        List<Order> orders = Arrays.asList(
                new Order("iPhone 15", "Electronics", "Иван Петров", 89999.0, LocalDate.of(2024, 1, 15)),
                new Order("MacBook Pro", "Electronics", "Анна Смирнова", 199999.0, LocalDate.of(2024, 1, 20)),
                new Order("Кроссовки Nike", "Sports", "Петр Иванов", 12000.0, LocalDate.of(2024, 1, 18))
        );
        //Вызываем функцию нахождения 3 самых дорогих заказов
        List<Order> top3Orders = Order.getTop3Orders(orders);
        assertEquals(3, top3Orders.size());
    }

    @Test
    @DisplayName("Тест группировки покупателей по месяцам")
    void testGroupCustomersByMonth() {
        List<Order> orders = Arrays.asList(
                new Order("iPhone 15", "Electronics", "Иван Петров", 89999.0, LocalDate.of(2024, 1, 15)),
                new Order("MacBook Pro", "Electronics", "Анна Смирнова", 199999.0, LocalDate.of(2024, 1, 20)),
                new Order("Кроссовки Nike", "Sports", "Петр Иванов", 12000.0, LocalDate.of(2024, 2, 5))
        );
        //Вызываем функцию группировки покупателей по месяцам
        Map<Integer, Long> customerCountsByMonth = Order.groupCustomersByMonth(orders);
        assertEquals(2, customerCountsByMonth.size());
    }
}


