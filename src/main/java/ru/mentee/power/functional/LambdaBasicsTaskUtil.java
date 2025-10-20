package ru.mentee.power.functional;

import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class LambdaBasicsTaskUtil {

    public static Predicate<String> createStartsWithAPredicate() {
        return s -> s != null && !s.isEmpty() && (s.charAt(0) == 'A' || s.charAt(0) == 'a');
        // альтернативно: s != null && s.toLowerCase().startsWith("a")
    }

    public static Function<Integer, Boolean> createIsPositiveFunction() {
        return n -> n != null && n > 0;
    }

    public static Consumer<List<String>> createPrintListElementsConsumer() {
        return list -> {
            if (list == null) return;
            for (String item : list) {
                System.out.println(item);
            }
        };
    }

    public static Supplier<Integer> createRandomIntSupplier() {
        Random rnd = new Random();
        return () -> rnd.nextInt(100);
    }
}
