package ru.mentee.power.finaltask;

import ru.mentee.power.finaltask.controller.QuoteController;
import ru.mentee.power.finaltask.model.*;
import ru.mentee.power.finaltask.view.ConsoleQuoteView;

public class QuoteApp {
    private static final String QUOTES_FILE = "quotes.json";
    public static void main(String[] args) {
        System.out.println("--- Запуск Сервиса Котировок (MVC) ---");

        QuoteDataSource dataSource = new JsonQuoteDataSource(QUOTES_FILE);
        QuoteRepository repository = new InMemoryQuoteRepository();
        QuoteService service = new QuoteService(repository, dataSource);
        ConsoleQuoteView view = new ConsoleQuoteView();
        QuoteController controller = new QuoteController(service, view);

        controller.run();

        System.out.println("--- Сервис Котировок Остановлен ---");
    }
    }
