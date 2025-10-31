package ru.mentee.power.solid.srp.task.solution;

import java.util.List;

public class ReportFormatter {

    public String formatToString(List<ReportData> filteredData) {
        System.out.println("ReportFormatter: Форматирование данных в строку...");
        StringBuilder sb = new StringBuilder("--- Отчет ---\n");
        for (ReportData item : filteredData) {
            sb.append(item.getName())
                    .append(": ")
                    .append(String.format("%.2f", item.getValue()))
                    .append("\n");
        }
        sb.append("--- Конец отчета ---");
        return sb.toString();

    }
}

