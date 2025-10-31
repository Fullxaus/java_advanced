package ru.mentee.power.patterns.strategy.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.mentee.power.patterns.strategy.Order;

public class WeightBasedShippingTest {

    @Test
    @DisplayName("Должен корректно рассчитывать стоимость по весу")
    void shouldCalculateCostBasedOnWeight() {
        Order order = new Order(2.5, "Центр", 100.0);
        WeightBasedShipping strategy = new WeightBasedShipping(1.5); // 1.5 за кг
        double expectedCost = 2.5 * 1.5;

        double actualCost = strategy.calculate(order);

        Assertions.assertThat(actualCost).isEqualTo(expectedCost);
    }
}
