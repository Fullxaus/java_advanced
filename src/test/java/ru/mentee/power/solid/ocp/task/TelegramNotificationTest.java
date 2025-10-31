package ru.mentee.power.solid.ocp.task;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.assertj.core.api.Assertions.assertThat;

public class TelegramNotificationTest {
    private final TelegramNotification telegramNotification = new TelegramNotification();
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
    @DisplayName("Должен отправлять Telegram уведомление")
    void shouldSendTelegramNotification() {
        String message = "Тест Telegram";
        String recipient = "@testuser";
        telegramNotification.send(message, recipient);
        // @TODO: Проверьте, что консольный вывод содержит корректное сообщение об отправке Telegram
        assertThat(outContent.toString()).contains("Отправка TELEGRAM сообщения '" + message + "' пользователю " + recipient);
    }
}
