package ru.mentee.power.solid.dip.task.solution.service;

import ru.mentee.power.solid.dip.task.solution.abstraction.DataSource;
import ru.mentee.power.solid.dip.task.solution.abstraction.ReportDestination;
import ru.mentee.power.solid.dip.task.solution.abstraction.ReportFormatter;

import java.util.List;

public class ReportService {
    private final DataSource dataSource;
    private final ReportFormatter reportFormatter;
    private final ReportDestination reportDestination;

    public ReportService(DataSource dataSource, ReportFormatter reportFormatter, ReportDestination reportDestination) {
        this.dataSource = dataSource;
        this.reportFormatter = reportFormatter;
        this.reportDestination = reportDestination;
    }

    public void generateAndWriteReport(String destinationHint) {
        System.out.println("--- Генерация отчета (DIP Solution) ---");
        List<String> data = dataSource.getData();
        String reportContent = reportFormatter.format(data);
        reportDestination.write(reportContent, destinationHint);
        System.out.println("--- Отчет сгенерирован и сохранен (DIP Solution) --- ");
    }
}
