package ru.mentee.power.patterns.strategy.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.mentee.power.patterns.strategy.Order;

public class FlatRateShippingTest {

    @Test
    @DisplayName("Должен возвращать фиксированную ставку")
    void shouldReturnFlatRate() {
        Order order = new Order(5.0, "Юг", 200.0); // Данные заказа не влияют
        double flatRate = 10.0;
        FlatRateShipping strategy = new FlatRateShipping(flatRate);

        double actualCost = strategy.calculate(order);

        Assertions.assertThat(actualCost).isEqualTo(flatRate);
    }
}
