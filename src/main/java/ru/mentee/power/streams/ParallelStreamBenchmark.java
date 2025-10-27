package ru.mentee.power.streams;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class ParallelStreamBenchmark {

    // Увеличьте или уменьшите, чтобы время выполнения было заметным (секунды)
    private static final long N_ELEMENTS = 50_000;
    private static final long COMPLEX_CALCULATION_ITERATIONS = 1000;

    // Имитация сложной вычислительной операции
    public static long complexCalculation(long input) {
        long result = input;
        for (long i = 0; i < COMPLEX_CALCULATION_ITERATIONS; i++) {
            result += Math.sqrt(result + i) - Math.tan(result * i);
        }
        return result % 1000; // Вернем что-то предсказуемое по размеру
    }

    public static void main(String[] args) {
        System.out.println("Подготовка данных для " + N_ELEMENTS + " элементов...");
        // Используем LongStream для генерации, но соберем в List,
        // чтобы источник был одинаковым для обоих тестов.
        List<Long> data = LongStream.rangeClosed(1, N_ELEMENTS)
                .boxed() // Преобразуем в Stream<Long>
                .collect(Collectors.toList()); // Собираем в список
        System.out.println("Данные готовы.");

        // --- Последовательная обработка ---
        System.out.println("\nЗапуск последовательной обработки...");
        Instant startSequential = Instant.now();

        // Демонстрация вызова утилитарного метода
        long resultSequential = ParallelStreamBenchmarkUtil.processDataSequential(data);

        Instant endSequential = Instant.now();
        Duration durationSequential = Duration.between(startSequential, endSequential);
        System.out.println("Последовательная обработка завершена за: " + durationSequential.toMillis() + " мс");
        System.out.println("Результат (контрольная сумма): " + resultSequential); // Выводим для проверки, что что-то посчиталось

        // --- Параллельная обработка ---
        System.out.println("\nЗапуск параллельной обработки...");
        Instant startParallel = Instant.now();

        // Демонстрация вызова утилитарного метода
        long resultParallel = ParallelStreamBenchmarkUtil.processDataParallel(data);

        Instant endParallel = Instant.now();
        Duration durationParallel = Duration.between(startParallel, endParallel);
        System.out.println("Параллельная обработка завершена за: " + durationParallel.toMillis() + " мс");
        System.out.println("Результат (контрольная сумма): " + resultParallel);

        // --- Анализ результатов ---
        System.out.println("\nСравнение:");
        if (durationParallel.compareTo(durationSequential) < 0) {
            double speedup = (double) durationSequential.toNanos() / durationParallel.toNanos();
            System.out.printf("Параллельная обработка быстрее в %.2f раз.\n", speedup);
        } else {
            System.out.println("Последовательная обработка оказалась быстрее или равна параллельной.");
        }

        // Опционально: Проверить, совпадают ли результаты (должны совпадать для sum)
        if (resultSequential == resultParallel) {
            System.out.println("Результаты совпадают.");
        } else {
            System.err.println("ВНИМАНИЕ: Результаты не совпадают!" +
                    " Sequential: " + resultSequential +
                    " Parallel: " + resultParallel);
        }
    }
}
