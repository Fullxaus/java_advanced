package ru.mentee.power.functional;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class LambdaBasicsTask {

    public static void main(String[] args) {
        Predicate<String> startsWithA = LambdaBasicsTaskUtil.createStartsWithAPredicate();
        System.out.println("Apple: " + startsWithA.test("Apple"));
        System.out.println("Banana: " + startsWithA.test("Banana"));
        System.out.println("Avocado: " + startsWithA.test("Avocado"));
        System.out.println("null: " + startsWithA.test(null));
        System.out.println("пусто: " + startsWithA.test(""));

        Function<Integer, Boolean> isPositive = LambdaBasicsTaskUtil.createIsPositiveFunction();
        System.out.println("\n5 > 0? " + isPositive.apply(5));
        System.out.println("-10 > 0? " + isPositive.apply(-10));
        System.out.println("0 > 0? " + isPositive.apply(0));

        System.out.println("\n--- Consumer: Печать элементов списка ---");
        List<String> names = List.of("Иван", "Мария", "Петр");
        Consumer<List<String>> printListElements = LambdaBasicsTaskUtil.createPrintListElementsConsumer();
        printListElements.accept(names);

        System.out.println("\n--- Supplier: Случайное число 0-99 ---");
        Supplier<Integer> randomIntSupplier = LambdaBasicsTaskUtil.createRandomIntSupplier();
        System.out.println("Случайное 1: " + randomIntSupplier.get());
        System.out.println("Случайное 2: " + randomIntSupplier.get());
    }
}
