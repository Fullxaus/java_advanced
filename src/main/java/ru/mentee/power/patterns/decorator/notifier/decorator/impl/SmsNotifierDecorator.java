package ru.mentee.power.patterns.decorator.notifier.decorator.impl;

import ru.mentee.power.patterns.decorator.notifier.Notifier;
import ru.mentee.power.patterns.decorator.notifier.decorator.NotifierDecorator;

public class SmsNotifierDecorator extends NotifierDecorator {

    public SmsNotifierDecorator(Notifier wrappedNotifier) {
        super(wrappedNotifier);
    }

    @Override
    public void send(String message) {
        super.send(message);
        System.out.println("Отправка SMS: " + message);
    }
}

