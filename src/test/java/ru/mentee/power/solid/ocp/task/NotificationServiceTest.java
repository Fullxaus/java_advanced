package ru.mentee.power.solid.ocp.task;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

public class NotificationServiceTest {

    @Test
    @DisplayName("Должен вызывать метод send у установленной стратегии")
    void shouldCallSendOnSetStrategy() {
        NotificationService notificationService = new NotificationService();
        NotificationStrategy mockStrategy = mock(NotificationStrategy.class);
        notificationService.setNotificationMethod(mockStrategy);

        String message = "Тестовое сообщение";
        String recipient = "получатель";
        notificationService.notifyUser(message, recipient);

        verify(mockStrategy, times(1)).send(message, recipient);
    }

    @Test
    @DisplayName("Не должен падать, если стратегия не установлена, но должен вывести сообщение об ошибке")
    void shouldNotFailIfStrategyNotSetAndPrintError() {
        NotificationService notificationService = new NotificationService();

        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        PrintStream originalErr = System.err;
        System.setErr(new PrintStream(errContent));

        try {
            // вызов не должен бросать исключение
            notificationService.notifyUser("test message", "test recipient");
        } finally {
            System.setErr(originalErr);
        }

        String err = errContent.toString();
        assertFalse(err.isBlank(), "Ожидалось сообщение об ошибке в System.err, но поток пуст");
    }
}



