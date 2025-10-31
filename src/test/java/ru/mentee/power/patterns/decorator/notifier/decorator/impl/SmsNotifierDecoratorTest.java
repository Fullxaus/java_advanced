package ru.mentee.power.patterns.decorator.notifier.decorator.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.mentee.power.patterns.decorator.notifier.Notifier;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

public class SmsNotifierDecoratorTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("SmsNotifierDecorator должен отправлять SMS и вызывать wrapped.send()")
    void shouldSendSmsAndCallWrapped() {
        Notifier mockNotifier = Mockito.mock(Notifier.class); // Мокируем базовый Notifier
        Notifier smsDecorator = new SmsNotifierDecorator(mockNotifier);
        String message = "Тест SMS декоратора";

        smsDecorator.send(message);
        verify(mockNotifier).send(message);
        assertThat(outContent.toString()).contains("Отправка SMS: " + message);
    }
}