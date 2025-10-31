package ru.mentee.power.solid.srp.task.solution;

import java.util.List;
import java.util.stream.Collectors;

public class DataFilter  {

    public List<ReportData> filterByThreshold(List<ReportData> data, double threshold) {
        System.out.println("DataFilter: Фильтрация данных по порогу: " + threshold);
        return data.stream()
                .filter(d -> d.getValue() > threshold)
                .collect(Collectors.toList());
    }
}

