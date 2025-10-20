package ru.mentee.power.streams;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class StreamIntroTaskUtilTest {

    @Test
    @DisplayName("processList должен корректно обрабатывать список строк")
    void processListShouldWorkCorrectly() {
        List<String> input = Arrays.asList(
                "  Java ", " Kotlin ", "", "  Scala", " Groovy ", " Clojure", " ", "   "
        );

        List<String> result = StreamIntroTaskUtil.processList(input);

        // Ожидаем: CLOJURE, GROOVY, JAVA, KOTLIN, SCALA (в верхнем регистре и отсортированные)
        assertThat(result).containsExactly("CLOJURE", "GROOVY", "JAVA", "KOTLIN", "SCALA");
    }

    @Test
    @DisplayName("processList должен возвращать пустой список для входного списка из пустых строк")
    void processListShouldReturnEmptyListForEmptyStrings() {
        List<String> input = Arrays.asList("", " ", "   ");

        List<String> result = StreamIntroTaskUtil.processList(input);

        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("processList должен правильно обрабатывать null-значения")
    void processListShouldHandleNullValues() {
        List<String> input = Arrays.asList(null, "  a ", null, " ");

        List<String> result = StreamIntroTaskUtil.processList(input);

        // Ожидаем только "A"
        assertThat(result).containsExactly("A");
    }
}
