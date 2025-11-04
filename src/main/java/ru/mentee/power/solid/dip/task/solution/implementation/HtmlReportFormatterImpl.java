package ru.mentee.power.solid.dip.task.solution.implementation;

import ru.mentee.power.solid.dip.task.solution.abstraction.ReportFormatter;

import java.util.List;

public class HtmlReportFormatterImpl implements ReportFormatter {
    @Override
    public String format(List<String> data) {
        System.out.println("DIP Solution: Форматирование в HTML...");
        StringBuilder sb = new StringBuilder();
        sb.append("<html><head><meta charset=\"UTF-8\"><title>Отчет</title></head><body>");
        sb.append("<h1>Отчет</h1>");
        sb.append("<ul>");
        for (String item : data) {
            sb.append("<li>").append(escapeHtml(item)).append("</li>");
        }
        sb.append("</ul>");
        sb.append("</body></html>");
        return sb.toString();
    }

    // Простая экранизация для демонстрации
    private String escapeHtml(String s) {
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }
}
