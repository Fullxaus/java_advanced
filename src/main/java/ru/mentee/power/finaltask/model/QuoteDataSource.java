package ru.mentee.power.finaltask.model;

import ru.mentee.power.finaltask.model.exceptions.DataFetchingException;

import java.util.List;

public interface QuoteDataSource {
    List fetchQuotes() throws DataFetchingException;
}
