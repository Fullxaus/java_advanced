package ru.mentee.power.finaltask.model;

import ru.mentee.power.finaltask.model.exceptions.DataFetchingException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class QuoteService {
    private final QuoteRepository repository;
    private final QuoteDataSource dataSource;

    public QuoteService(QuoteRepository repository, QuoteDataSource dataSource) {
        this.repository = repository;
        this.dataSource = dataSource;
    }

    public void updateQuotes() throws DataFetchingException {
        System.out.println("Сервис: получение котировок...");
        List<Quote> fetched = dataSource.fetchQuotes();
        for (Quote q : fetched) {
            repository.saveOrUpdate(q);
        }
        System.out.println("Сервис: обновление завершено.");
    }

    public Optional<Quote> getQuote(String ticker) {
        return repository.findByTicker(ticker);
    }

    public Map<String, Quote> getAllQuotes() {
        return repository.findAll();
    }
}
