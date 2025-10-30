package ru.mentee.power.solid.ocp.task;

public class SmsNotification implements NotificationStrategy {
    @Override
    public void send(String message, String recipient) {
        System.out.println("Отправка SMS сообщения '" + message + "' получателю " + recipient);
        // Логика отправки SMS
    }
}
