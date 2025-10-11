package ru.mentee.power.exceptions.multilevel;

import java.util.List;
import java.util.logging.Logger;

public class OrderService {
    private static final Logger LOG = Logger.getLogger(OrderService.class.getName());

    private final DataSource dataSource;
    private final OrderProcessor processor;
    private final ReportGenerator reportGenerator;

    public OrderService(String inputFilePath, String outputFilePath) {
        this.dataSource = new DataSource(inputFilePath);
        this.processor = new OrderProcessor();
        this.reportGenerator = new ReportGenerator(outputFilePath);
    }

    public void processOrder() throws OrderProcessingException {
        List<String> lines;
        try {
            lines = dataSource.readLines();
        } catch (FileReadException fre) {
            String msg = "Ошибка при чтении входного файла при обработке заказа";
            LOG.severe(msg + ": " + fre.getMessage());
            throw new FileReadException(msg + ": " + fre.getMessage(), fre.getPath(), fre);
        }

        List<ProductItem> products;
        try {
            products = processor.parseProducts(lines);
        } catch (DataFormatException dfe) {
            String msg = "Ошибка формата данных при обработке заказа";
            LOG.warning(msg + ": " + dfe.getMessage());
            throw new DataFormatException(msg, dfe);
        }

        double total;
        try {
            total = processor.calculateOrderTotal(products);
        } catch (OrderException oe) {
            String msg = "Ошибка при расчете заказа";
            LOG.severe(msg + ": " + oe.getMessage());
            throw new OrderException(msg, oe);
        }

        try {
            reportGenerator.generateReport(products, total);
        } catch (FileWriteException fwe) {
            String msg = "Ошибка при создании отчета";
            LOG.severe(msg + ": " + fwe.getMessage());
            throw new FileWriteException(msg, fwe);
        }

        LOG.info("Обработка заказа завершена успешно. Итог: " + total);
    }
}

