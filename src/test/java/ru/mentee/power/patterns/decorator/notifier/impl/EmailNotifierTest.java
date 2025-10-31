package ru.mentee.power.patterns.decorator.notifier.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.mentee.power.patterns.decorator.notifier.Notifier;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.assertj.core.api.Assertions.assertThat;

public class EmailNotifierTest {
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
    @DisplayName("EmailNotifier должен отправлять EMAIL сообщение")
    void shouldSendEmail() {
        Notifier notifier = new EmailNotifier();
        String message = "Тестовое email сообщение";
        notifier.send(message);
        assertThat(outContent.toString()).contains("Отправка EMAIL: " + message);
    }
}
