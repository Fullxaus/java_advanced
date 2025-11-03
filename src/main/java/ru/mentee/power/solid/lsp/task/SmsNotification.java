package ru.mentee.power.solid.lsp.task;

public class SmsNotification implements NotificationStrategy {
    @Override
    public void send(String message, String recipient) throws NotificationException {
        if (recipient == null || !recipient.matches("\\+?[0-9]{10,15}")) {
            throw new NotificationException("SMS: Неверный номер телефона " + recipient);
        }
        System.out.println("Отправка SMS сообщения '" + message + "' получателю " + recipient);
    }
}
