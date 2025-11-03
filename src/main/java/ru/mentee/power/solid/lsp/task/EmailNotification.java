package ru.mentee.power.solid.lsp.task;

public class EmailNotification implements NotificationStrategy {
    @Override
    public void send(String message, String recipient) throws NotificationException {
        if (recipient == null || !recipient.contains("@")) {
            // Единообразно — бросаем проверяемое исключение
            throw new NotificationException("EMAIL: Неверный адрес получателя " + recipient);
        }
        System.out.println("Отправка EMAIL сообщения '" + message + "' получателю " + recipient);
    }
}