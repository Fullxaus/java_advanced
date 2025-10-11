package ru.mentee.power.exceptions.multilevel;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Logger;
import java.io.IOException;
import java.nio.file.StandardOpenOption;

public class Main {
    private static final Logger LOG = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        String input = "order_input.txt";
        String output = "order_report.txt";

        if (args.length >= 1) input = args[0];
        if (args.length >= 2) output = args[1];

        // Для демонстрации создадим пример входного файла, если его нет
        try {
            if (!Files.exists(Paths.get(input))) {
                List<String> sample = List.of(
                        "Яблоки;10;2.5",
                        "Груши;5;3.0",
                        "Шоколад;2;150.99"
                        // попробуйте добавить некорректные строки для проверки обработчиков
                );
                Files.write(Paths.get(input), sample, StandardOpenOption.CREATE);
                LOG.info("Создан пример входного файла: " + input);
            }
        } catch (IOException e) {
            LOG.severe("Не удалось создать пример входного файла: " + e.getMessage());
        }

        OrderService service = new OrderService(input, output);
        try {
            service.processOrder();
            LOG.info("Отчет создан: " + output);
        } catch (OrderProcessingException e) {
            // Демонстрация: распечатаем всю цепочку причин и сообщение
            LOG.severe("Ошибка при обработке заказа: " + e.getMessage());
            Throwable cause = e.getCause();
            while (cause != null) {
                LOG.severe("Причина: " + cause.getClass().getSimpleName() + " - " + cause.getMessage());
                cause = cause.getCause();
            }
        }
    }
}

