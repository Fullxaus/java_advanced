package ru.mentee.power.functional;

import org.junit.jupiter.api.Test;

import java.util.function.*;

import static org.junit.jupiter.api.Assertions.*;

public class MethodRefsUtilTest {

    @Test
    public void testGetStringLengthFunction() {
        Function<String, Integer> function = MethodRefsUtil.getStringLengthFunction();
        assertEquals(5, function.apply("Hello"));
        assertEquals(0, function.apply(""));
    }

    @Test
    public void testGetPrintStringConsumer() {
        // Проверка Consumer не имеет смысла, т.к. он просто печатает в консоль
        // Но можно проверить, что он не выбрасывает исключения
        Consumer<String> consumer = MethodRefsUtil.getPrintStringConsumer();
        assertDoesNotThrow(() -> consumer.accept("Test"));
    }

    @Test
    public void testGetThreadSupplier() {
        Supplier<Thread> supplier = MethodRefsUtil.getThreadSupplier();
        Thread thread = supplier.get();
        assertNotNull(thread);
    }

    @Test
    public void testGetFormatSumFunction() {
        BiFunction<Integer, Integer, String> function = MethodRefsUtil.getFormatSumFunction();
        assertEquals("5 + 7 = 12", function.apply(5, 7));
        assertEquals("-1 + 10 = 9", function.apply(-1, 10));
    }

    @Test
    public void testGetMaxOperator() {
        IntBinaryOperator operator = MethodRefsUtil.getMaxOperator();
        assertEquals(15, operator.applyAsInt(10, 15));
        assertEquals(-2, operator.applyAsInt(-5, -2));
    }

    @Test
    public void testGetStringToIntLengthFunction() {
        ToIntFunction<String> function = MethodRefsUtil.getStringToIntLengthFunction();
        assertEquals(8, function.applyAsInt("Function"));
        assertEquals(0, function.applyAsInt(""));
    }
}
