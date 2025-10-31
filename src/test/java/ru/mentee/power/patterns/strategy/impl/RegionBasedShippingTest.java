package ru.mentee.power.patterns.strategy.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.mentee.power.patterns.strategy.Order;

public class RegionBasedShippingTest {

    private final RegionBasedShipping strategy = new RegionBasedShipping();

    @ParameterizedTest
    @CsvSource({
            "Центр, 5.0",
            "Север, 15.0",
            "Юг, 12.0", // Пример для default ветки
            "Восток, 12.0" // Еще один пример для default
    })
    @DisplayName("Должен рассчитывать стоимость в зависимости от региона")
    void shouldCalculateCostBasedOnRegion(String region, double expectedCost) {
        Order order = new Order(1.0, region, 50.0);
        double actualCost = strategy.calculate(order);

        Assertions.assertThat(actualCost).isEqualTo(expectedCost);
    }
}
