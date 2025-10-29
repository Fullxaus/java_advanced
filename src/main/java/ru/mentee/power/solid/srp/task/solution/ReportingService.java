package ru.mentee.power.solid.srp.task.solution;

import java.util.List;

public class ReportingService {
    private final DataFilterInterface dataFilter;
    private final ReportFormatter reportFormatter;
    private final ReportPrinter reportPrinter;

    public ReportingService(DataFilterInterface dataFilter, ReportFormatter reportFormatter, ReportPrinter reportPrinter) {
        this.dataFilter = dataFilter;
        this.reportFormatter = reportFormatter;
        this.reportPrinter = reportPrinter;
    }


    /**
     * Генерирует и выводит отчет на основе предоставленных данных и порога.
     * @param allData Список всех данных.
     * @param threshold Порог для фильтрации.
     */
    public void generateAndPrintReport(List<ReportData> allData, double threshold) {
        List<ReportData> filtered = dataFilter.filterByThreshold(allData, threshold);
        String report = reportFormatter.formatToString(filtered);
        reportPrinter.printToConsole(report);
        System.out.println("ReportingService: Отчет сгенерирован и выведен.");
    }

    // Демонстрационный main метод
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


