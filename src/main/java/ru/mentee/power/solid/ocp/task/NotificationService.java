package ru.mentee.power.solid.ocp.task;

public class NotificationService {
    private NotificationStrategy strategy;

    public void setNotificationMethod(NotificationStrategy strategy) {
        this.strategy = strategy;
    }

    public void notifyUser(String message, String recipient) {
        if (strategy == null) {
            System.err.println("Ошибка: Метод уведомления не установлен!");
            return;
        }

        strategy.send(message, recipient);
    }

    public static void main(String[] args) {
        NotificationService service = new NotificationService();
        String msg = "Ваш заказ #123 готов";
        String emailRecipient = "user@example.com";
        String phoneRecipient = "+79991234567";


        service.setNotificationMethod(new EmailNotification());
        service.notifyUser(msg, emailRecipient);

        service.setNotificationMethod(new SmsNotification());
        service.notifyUser(msg, phoneRecipient);

        NotificationService notificationService = new NotificationService();
        String message = "Ваш заказ #123 готов";
        String emailrecipient = "user@example.com";
        String phonerecipient = "+79991234567";
        String telegramRecipient = "@telegram_user";

        service.setNotificationMethod(new EmailNotification());
        service.notifyUser(message, emailrecipient);

        service.setNotificationMethod(new SmsNotification());
        service.notifyUser(message, phonerecipient);

        service.setNotificationMethod(new TelegramNotification());
        service.notifyUser(message, telegramRecipient);

    }
}
