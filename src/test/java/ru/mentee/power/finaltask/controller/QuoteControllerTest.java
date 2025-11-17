package ru.mentee.power.finaltask.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import ru.mentee.power.finaltask.model.Quote;
import ru.mentee.power.finaltask.model.QuoteService;
import ru.mentee.power.finaltask.model.exceptions.DataFetchingException;
import ru.mentee.power.finaltask.view.ConsoleQuoteView;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

import static org.mockito.Mockito.*;

public class QuoteControllerTest {

    private QuoteService service;
    private ConsoleQuoteView view;
    private QuoteController controller;

    @BeforeEach
    void setUp() {
        service = mock(QuoteService.class);
        view = mock(ConsoleQuoteView.class);
        controller = new QuoteController(service, view);
    }

    @Test
    void run_listCommand_shouldCallServiceGetAllAndViewDisplay() throws Exception {

        doNothing().when(service).updateQuotes();
        when(service.getAllQuotes()).thenReturn(Map.of());

        when(view.getCommand()).thenReturn("list").thenReturn("exit");

        controller.run();

        verify(service, atLeastOnce()).updateQuotes(); // вызван при старте
        verify(service, times(1)).getAllQuotes();
        verify(view, atLeastOnce()).displayMenu();
        verify(view, times(1)).displayQuotes(anyMap());
    }

    @Test
    void run_getCommand_shouldAskTickerAndDisplayQuote() throws Exception {
        doNothing().when(service).updateQuotes();
        Quote q = Quote.builder()
                .ticker("AAPL")
                .price(new BigDecimal("100"))
                .lastUpdate(LocalDateTime.now())
                .build();

        when(view.getCommand()).thenReturn("get").thenReturn("exit");
        when(view.getTickerSymbol()).thenReturn("AAPL");
        when(service.getQuote("AAPL")).thenReturn(Optional.of(q));

        controller.run();

        verify(view, times(1)).getTickerSymbol();
        verify(service, times(1)).getQuote("AAPL");
        verify(view, times(1)).displayQuote(Optional.of(q), "AAPL");
    }

    @Test
    void run_updateCommand_success_shouldShowSuccessMessage() throws Exception {

        doNothing().when(service).updateQuotes();

        when(view.getCommand()).thenReturn("update").thenReturn("exit");

        doNothing().when(service).updateQuotes();

        controller.run();

        verify(service, atLeast(2)).updateQuotes();
        verify(view, atLeastOnce()).showMessage("Инициализация: котировки загружены.");
        verify(view, atLeastOnce()).showMessage("Котировки успешно обновлены.");
    }

    @Test
    void run_updateCommand_failure_shouldShowErrorMessage() throws Exception {

        doThrow(new DataFetchingException("start fail")).when(service).updateQuotes();

        when(view.getCommand()).thenReturn("update").thenReturn("exit");

        doThrow(new DataFetchingException("update fail")).when(service).updateQuotes();

        controller.run();

        verify(view, atLeastOnce()).showError(contains("Не удалось загрузить котировки при старте"));
        verify(view, atLeastOnce()).showError(contains("Ошибка при обновлении"));
    }
}

