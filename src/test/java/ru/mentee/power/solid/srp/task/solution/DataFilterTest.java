package ru.mentee.power.solid.srp.task.solution;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

public class DataFilterTest {

    private DataFilter dataFilter = new DataFilter();

    @Test
    @DisplayName("Должен фильтровать данные по порогу")
    void shouldFilterDataByThreshold() {
        List<ReportData> inputData = List.of(
                new ReportData("Item1", 10.0),
                new ReportData("Item2", 20.0),
                new ReportData("Item3", 5.0)
        );
        double threshold = 15.0;

        List<ReportData> result = dataFilter.filterByThreshold(inputData, threshold);

        Assertions.assertThat(result)
                .hasSize(1)
                .extracting(ReportData::getName)
                .containsExactly("Item2");
    }

    @Test
    @DisplayName("Должен возвращать пустой список, если нет данных выше порога")
    void shouldReturnEmptyListWhenNoDataAboveThreshold() {
        List<ReportData> inputData = List.of(
                new ReportData("Low1", 1.0),
                new ReportData("Low2", 2.0)
        );
        double threshold = 5.0;
        List<ReportData> result = dataFilter.filterByThreshold(inputData, threshold);
        Assertions.assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("Должен возвращать все данные, если все выше порога")
    void shouldReturnAllDataWhenAllAboveThreshold() {
        List<ReportData> inputData = List.of(
                new ReportData("High1", 10.0),
                new ReportData("High2", 20.0)
        );
        double threshold = 5.0;
        List<ReportData> result = dataFilter.filterByThreshold(inputData, threshold);
        Assertions.assertThat(result).containsExactlyElementsOf(inputData);
    }
}


