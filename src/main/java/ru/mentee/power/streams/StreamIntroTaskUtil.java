package ru.mentee.power.streams;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class StreamIntroTaskUtil {

    public static List<String> processList(List<String> input) {
        if (input == null) {
            return java.util.Collections.emptyList();
        }
        return input.stream()
                .filter(Objects::nonNull)
                .filter(s -> !s.isEmpty())
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.toList());
    }
}
