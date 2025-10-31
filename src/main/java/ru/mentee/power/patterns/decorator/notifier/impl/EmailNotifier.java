package ru.mentee.power.patterns.decorator.notifier.impl;

import ru.mentee.power.patterns.decorator.notifier.Notifier;

public class EmailNotifier implements Notifier {
    @Override
    public void send(String message) {
        System.out.println("Отправка EMAIL: " + message);
    }
}

