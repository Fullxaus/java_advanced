package ru.mentee.power.solid.ocp.task;


@FunctionalInterface
public interface NotificationStrategy {
    void send(String message, String recipient);
}

