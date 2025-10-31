package ru.mentee.power.solid.ocp.task;


class EmailNotification implements NotificationStrategy {
    @Override
    public void send(String message, String recipient) {
        System.out.println("Отправка EMAIL сообщения '" + message + "' получателю " + recipient);

    }
}
