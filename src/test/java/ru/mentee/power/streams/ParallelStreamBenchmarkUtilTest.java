package ru.mentee.power.streams;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import static org.assertj.core.api.Assertions.assertThat;

public class ParallelStreamBenchmarkUtilTest {

    @Test
    @DisplayName("complexCalculation должен возвращать одинаковые результаты для одинаковых входных данных")
    void complexCalculation_ShouldReturnConsistentResults() {
        long input = 42L;
        long result1 = ParallelStreamBenchmarkUtil.complexCalculation(input);
        long result2 = ParallelStreamBenchmarkUtil.complexCalculation(input);

        assertThat(result1).isEqualTo(result2);
    }

    @Test
    @DisplayName("Последовательная и параллельная обработка должны давать одинаковый результат")
    void sequentialAndParallelProcessing_ShouldReturnSameResult() {
        List<Long> data = LongStream.rangeClosed(1, 100)
                .boxed()
                .collect(Collectors.toList());

        long resultSequential = data.stream()
                .mapToLong(ParallelStreamBenchmarkUtil::complexCalculation)
                .sum();

        long resultParallel = ParallelStreamBenchmarkUtil.processDataParallel(data);

        assertThat(resultParallel).isEqualTo(resultSequential);
    }

    @Test
    @DisplayName("Проверка параллельного processData с разными размерами данных")
    void processDataParallel_WithDifferentDataSizes() {
        assertThat(ParallelStreamBenchmarkUtil.processDataParallel(List.of())).isEqualTo(0L); // Пустой список

        List<Long> smallData = LongStream.rangeClosed(1, 10)
                .boxed()
                .collect(Collectors.toList());
        assertThat(ParallelStreamBenchmarkUtil.processDataParallel(smallData)).isGreaterThan(0L);

        assertThat(ParallelStreamBenchmarkUtil.processDataParallel(smallData))
                .isEqualTo(ParallelStreamBenchmarkUtil.processDataSequential(smallData));
    }
}
