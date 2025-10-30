package ru.mentee.power.solid.ocp.task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


@ExtendWith(MockitoExtension.class)
public class NotificationServiceTest {

    @Test
    @DisplayName("Должен вызывать метод send у установленной стратегии")
    void shouldCallSendOnSetStrategy() {
        // Создаем mock для стратегии уведомления

        NotificationStrategy mockStrategy;

        // Создаем экземпляр NotificationService
        NotificationService notificationService = new NotificationService();

        // Устанавливаем mock-стратегию
        notificationService.setNotificationMethod(mockStrategy);

        // Определяем тестовые данные
        String message = "Тестовое сообщение";
        String recipient = "получатель";

        // Вызываем метод notifyUser
        notificationService.notifyUser(message, recipient);

        // Проверяем, что метод send() у mockStrategy был вызван один раз с правильными аргументами
        Mockito.verify(mockStrategy, Mockito.times(1)).send(message, recipient);
    }

    @Test
    @DisplayName("Не должен падать, если стратегия не установлена, но должен вывести сообщение об ошибке")
    void shouldNotFailIfStrategyNotSetAndPrintError() {
        // Создаем экземпляр NotificationService
        NotificationService notificationService = new NotificationService();

        // Проверяем, что при вызове notifyUser без установленной стратегии выбрасывается сообщение об ошибке
        // Для этого перехватываем System.err
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(outContent));
        notificationService.notifyUser("test", "test");

        // Проверяем содержимое System.err
        String errorMessage = outContent.toString();
        Assertions.assertTrue(errorMessage.contains("Ошибка: Метод уведомления не установлен!"));
    }
}


