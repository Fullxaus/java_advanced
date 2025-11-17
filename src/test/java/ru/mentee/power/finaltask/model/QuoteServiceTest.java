package ru.mentee.power.finaltask.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.mentee.power.finaltask.model.exceptions.DataFetchingException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class QuoteServiceTest {

    private QuoteRepository repository;
    private QuoteDataSource dataSource;
    private QuoteService service;

    @BeforeEach
    void setUp() {
        repository = mock(QuoteRepository.class);
        dataSource = mock(QuoteDataSource.class);
        service = new QuoteService(repository, dataSource);
    }

    @Test
    void updateQuotes_success_shouldSaveAllToRepository() throws Exception {
        Quote q1 = Quote.builder().ticker("AAPL").price(new BigDecimal("1")).lastUpdate(LocalDateTime.now()).build();
        Quote q2 = Quote.builder().ticker("MSFT").price(new BigDecimal("2")).lastUpdate(LocalDateTime.now()).build();

        when(dataSource.fetchQuotes()).thenReturn(List.of(q1, q2));

        service.updateQuotes();

        verify(repository, times(1)).saveOrUpdate(q1);
        verify(repository, times(1)).saveOrUpdate(q2);
    }

    @Test
    void updateQuotes_dataSourceThrows_shouldPropagateException() throws Exception {
        when(dataSource.fetchQuotes()).thenThrow(new DataFetchingException("fail"));

        assertThrows(DataFetchingException.class, () -> service.updateQuotes());
    }

    @Test
    void getQuote_shouldDelegateToRepository() {
        when(repository.findByTicker("AAPL")).thenReturn(Optional.of(Quote.builder().ticker("AAPL").build()));
        Optional<Quote> res = service.getQuote("AAPL");
        assertTrue(res.isPresent());
        assertEquals("AAPL", res.get().getTicker());
        verify(repository, times(1)).findByTicker("AAPL");
    }

    @Test
    void getAllQuotes_shouldReturnRepositoryMap() {
        Map<String, Quote> map = Map.of("A", Quote.builder().ticker("A").build());
        when(repository.findAll()).thenReturn(map);
        Map<String, Quote> r = service.getAllQuotes();
        assertEquals(1, r.size());
        assertTrue(r.containsKey("A"));
    }
}

