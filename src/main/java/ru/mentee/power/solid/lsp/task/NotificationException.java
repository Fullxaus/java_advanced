package ru.mentee.power.solid.lsp.task;

public class NotificationException extends Exception {
    public NotificationException(String message) {
        super(message);
    }

    public NotificationException(String message, Throwable cause) {
        super(message, cause);
    }
}
