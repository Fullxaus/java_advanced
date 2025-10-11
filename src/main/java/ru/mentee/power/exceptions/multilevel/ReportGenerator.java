package ru.mentee.power.exceptions.multilevel;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.logging.Logger;

public class ReportGenerator {
    private static final Logger LOG = Logger.getLogger(ReportGenerator.class.getName());
    private final String reportPath;

    public ReportGenerator(String reportPath) {
        this.reportPath = reportPath;
    }

    public void generateReport(List<ProductItem> products, double totalPrice) throws FileWriteException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(reportPath), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            writer.write("Отчет по заказу");
            writer.newLine();
            writer.write("-------------------------------------------------");
            writer.newLine();
            writer.write(String.format("%-20s %8s %12s %12s", "Товар", "Кол-во", "Цена", "Сумма"));
            writer.newLine();
            writer.write("-------------------------------------------------");
            writer.newLine();
            for (ProductItem p : products) {
                writer.write(String.format("%-20s %8d %12.2f %12.2f", p.getName(), p.getQuantity(), p.getPrice(), p.totalPrice()));
                writer.newLine();
            }
            writer.write("-------------------------------------------------");
            writer.newLine();
            writer.write(String.format("Итого: %.2f", totalPrice));
            writer.newLine();
        } catch (IOException e) {
            String msg = "Ошибка записи отчета в файл: " + reportPath;
            LOG.severe(msg + " - " + e.getMessage());
            throw new FileWriteException(msg, e);
        }
    }
}

