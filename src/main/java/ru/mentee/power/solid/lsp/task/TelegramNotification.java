package ru.mentee.power.solid.lsp.task;

public class TelegramNotification implements NotificationStrategy {
    @Override
    public void send(String message, String recipient) throws NotificationException {
        if (recipient == null || !recipient.startsWith("@")) {
            throw new NotificationException("TELEGRAM: Неверный username " + recipient);
        }
        System.out.println("Отправка TELEGRAM сообщения '" + message + "' получателю " + recipient);
    }
}
