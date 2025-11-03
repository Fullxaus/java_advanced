package ru.mentee.power.solid.lsp.task;

import ru.mentee.power.solid.lsp.task.NotificationException;

public interface NotificationStrategy {
    /**
     * Попытка отправки уведомления.
     * Может бросать NotificationException при ошибках, связанных с валидацией или доставкой.
     */
    void send(String message, String recipient) throws NotificationException;
}
