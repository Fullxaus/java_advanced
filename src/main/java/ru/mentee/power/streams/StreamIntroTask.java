package ru.mentee.power.streams;

import java.util.Arrays;
import java.util.List;

public class StreamIntroTask {

    public static void main(String[] args) {
        List<String> data = Arrays.asList(
                "  Java ", " Kotlin ", "", "  Scala", " Groovy ", " Clojure", " ", "   ", null
        );

        System.out.println("Исходные данные: " + data);


        List<String> processedData = StreamIntroTaskUtil.processList(data);

        System.out.println("Обработанные данные (Stream API): " + processedData);


        java.util.List<String> imperativeResult = new java.util.ArrayList<>();
        for (String str : data) {
            if (str == null) continue;
            if (str.isEmpty()) continue;
            String trimmed = str.trim();
            if (trimmed.isEmpty()) continue;
            imperativeResult.add(trimmed.toUpperCase());
        }
        java.util.Collections.sort(imperativeResult);
        System.out.println("Обработанные данные (Императивно): " + imperativeResult);
    }
}
