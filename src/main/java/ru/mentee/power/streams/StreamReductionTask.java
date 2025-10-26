package ru.mentee.power.streams;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.OptionalLong;
import java.util.stream.IntStream;

public class StreamReductionTask {

    public static void main(String[] args) {
        // ВАЖНО: Потоки одноразовые! Для каждой задачи создаем поток заново.

        System.out.println("--- Задача 1: Числа, кратные 10 ---");
        IntStream stream1 = IntStream.rangeClosed(1, 100);
        // Демонстрация вызова утилитарного метода
        System.out.println("Вывод чисел, кратных 10:");
        StreamReductionTaskUtil.printNumbersDivisibleBy10(stream1);

        System.out.println("\n--- Задача 2: Количество чисел, кратных 3 ---");
        IntStream stream2 = IntStream.rangeClosed(1, 100);
        // Демонстрация вызова утилитарного метода
        long countDivisibleBy3 = StreamReductionTaskUtil.countNumbersDivisibleBy3(stream2);
        System.out.println("Количество: " + countDivisibleBy3);

        System.out.println("\n--- Задача 3: Массив нечетных чисел ---");
        IntStream stream3 = IntStream.rangeClosed(1, 100);
        // Демонстрация вызова утилитарного метода
        int[] oddNumbersArray = StreamReductionTaskUtil.collectOddNumbers(stream3);
        System.out.println("Массив: " + Arrays.toString(oddNumbersArray));

        System.out.println("\n--- Задача 4: Сумма всех чисел ---");
        IntStream stream4 = IntStream.rangeClosed(1, 100);
        // Демонстрация вызова утилитарного метода
        int sumOfAll = StreamReductionTaskUtil.calculateSum(stream4);
        // Альтернатива: IntStream имеет удобный метод sum()
        // int sumOfAllAlternative = IntStream.rangeClosed(1, 100).sum();
        System.out.println("Сумма: " + sumOfAll);

        System.out.println("\n--- Задача 5: Произведение чисел от 1 до 10 ---");
        IntStream stream5 = IntStream.rangeClosed(1, 10);
        // Демонстрация вызова утилитарного метода
        OptionalInt productOpt = StreamReductionTaskUtil.calculateProduct1to10(stream5);
        productOpt.ifPresentOrElse(
                product -> System.out.println("Произведение 1-10: " + product),
                () -> System.out.println("Не удалось вычислить произведение (возможно, поток пуст).")
        );
    }
}
