package ru.mentee.power.streams;

import java.util.List;

public class ParallelStreamBenchmarkUtil {

    private static final long COMPLEX_CALCULATION_ITERATIONS = 1000;

    public static long complexCalculation(long input) {
        long result = input;
        for (long i = 0; i < COMPLEX_CALCULATION_ITERATIONS; i++) {
            result += Math.sqrt(result + i) - Math.tan(result * i);
        }
        return result % 1000; // Возвращаем значение в определенном диапазоне
    }

    public static long processDataSequential(List<Long> data) {
        if (data == null || data.isEmpty()) {
            return 0L;
        }
        return data.stream()
                .mapToLong(ParallelStreamBenchmarkUtil::complexCalculation)
                .sum();
    }

    public static long processDataParallel(List<Long> data) {
        if (data == null || data.isEmpty()) {
            return 0L;
        }
        return data.parallelStream()
                .mapToLong(ParallelStreamBenchmarkUtil::complexCalculation)
                .sum();
    }
}
