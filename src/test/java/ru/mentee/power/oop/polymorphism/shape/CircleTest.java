package ru.mentee.power.oop.polymorphism.shape;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Тесты для класса Circle")
public class CircleTest {
    @Test
    @DisplayName("getArea должен вычислять площадь круга")
    void getAreaShouldCalculateCorrectly() {
        Circle c = new Circle("Red", 2.5);
        double expected = Math.PI * 2.5 * 2.5;
        assertThat(c.getArea()).isCloseTo(expected, within(1e-9));
    }

    @Test
    @DisplayName("getPerimeter должен вычислять длину окружности")
    void getPerimeterShouldCalculateCorrectly() {
        Circle c = new Circle("Blue", 3.0);
        double expected = 2 * Math.PI * 3.0;
        assertThat(c.getPerimeter()).isCloseTo(expected, within(1e-9));
    }

    @Test
    @DisplayName("Конструктор должен выбрасывать исключение при не положительном радиусе")
    void constructorShouldThrowExceptionForNonPositiveRadius() {
        assertThatThrownBy(() -> new Circle("Black", 0.0))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> new Circle("Black", -1.0))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
