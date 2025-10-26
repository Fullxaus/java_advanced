package ru.mentee.power.streams;

import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.IntSummaryStatistics;

public class PrimitiveStreamTaskUtil {

    public static int calculateSumOfPositives(int[] data) {
        return IntStream.of(data)
                .filter(n -> n > 0)
                .sum();
    }

    public static OptionalDouble calculateAverage(int[] data) {
        return IntStream.of(data)
                .average();
    }

    public static List<Integer> getSquaresOfUniqueNumbers(int[] data) {
        return IntStream.of(data)
                .distinct()
                .map(n -> n * n)
                .boxed()
                .collect(Collectors.toList());
    }

    public static IntSummaryStatistics getStatsForNumbersGreaterThan10(int[] data) {
        return IntStream.of(data)
                .filter(n -> n > 10)
                .summaryStatistics();
    }
}
