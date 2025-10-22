package ru.mentee.power.functional;

import java.util.function.*;

public class MethodRefsUtil {


    public static Function<String, Integer> getStringLengthFunction() {
        return String::length;
    }


    public static Consumer<String> getPrintStringConsumer() {
        return System.out::println;
    }


    public static Supplier<Thread> getThreadSupplier() {
        return Thread::new;
    }


    public static BiFunction<Integer, Integer, String> getFormatSumFunction() {
        return (a, b) -> String.format("%d + %d = %d", a, b, a + b);
    }


    public static IntBinaryOperator getMaxOperator() {
        return Math::max;
    }


    public static ToIntFunction<String> getStringToIntLengthFunction() {
        return String::length;
    }
}
