package ru.mentee.power.solid.lsp.task;

class FixedNotificationSender {
    public void sendNotification(NotificationStrategy strategy, String msg, String recipient) {
        try {
            strategy.send(msg, recipient);
            System.out.println("Вызов send() завершен.");
        } catch (Exception e) {
            System.err.println("Неожиданная ошибка при отправке: " + e.getMessage());
        }
    }
}
