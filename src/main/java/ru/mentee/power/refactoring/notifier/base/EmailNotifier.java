package ru.mentee.power.refactoring.notifier.base;

public class EmailNotifier implements Notifier {
    @Override
    public void send(String message) {
        System.out.println("Email sent: " + message);
    }
}
