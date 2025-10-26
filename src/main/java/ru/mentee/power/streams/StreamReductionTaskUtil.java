package ru.mentee.power.streams;

import java.util.OptionalInt;

import java.util.stream.IntStream;

public class StreamReductionTaskUtil {

    public static void printNumbersDivisibleBy10(IntStream stream) {
        stream.filter(n -> n % 10 == 0).forEach(System.out::println);
    }

    public static long countNumbersDivisibleBy3(IntStream stream) {
        return stream.filter(n -> n % 3 == 0).count();
    }

    public static int[] collectOddNumbers(IntStream stream) {
        return stream.filter(n -> n % 2 != 0).toArray();
    }

    public static int calculateSum(IntStream stream) {
        return stream.sum();
    }

    public static OptionalInt calculateProduct1to10(IntStream stream) {
        return stream.reduce((a, b) -> a * b);
    }
}
