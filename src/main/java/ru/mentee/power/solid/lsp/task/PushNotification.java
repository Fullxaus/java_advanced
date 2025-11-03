package ru.mentee.power.solid.lsp.task;

public class PushNotification implements NotificationStrategy {
    @Override
    public void send(String message, String recipient) throws NotificationException {
        if (recipient == null || !recipient.startsWith("device-")) {
            throw new NotificationException("Неверный deviceId для PUSH: " + recipient);
        }
        System.out.println("Отправка PUSH сообщения '" + message + "' на устройство " + recipient);
    }
}

