package ru.mentee.power.finaltask.model;

import java.util.Map;
import java.util.Optional;

public interface QuoteRepository {
    Optional findByTicker(String ticker);
    Map<String, Quote> findAll();
    void saveOrUpdate(Quote quote);
}
