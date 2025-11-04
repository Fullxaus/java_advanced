package ru.mentee.power.solid.dip.task.solution;

import ru.mentee.power.solid.dip.task.solution.abstraction.DataSource;
import ru.mentee.power.solid.dip.task.solution.abstraction.ReportDestination;
import ru.mentee.power.solid.dip.task.solution.abstraction.ReportFormatter;
import ru.mentee.power.solid.dip.task.solution.implementation.ConsoleReportDestinationImpl;
import ru.mentee.power.solid.dip.task.solution.implementation.DatabaseDataSourceImpl;
import ru.mentee.power.solid.dip.task.solution.implementation.FileReportDestinationImpl;
import ru.mentee.power.solid.dip.task.solution.implementation.HtmlReportFormatterImpl;
import ru.mentee.power.solid.dip.task.solution.service.ReportService;

public class DipDemo {
    public static void main(String[] args) {
        DataSource dbDataSource = new DatabaseDataSourceImpl();
        ReportFormatter htmlFormatter = new HtmlReportFormatterImpl();
        ReportDestination fileDestination = new FileReportDestinationImpl();

        ReportService reportService = new ReportService(dbDataSource, htmlFormatter, fileDestination);
        reportService.generateAndWriteReport("dip_report.html");

        // Демонстрация гибкости: вывести тот же отчет в консоль
        ReportDestination consoleDestination = new ConsoleReportDestinationImpl();
        ReportService reportServiceConsole = new ReportService(dbDataSource, htmlFormatter, consoleDestination);
        reportServiceConsole.generateAndWriteReport("console_hint");
    }
}
