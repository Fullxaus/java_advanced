package ru.mentee.power.patterns.adapter;

import ru.mentee.power.patterns.adapter.adapter.PrinterAdapter;
import ru.mentee.power.patterns.adapter.client.PrintingServiceClient;
import ru.mentee.power.patterns.adapter.legacy.LegacyPrinter;
import ru.mentee.power.patterns.adapter.printer.ModernPrinter;

public class AdapterDemo {
    public static void main(String[] args) {
        LegacyPrinter legacyPrinter = new LegacyPrinter();
        ModernPrinter modernPrinter = new PrinterAdapter(legacyPrinter);
        PrintingServiceClient client = new PrintingServiceClient();

        String report = "Это данные отчета для современной системы.";
        client.printReport(modernPrinter, report);
    }
}
