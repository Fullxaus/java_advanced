package ru.mentee.power.streams;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.*;

public class StreamCreationTaskUtilTest {

    @Test
    @DisplayName("getFirstNElementsFromList: Получение первых N элементов из списка")
    void getFirstNElementsFromList_ShouldReturnCorrectElements() {
        List<String> source = List.of("a", "b", "c", "d", "e");

        assertThat(StreamCreationTaskUtil.getFirstNElementsFromList(source, 3)).containsExactly("a", "b", "c");
        assertThat(StreamCreationTaskUtil.getFirstNElementsFromList(source, 10)).containsExactly("a", "b", "c", "d", "e");
        assertThat(StreamCreationTaskUtil.getFirstNElementsFromList(source, 0)).isEmpty();
        assertThat(StreamCreationTaskUtil.getFirstNElementsFromList(source, -1)).isEmpty();
        assertThat(StreamCreationTaskUtil.getFirstNElementsFromList(Collections.emptyList(), 5)).isEmpty();
        assertThat(StreamCreationTaskUtil.getFirstNElementsFromList(null, 5)).isEmpty();
    }

    @Test
    @DisplayName("arrayToList: Преобразование массива в список")
    void arrayToList_ShouldConvertCorrectly() {
        String[] sourceArray = {"one", "two", "three"};

        assertThat(StreamCreationTaskUtil.arrayToList(sourceArray)).containsExactly("one", "two", "three");
        assertThat(StreamCreationTaskUtil.arrayToList(new String[0])).isEmpty();
        assertThat(StreamCreationTaskUtil.arrayToList(null)).isEmpty();
    }

    @Test
    @DisplayName("getRangeAsList: Создание диапазона чисел")
    void getRangeAsList_ShouldCreateCorrectRange() {
        assertThat(StreamCreationTaskUtil.getRangeAsList(10, 15)).containsExactly(10, 11, 12, 13, 14, 15);
        assertThat(StreamCreationTaskUtil.getRangeAsList(10, 10)).containsExactly(10);
        assertThat(StreamCreationTaskUtil.getRangeAsList(15, 10)).isEmpty();
    }

    @Test
    @DisplayName("generateRandomValues: Генерация случайных значений")
    void generateRandomValues_ShouldGenerateCorrectNumberOfValues() {
        Supplier<Integer> intGenerator = () -> new Random().nextInt(100);

        List<Integer> randomInts = StreamCreationTaskUtil.generateRandomValues(5, intGenerator);
        assertThat(randomInts).hasSize(5);
        assertThat(randomInts).allSatisfy(i -> assertThat(i).isBetween(0, 99));

        assertThat(StreamCreationTaskUtil.generateRandomValues(0, intGenerator)).isEmpty();
        assertThat(StreamCreationTaskUtil.generateRandomValues(-1, intGenerator)).isEmpty();
        assertThat(StreamCreationTaskUtil.generateRandomValues(5, null)).isEmpty();
    }

    @Test
    @DisplayName("generateSequence: Генерация последовательности")
    void generateSequence_ShouldGenerateCorrectSequence() {
        assertThat(StreamCreationTaskUtil.generateSequence(0, 5, 6)).containsExactly(0, 5, 10, 15, 20, 25);
        assertThat(StreamCreationTaskUtil.generateSequence(0, 5, 1)).containsExactly(0);
        assertThat(StreamCreationTaskUtil.generateSequence(0, 5, 0)).isEmpty();
        assertThat(StreamCreationTaskUtil.generateSequence(0, 5, -1)).isEmpty();
    }

    @Test
    @DisplayName("readFirstNLinesFromFile: Чтение первых N строк из файла")
    void readFirstNLinesFromFile_ShouldReadCorrectly(@TempDir Path tempDir) throws IOException {
        Path testFile = tempDir.resolve("testData.txt");
        List<String> fileContent = List.of("Line One", "Line Two", "Line Three", "Line Four");
        Files.write(testFile, fileContent);

        assertThat(StreamCreationTaskUtil.readFirstNLinesFromFile(testFile, 3)).containsExactly("Line One", "Line Two", "Line Three");

        assertThat(StreamCreationTaskUtil.readFirstNLinesFromFile(testFile, 10)).containsExactly("Line One", "Line Two", "Line Three", "Line Four");

        assertThat(StreamCreationTaskUtil.readFirstNLinesFromFile(testFile, 0)).isEmpty();

        assertThat(StreamCreationTaskUtil.readFirstNLinesFromFile(testFile, -1)).isEmpty();

        assertThat(StreamCreationTaskUtil.readFirstNLinesFromFile(null, 3)).isEmpty();

        Path nonExistingFile = tempDir.resolve("nonExistingFile.txt");
        assertThat(StreamCreationTaskUtil.readFirstNLinesFromFile(nonExistingFile, 3)).isEmpty();
    }
    @Test
    @DisplayName("countEmptyStream: Подсчет элементов в пустом потоке")
    void countEmptyStream_ShouldReturnZero() {

        long count = StreamCreationTaskUtil.countEmptyStream();

        assertThat(count).isEqualTo(0L);
    }
}
