package ru.mentee.power.solid.ocp.task;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.assertj.core.api.Assertions.assertThat;

public class SmsNotificationTest {
    private final SmsNotification smsNotification = new SmsNotification();
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
    @DisplayName("Должен отправлять SMS уведомление")
    void shouldSendSmsNotification() {
        String message = "Тест SMS";
        String recipient = "+1234567890";
        smsNotification.send(message, recipient);
        // @TODO: Проверьте, что консольный вывод содержит корректное сообщение об отправке SMS
        assertThat(outContent.toString()).contains("Отправка SMS сообщения '" + message + "' получателю " + recipient);
    }
}
