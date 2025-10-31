package ru.mentee.power.patterns.strategy;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ShippingContextTest {

    @Test
    @DisplayName("Должен вызывать метод calculate у установленной стратегии")
    void shouldCallCalculateOnSetStrategy() {
        ShippingContext context = new ShippingContext();
        ShippingStrategy mockStrategy = Mockito.mock(ShippingStrategy.class);
        Order order = new Order(1.0, "Тест", 10.0);
        double expectedCost = 5.5;

        when(mockStrategy.calculate(order)).thenReturn(expectedCost);
        context.setStrategy(mockStrategy);

        double actualCost = context.calculateShippingCost(order);

        verify(mockStrategy).calculate(order);
        Assertions.assertThat(actualCost).isEqualTo(expectedCost);
    }

    @Test
    @DisplayName("Должен возвращать 0.0, если стратегия не установлена")
    void shouldHandleNoStrategySet() {
        ShippingContext context = new ShippingContext();
        Order order = new Order(1.0, "Тест", 10.0);

        double actualCost = context.calculateShippingCost(order);

        Assertions.assertThat(actualCost).isEqualTo(0.0);
    }

}
