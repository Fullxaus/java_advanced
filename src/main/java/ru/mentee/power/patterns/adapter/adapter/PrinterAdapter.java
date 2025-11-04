package ru.mentee.power.patterns.adapter.adapter;

import ru.mentee.power.patterns.adapter.legacy.LegacyPrinter;
import ru.mentee.power.patterns.adapter.printer.ModernPrinter;

public class PrinterAdapter implements ModernPrinter {
    private final LegacyPrinter legacyPrinter;

    public PrinterAdapter(LegacyPrinter legacyPrinter) {
        this.legacyPrinter = legacyPrinter;
    }

    @Override
    public void printDocument(String content) {
        char[] data = content.toCharArray();
        legacyPrinter.printLegacyFormat(data);
    }
}
