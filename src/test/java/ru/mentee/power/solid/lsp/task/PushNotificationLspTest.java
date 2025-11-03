package ru.mentee.power.solid.lsp.task;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class PushNotificationLspTest {

    @Test
    @DisplayName("[FIX Вариант А] PushNotification бросает NotificationException при неверном recipient")
    void fixedPushNotification_shouldThrowNotificationException_whenRecipientIsInvalid() {
        NotificationStrategy pushNotifier = new PushNotification(); // реализация throws NotificationException
        String message = "Тест";
        String invalidRecipient = "not-a-device-id";

        assertThatThrownBy(() -> pushNotifier.send(message, invalidRecipient))
                .isInstanceOf(NotificationException.class)
                .hasMessageContaining("Неверный deviceId для PUSH");
    }

    @Test
    @DisplayName("[NotificationSender] FixedNotificationSender обрабатывает NotificationException")
    void fixedNotificationSender_handlesNotificationException() {
        FixedNotificationSender sender = new FixedNotificationSender();
        NotificationStrategy pushNotifier = new PushNotification();
        String message = "Тест";

        assertDoesNotThrow(() -> sender.sendNotification(pushNotifier, message, "not-a-device-id"));
    }
}

