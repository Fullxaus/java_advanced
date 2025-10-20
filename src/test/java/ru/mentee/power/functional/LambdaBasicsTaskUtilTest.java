package ru.mentee.power.functional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import static org.assertj.core.api.Assertions.*;

public class LambdaBasicsTaskUtilTest {

    @Test
    @DisplayName("Предикат должен корректно проверять строки, начинающиеся на 'A'")
    void startsWithAPredicateShouldWorkCorrectly() {
        Predicate<String> predicate = LambdaBasicsTaskUtil.createStartsWithAPredicate();
        assertThat(predicate).isNotNull();
        assertThat(predicate.test("Apple")).isTrue();
        assertThat(predicate.test("android")).isTrue();
        assertThat(predicate.test("Banana")).isFalse();
        assertThat(predicate.test("")).isFalse();
        assertThat(predicate.test(null)).isFalse();
    }

    @Test
    @DisplayName("Функция должна корректно проверять положительные числа")
    void isPositiveFunctionShouldWorkCorrectly() {
        Function<Integer, Boolean> function = LambdaBasicsTaskUtil.createIsPositiveFunction();
        assertThat(function).isNotNull();
        assertThat(function.apply(5)).isTrue();
        assertThat(function.apply(0)).isFalse();
        assertThat(function.apply(-10)).isFalse();

        try {
            Boolean result = function.apply(null);

            assertThat(result).isIn(Boolean.TRUE, Boolean.FALSE);
        } catch (NullPointerException ignored) {

        }
    }

    @Test
    @DisplayName("Консьюмер должен печатать элементы списка")
    void printListElementsConsumerShouldWorkCorrectly() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        try {
            Consumer<List<String>> consumer = LambdaBasicsTaskUtil.createPrintListElementsConsumer();
            List<String> testList = List.of("One", "Two", "Three");

            assertThat(consumer).isNotNull();
            consumer.accept(testList);
            String output = outContent.toString().replace("\r\n", "\n");
            assertThat(output).isEqualTo("One\nTwo\nThree\n");


            outContent.reset();
            consumer.accept(List.of());
            assertThat(outContent.toString()).isEmpty();

            outContent.reset();
            consumer.accept(null);
            assertThat(outContent.toString()).isEmpty();

        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    @DisplayName("Поставщик должен генерировать случайные числа в диапазоне 0-99")
    void randomIntSupplierShouldGenerateNumbersInRange() {
        Supplier<Integer> supplier = LambdaBasicsTaskUtil.createRandomIntSupplier();
        assertThat(supplier).isNotNull();
        for (int i = 0; i < 100; i++) {
            Integer randomNumber = supplier.get();
            assertThat(randomNumber).isNotNull();
            assertThat(randomNumber).isBetween(0, 99);
        }
    }
}
