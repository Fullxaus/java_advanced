package ru.mentee.power.solid.dip.task.solution.implementation;

import ru.mentee.power.solid.dip.task.solution.abstraction.ReportDestination;

import java.io.FileWriter;
import java.io.IOException;

public class FileReportDestinationImpl implements ReportDestination {
    @Override
    public void write(String content, String destinationHint) {
        String filename = destinationHint == null || destinationHint.isBlank() ? "report.html" : destinationHint;
        System.out.println("DIP Solution: Запись отчета в файл: " + filename);
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(content);
            System.out.println("DIP Solution: Запись завершена: " + filename);
        } catch (IOException e) {
            System.err.println("DIP Solution: Ошибка записи в файл: " + e.getMessage());
        }
    }
}
