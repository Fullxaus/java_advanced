package ru.mentee.power.solid.srp.task.solution;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

public class ReportFormatterTest {

    private final ReportFormatter reportFormatter = new ReportFormatter();

    @Test
    @DisplayName("Должен корректно форматировать список данных в строку")
    void shouldFormatDataToString() {
        List<ReportData> data = List.of(
                new ReportData("Продукт А", 120.755),
                new ReportData("Продукт Б", 99.0)
        );
        String expectedReport = "--- Отчет ---\n" +
                "Продукт А: 120.76\n" +
                "Продукт Б: 99.00\n" +
                "--- Конец отчета ---";

        String actualReport = reportFormatter.formatToString(data);

        Assertions.assertThat(actualReport).isEqualTo(expectedReport);
    }

    @Test
    @DisplayName("Должен корректно форматировать пустой список")
    void shouldFormatEmptyList() {
        List<ReportData> data = List.of();
        String expectedReport = "--- Отчет ---\n" +
                "--- Конец отчета ---";
        String actualReport = reportFormatter.formatToString(data);
        Assertions.assertThat(actualReport).isEqualTo(expectedReport);
    }
}

