package ru.mentee.power.oop.polymorphism.shape;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Тесты для класса Rectangle")
public class RectangleTest {
    @Test
    @DisplayName("getArea должен вычислять площадь прямоугольника")
    void getAreaShouldCalculateCorrectly() {
        Rectangle r = new Rectangle("Blue", 4.0, 5.5);
        double expected = 4.0 * 5.5;
        assertThat(r.getArea()).isCloseTo(expected, within(1e-9));
    }

    @Test
    @DisplayName("getPerimeter должен вычислять периметр прямоугольника")
    void getPerimeterShouldCalculateCorrectly() {
        Rectangle r = new Rectangle("Green", 3.0, 7.0);
        double expected = 2 * (3.0 + 7.0);
        assertThat(r.getPerimeter()).isCloseTo(expected, within(1e-9));
    }

    @Test
    @DisplayName("Конструктор должен выбрасывать исключение при не положительных размерах")
    void constructorShouldThrowExceptionForNonPositiveDimensions() {
        assertThatThrownBy(() -> new Rectangle("Black", 0.0, 1.0))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> new Rectangle("Black", 1.0, 0.0))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> new Rectangle("Black", -1.0, 2.0))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> new Rectangle("Black", 2.0, -2.0))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
