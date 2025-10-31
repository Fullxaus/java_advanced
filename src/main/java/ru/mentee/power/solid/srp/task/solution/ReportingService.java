package ru.mentee.power.solid.srp.task.solution;

import java.util.List;

public class ReportingService {
    private final DataFilter dataFilter;
    private final ReportFormatter reportFormatter;
    private final ReportPrinter reportPrinter;

    public ReportingService(DataFilter dataFilter, ReportFormatter reportFormatter, ReportPrinter reportPrinter) {
        this.dataFilter = dataFilter;
        this.reportFormatter = reportFormatter;
        this.reportPrinter = reportPrinter;
    }

    public void generateAndPrintReport(List<ReportData> allData, double threshold) {

        List<ReportData> filtered = dataFilter.filterByThreshold(allData, threshold);
        String report = reportFormatter.formatToString(filtered);
        reportPrinter.printToConsole(report);
        System.out.println("ReportingService: Отчет сгенерирован и выведен.");
    }

    public static void main(String[] args) {
        List<ReportData> sampleData = List.of(
                new ReportData("Альфа SRP", 150.5),
                new ReportData("Бета SRP", 80.0),
                new ReportData("Гамма SRP", 210.99)
        );


        DataFilter filter = new DataFilter();
        ReportFormatter formatter = new ReportFormatter();
        ReportPrinter printer = new ReportPrinter();


        ReportingService service = new ReportingService(filter, formatter, printer);
        service.generateAndPrintReport(sampleData, 100.0);

    }
}


