package ru.mentee.power.solid.srp.task.solution;

import java.util.List;

public interface DataFilterInterface {
    List<ReportData> filterByThreshold(List<ReportData> data, double threshold);
}
