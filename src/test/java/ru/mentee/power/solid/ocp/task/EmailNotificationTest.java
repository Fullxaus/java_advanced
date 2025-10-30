package ru.mentee.power.solid.ocp.task;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.assertj.core.api.Assertions.assertThat;

public class EmailNotificationTest {
    private final EmailNotification emailNotification = new EmailNotification();
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
    @DisplayName("Должен отправлять Email уведомление")
    void shouldSendEmailNotification() {
        String message = "Тест Email";
        String recipient = "test@example.com";
        emailNotification.send(message, recipient);
        // @TODO: Проверьте, что консольный вывод содержит корректное сообщение об отправке Email
        assertThat(outContent.toString()).contains("Отправка EMAIL сообщения '" + message + "' получателю " + recipient);
    }
}
