package ru.mentee.power.patterns.adapter.client;

import ru.mentee.power.patterns.adapter.printer.ModernPrinter;

public class PrintingServiceClient {
    public void printReport(ModernPrinter printer, String reportData) {
        System.out.println("Клиент: Подготовка к печати отчета...");
        printer.printDocument(reportData);
        System.out.println("Клиент: Отчет отправлен на печать.");
    }
}
