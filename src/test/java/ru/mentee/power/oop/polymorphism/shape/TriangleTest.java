package ru.mentee.power.oop.polymorphism.shape;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Тесты для класса Triangle")
public class TriangleTest {
    @Test
    @DisplayName("getArea должен вычислять площадь треугольника по формуле Герона")
    void getAreaShouldCalculateCorrectly() {
        Triangle t = new Triangle("Yellow", 3.0, 4.0, 5.0); // прямоугольный треугольник
        double p = (3.0 + 4.0 + 5.0) / 2.0;
        double expected = Math.sqrt(p * (p - 3.0) * (p - 4.0) * (p - 5.0));
        assertThat(t.getArea()).isCloseTo(expected, within(1e-9));
    }

    @Test
    @DisplayName("getPerimeter должен вычислять сумму сторон")
    void getPerimeterShouldCalculateCorrectly() {
        Triangle t = new Triangle("Purple", 2.5, 3.5, 4.0);
        double expected = 2.5 + 3.5 + 4.0;
        assertThat(t.getPerimeter()).isCloseTo(expected, within(1e-9));
    }

    @Test
    @DisplayName("Конструктор должен выбрасывать исключение при не положительных сторонах")
    void constructorShouldThrowExceptionForNonPositiveSides() {
        assertThatThrownBy(() -> new Triangle("Black", 0.0, 1.0, 1.0))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> new Triangle("Black", 1.0, -1.0, 1.0))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> new Triangle("Black", 1.0, 1.0, 0.0))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
