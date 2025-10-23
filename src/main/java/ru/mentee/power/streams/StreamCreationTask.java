package ru.mentee.power.streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class StreamCreationTask {

    public static void main(String[] args) {
        System.out.println("--- Поток из коллекции ---");
        List<String> list = List.of("Apple", "Banana", "Cherry", "Date", "Fig", "Grape");
        List<String> firstFiveFromList = StreamCreationTaskUtil.getFirstNElementsFromList(list, 5);
        firstFiveFromList.forEach(System.out::println);

        System.out.println("\n--- Поток из массива ---");
        String[] array = {"One", "Two", "Three", "Four"};
        List<String> arrayAsList = StreamCreationTaskUtil.arrayToList(array);
        arrayAsList.forEach(System.out::println);

        System.out.println("\n--- Поток из числового диапазона --- ");
        List<Integer> range = StreamCreationTaskUtil.getRangeAsList(10, 20);
        range.forEach(System.out::println);

        System.out.println("\n--- Поток случайных чисел --- ");
        List<Double> randomNumbers = StreamCreationTaskUtil.generateRandomValues(5, Math::random);
        randomNumbers.forEach(System.out::println);

        System.out.println("\n--- Поток с помощью iterate --- ");
        List<Integer> sequence = StreamCreationTaskUtil.generateSequence(0, 5, 10);
        sequence.forEach(System.out::println);

        System.out.println("\n--- Поток из файла --- ");
        Path dataFile = Paths.get("data.txt"); // Убедитесь, что файл существует в корне проекта или укажите полный путь
        try {
            // Создадим файл, если он не существует, для демонстрации
            if (!Files.exists(dataFile)) {
                Files.write(dataFile, List.of("Line 1", "Line 2", "Line 3", "Line 4", "Line 5"));
            }
            List<String> lines = StreamCreationTaskUtil.readFirstNLinesFromFile(dataFile, 3);
            lines.forEach(System.out::println);
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }

        System.out.println("\n--- Пустой поток --- ");
        long count = StreamCreationTaskUtil.countEmptyStream();
        System.out.println("Количество элементов в пустом потоке: " + count);
    }
}
