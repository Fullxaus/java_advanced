package ru.mentee.power.finaltask.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class InMemoryQuoteRepositoryTest {

    private InMemoryQuoteRepository repository;

    @BeforeEach
    void setUp() {
        repository = new InMemoryQuoteRepository();
    }

    @Test
    void saveAndFindByTicker_shouldStoreAndReturnQuote() {
        Quote q = Quote.builder()
                .ticker("AAPL")
                .price(new BigDecimal("150.25"))
                .lastUpdate(LocalDateTime.of(2023, 10, 1, 12, 0))
                .build();

        repository.saveOrUpdate(q);

        Optional<Quote> fetched = repository.findByTicker("AAPL");
        assertTrue(fetched.isPresent());
        assertEquals("AAPL", fetched.get().getTicker());
        assertEquals(0, new BigDecimal("150.25").compareTo(fetched.get().getPrice()));
    }

    @Test
    void saveOrUpdate_shouldUpdateExistingQuote() {
        Quote q1 = Quote.builder()
                .ticker("MSFT")
                .price(new BigDecimal("300.00"))
                .lastUpdate(LocalDateTime.now())
                .build();
        repository.saveOrUpdate(q1);

        Quote q2 = Quote.builder()
                .ticker("MSFT")
                .price(new BigDecimal("305.50"))
                .lastUpdate(LocalDateTime.now().plusMinutes(5))
                .build();
        repository.saveOrUpdate(q2);

        Optional<Quote> fetched = repository.findByTicker("msft"); // lower-case input
        assertTrue(fetched.isPresent());
        assertEquals(0, new BigDecimal("305.50").compareTo(fetched.get().getPrice()));
    }

    @Test
    void findAll_shouldReturnAllStoredQuotes() {
        repository.saveOrUpdate(Quote.builder().ticker("A").price(new BigDecimal("1")).lastUpdate(LocalDateTime.now()).build());
        repository.saveOrUpdate(Quote.builder().ticker("B").price(new BigDecimal("2")).lastUpdate(LocalDateTime.now()).build());

        Map<String, Quote> all = repository.findAll();
        assertEquals(2, all.size());
        assertTrue(all.containsKey("A"));
        assertTrue(all.containsKey("B"));
    }

    @Test
    void findByTicker_nonExisting_shouldReturnEmpty() {
        Optional<Quote> fetched = repository.findByTicker("UNKNOWN");
        assertTrue(fetched.isEmpty());
    }
}

