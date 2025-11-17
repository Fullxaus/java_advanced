package ru.mentee.power.finaltask.view;

import ru.mentee.power.finaltask.model.Quote;

import java.math.RoundingMode;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class ConsoleQuoteView {
    private final Scanner scanner = new Scanner(System.in);
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void displayQuotes(Map<String, Quote> quotes) {
        if (quotes == null || quotes.isEmpty()) {
            System.out.println("[Нет котировок]");
            return;
        }
        String fmt = "| %-8s | %12s | %-19s |%n";
        String line = "+----------+--------------+---------------------+";
        System.out.println(line);
        System.out.printf(fmt, "TICKER", "PRICE", "LAST UPDATE");
        System.out.println(line);
        quotes.values().stream()
                .sorted((a, b) -> a.getTicker().compareToIgnoreCase(b.getTicker()))
                .forEach(q -> {
                    String price = q.getPrice() == null ? "-" : q.getPrice().setScale(2, RoundingMode.HALF_UP).toString();
                    String lu = q.getLastUpdate() == null ? "-" : q.getLastUpdate().format(dtf);
                    System.out.printf(fmt, q.getTicker(), price, lu);
                });
        System.out.println(line);
    }

    public void displayQuote(Optional<Quote> quoteOpt, String ticker) {
        if (quoteOpt == null || quoteOpt.isEmpty()) {
            System.out.println("Котировка для '" + ticker + "' не найдена.");
            return;
        }
        Quote q = quoteOpt.get();
        System.out.println("Котировка: " + q.getTicker());
        System.out.println("  Цена: " + (q.getPrice() == null ? "-" : q.getPrice()));
        System.out.println("  Последнее обновление: " + (q.getLastUpdate() == null ? "-" : q.getLastUpdate().format(dtf)));
    }

    public void displayMenu() {
        System.out.println();
        System.out.println("Команды:");
        System.out.println("  list    - Показать все котировки");
        System.out.println("  get     - Показать котировку по тикеру");
        System.out.println("  update  - Обновить котировки из файла");
        System.out.println("  exit    - Выйти");
        System.out.print("Введите команду: ");
    }

    public String getCommand() {
        String line = scanner.nextLine();
        return line == null ? "" : line.trim().toLowerCase();
    }

    public String getTickerSymbol() {
        System.out.print("Введите тикер: ");
        String t = scanner.nextLine();
        return t == null ? "" : t.trim().toUpperCase();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showError(String error) {
        System.err.println("Ошибка: " + error);
    }
}
