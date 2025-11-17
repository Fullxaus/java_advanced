package ru.mentee.power.finaltask.controller;

import ru.mentee.power.finaltask.model.QuoteService;
import ru.mentee.power.finaltask.model.exceptions.DataFetchingException;
import ru.mentee.power.finaltask.view.ConsoleQuoteView;

import java.util.Map;

public class QuoteController {
    private final QuoteService service;
    private final ConsoleQuoteView view;

    public QuoteController(QuoteService service, ConsoleQuoteView view) {
        this.service = service;
        this.view = view;
    }

    public void run() {
        // Попытка загрузки при старте
        try {
            service.updateQuotes();
            view.showMessage("Инициализация: котировки загружены.");
        } catch (DataFetchingException e) {
            view.showError("Не удалось загрузить котировки при старте: " + e.getMessage());
        }

        boolean running = true;
        while (running) {
            view.displayMenu();
            String cmd = view.getCommand();
            switch (cmd) {
                case "list":
                    Map<String, ?> all = service.getAllQuotes();
                    view.displayQuotes((Map) all);
                    break;
                case "get":
                    String ticker = view.getTickerSymbol();
                    view.displayQuote(service.getQuote(ticker), ticker);
                    break;
                case "update":
                    try {
                        service.updateQuotes();
                        view.showMessage("Котировки успешно обновлены.");
                    } catch (DataFetchingException e) {
                        view.showError("Ошибка при обновлении: " + e.getMessage());
                    }
                    break;
                case "exit":
                    running = false;
                    view.showMessage("Выход из приложения.");
                    break;
                default:
                    view.showError("Неизвестная команда: " + cmd);
            }
        }
    }
}
