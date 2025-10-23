package ru.mentee.power.streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamCreationTaskUtil {

    /**
     * Возвращает первые n элементов из списка как новый список.
     * Если n больше размера списка, возвращает все элементы списка.
     * Если n <= 0, возвращает пустой список.
     * Если source null, возвращает пустой список.
     * @param source исходный список
     * @param n количество элементов для взятия
     * @return список первых n элементов
     */
    public static <T> List<T> getFirstNElementsFromList(List<T> source, int n) {
        if (source == null || n <= 0) {
            return Collections.emptyList();
        }
        return source.stream()
                .limit(n)
                .collect(Collectors.toList());
    }

    /**
     * Возвращает все элементы массива как список.
     * Если array null, возвращает пустой список.
     * @param array исходный массив
     * @return список всех элементов массива
     */
    public static <T> List<T> arrayToList(T[] array) {
        if (array == null) {
            return Collections.emptyList();
        }
        return Stream.of(array)
                .collect(Collectors.toList());
    }

    /**
     * Возвращает список чисел в указанном диапазоне [start, end].
     * Если start > end, возвращает пустой список.
     * @param start начальное значение (включительно)
     * @param end конечное значение (включительно)
     * @return список всех чисел в указанном диапазоне
     */
    public static List<Integer> getRangeAsList(int start, int end) {
        if (start > end) {
            return Collections.emptyList();
        }
        return IntStream.rangeClosed(start, end)
                .boxed()
                .collect(Collectors.toList());
    }

    /**
     * Генерирует список с указанным количеством элементов, используя предоставленный Supplier.
     * Если count <=0 или generator null, возвращает пустой список.
     * @param count количество элементов
     * @param generator поставщик элементов
     * @return список сгенерированных элементов
     */
    public static <T> List<T> generateRandomValues(int count, Supplier<T> generator) {
        if (count <= 0 || generator == null) {
            return Collections.emptyList();
        }
        return Stream.generate(generator)
                .limit(count)
                .collect(Collectors.toList());
    }

    /**
     * Генерирует последовательность Integer с заданным начальным значением, шагом и количеством элементов.
     * Если count <= 0, возвращает пустой список.
     * @param seed начальное значение
     * @param step шаг
     * @param count количество элементов
     * @return список элементов последовательности
     */
    public static List<Integer> generateSequence(int seed, int step, int count) {
        if (count <= 0) {
            return Collections.emptyList();
        }
        return Stream.iterate(seed, n -> n + step)
                .limit(count)
                .collect(Collectors.toList());
    }

    /**
     * Читает первые n строк из файла.
     * Если n <=0 или path null, или файл не существует, возвращает пустой список.
     * @param path путь к файлу
     * @param n количество строк для чтения
     * @return список прочитанных строк
     * @throws IOException при ошибке чтения файла (кроме случая несуществующего файла, который обрабатывается возвратом пустого списка)
     */
    public static List<String> readFirstNLinesFromFile(Path path, int n) throws IOException {
        if (path == null || n <= 0 || !Files.exists(path)) {
            return Collections.emptyList();
        }
        try (Stream<String> lines = Files.lines(path)) {
            return lines.limit(n)
                    .collect(Collectors.toList());
        }
    }

    /**
     * Подсчитывает количество элементов в пустом потоке.
     * @return количество элементов (всегда 0)
     */
    public static long countEmptyStream() {
        return Stream.empty()
                .count();
    }
}
