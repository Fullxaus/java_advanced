package ru.mentee.power.refactoring.notifier.base;

public class SmsNotifierDecorator extends NotifierDecorator {
    public SmsNotifierDecorator(Notifier wrapped) { super(wrapped); }

    @Override
    public void send(String message) {
        wrapped.send(message);
        System.out.println("SMS sent: " + message);
    }
}
