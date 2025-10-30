package ru.mentee.power.solid.ocp.task;

public class TelegramNotification implements NotificationStrategy {

    @Override
    public void send(String message, String recipient) {
        System.out.println("Отправка TELEGRAM сообщения '" + message + "' пользователю " + recipient);
    }
}

