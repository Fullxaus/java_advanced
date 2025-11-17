package ru.mentee.power.finaltask.model;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class InMemoryQuoteRepository implements QuoteRepository {
    private final ConcurrentMap<String, Quote> quotes = new ConcurrentHashMap<>();

    @Override
    public Optional<Quote> findByTicker(String ticker) {
        if (ticker == null) return Optional.empty();
        return Optional.ofNullable(quotes.get(ticker.toUpperCase()));
    }

    @Override
    public Map<String, Quote> findAll() {
        return Map.copyOf(quotes);
    }

    @Override
    public void saveOrUpdate(Quote quote) {
        if (quote == null || quote.getTicker() == null) return;
        quotes.put(quote.getTicker().toUpperCase(), quote);
    }
}

