package ru.mentee.power.functional;

import java.util.function.*;

public class MethodRefsAndMoreTask {

    public static void main(String[] args) {


        System.out.println("--- Ссылки на методы ---");


        Function<String, Integer> getStringLength = MethodRefsUtil.getStringLengthFunction();
        System.out.println("Длина 'Java': " + getStringLength.apply("Java"));


        Consumer<String> printString = MethodRefsUtil.getPrintStringConsumer();
        printString.accept("Печать этой строки через ссылку.");


        Supplier<Thread> createThread = MethodRefsUtil.getThreadSupplier();
        Thread t = createThread.get();
        System.out.println("Создан поток: " + t.getName());



        System.out.println("\n--- BiFunction: Сумма чисел в строку ---");
        BiFunction<Integer, Integer, String> formatSum = MethodRefsUtil.getFormatSumFunction();
        System.out.println(formatSum.apply(5, 7));
        System.out.println(formatSum.apply(-1, 10));


        System.out.println("\n--- IntBinaryOperator: Максимум двух int ---");
        IntBinaryOperator findMax = MethodRefsUtil.getMaxOperator();
        System.out.println("Максимум из 10 и 15: " + findMax.applyAsInt(10, 15));
        System.out.println("Максимум из -5 и -2: " + findMax.applyAsInt(-5, -2));


        System.out.println("\n--- ToIntFunction: Длина строки в int ---");
        ToIntFunction<String> stringToIntLength = MethodRefsUtil.getStringToIntLengthFunction();
        System.out.println("Длина 'Function': " + stringToIntLength.applyAsInt("Function"));
        System.out.println("Длина '': " + stringToIntLength.applyAsInt(""));
    }
}
