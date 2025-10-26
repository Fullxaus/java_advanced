package ru.mentee.power.streams;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.OptionalDouble;
import static org.assertj.core.api.Assertions.*;

public class PrimitiveStreamTaskUtilTest {

    @Test
    @DisplayName("Задача 1: Сумма положительных")
    void sumOfPositives() {
        int[] data = {10, -5, 20, 0, 15}; // Сумма положительных = 45
        int sum = PrimitiveStreamTaskUtil.calculateSumOfPositives(data);
        assertThat(sum).isEqualTo(45);
    }

    @Test
    @DisplayName("Задача 2: Среднее значение")
    void averageValue() {
        int[] data = {1, 2, 3, 4, 5}; // Среднее = 3.0
        OptionalDouble avg = PrimitiveStreamTaskUtil.calculateAverage(data);
        assertThat(avg).isPresent().hasValue(3.0);
    }

    @Test
    @DisplayName("Задача 2: Среднее для пустого массива")
    void averageValueEmpty() {
        int[] data = {};
        OptionalDouble avg = PrimitiveStreamTaskUtil.calculateAverage(data);
        assertThat(avg).isEmpty();
    }

    @Test
    @DisplayName("Задача 3: Квадраты уникальных")
    void squaresOfUniqueNumbers() {
        int[] data = {1, 2, 1, 3, 2, 4}; // Уникальные: 1, 2, 3, 4. Квадраты: 1, 4, 9, 16
        List<Integer> squares = PrimitiveStreamTaskUtil.getSquaresOfUniqueNumbers(data);
        assertThat(squares).containsExactlyInAnyOrder(1, 4, 9, 16);
    }

    @Test
    @DisplayName("Задача 4: Статистика > 10")
    void statisticsGreaterThan10() {
        int[] data = {5, 15, 20, 10, 25}; // > 10: 15, 20, 25
        IntSummaryStatistics stats = PrimitiveStreamTaskUtil.getStatsForNumbersGreaterThan10(data);
        assertThat(stats.getCount()).isEqualTo(3);
        assertThat(stats.getMin()).isEqualTo(15);
        assertThat(stats.getMax()).isEqualTo(25);
        assertThat(stats.getSum()).isEqualTo(60);
        assertThat(stats.getAverage()).isEqualTo(20.0);
    }
}
