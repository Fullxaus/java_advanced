package ru.mentee.power.solid.ocp.task;

public class NotificationService {
    private NotificationStrategy strategy;

    // Метод для установки стратегии (может быть и через конструктор)
    public void setNotificationMethod(NotificationStrategy strategy) {
        this.strategy = strategy;
    }

    // Метод, использующий стратегию
    public void notifyUser(String message, String recipient) {
        if (strategy == null) {
            System.err.println("Ошибка: Метод уведомления не установлен!");
            return;
        }
        // Делегируем отправку выбранной стратегии
        strategy.send(message, recipient);
    }

    public static void main(String[] args) {
        NotificationService service = new NotificationService();
        String msg = "Ваш заказ #123 готов";
        String emailRecipient = "user@example.com";
        String phoneRecipient = "+79991234567";

        // Уведомляем по Email
        service.setNotificationMethod(new EmailNotification());
        service.notifyUser(msg, emailRecipient);

        // Уведомляем по SMS
        service.setNotificationMethod(new SmsNotification());
        service.notifyUser(msg, phoneRecipient);

        NotificationService notificationService = new NotificationService();
        String message = "Ваш заказ #123 готов";
        String emailrecipient = "user@example.com";
        String phonerecipient = "+79991234567";
        String telegramRecipient = "@telegram_user";

        // Уведомляем по Email
        service.setNotificationMethod(new EmailNotification());
        service.notifyUser(message, emailrecipient);

        // Уведомляем по SMS
        service.setNotificationMethod(new SmsNotification());
        service.notifyUser(message, phonerecipient);

        // Уведомляем по Telegram
        service.setNotificationMethod(new TelegramNotification());
        service.notifyUser(message, telegramRecipient);

    }
}
