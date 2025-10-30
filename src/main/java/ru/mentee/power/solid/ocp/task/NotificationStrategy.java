package ru.mentee.power.solid.ocp.task;

// 1. Интерфейс стратегии уведомления
@FunctionalInterface
public interface NotificationStrategy {
    void send(String message, String recipient);
}

