package ru.mentee.power.patterns.decorator.notifier.decorator.impl;

import ru.mentee.power.patterns.decorator.notifier.Notifier;
import ru.mentee.power.patterns.decorator.notifier.decorator.NotifierDecorator;

public class SignatureDecorator extends NotifierDecorator {

    public SignatureDecorator(Notifier wrappedNotifier) {
        super(wrappedNotifier);
    }

    private String addSignature(String message) {
        return message + "\n--\nС уважением, Команда";
    }

    @Override
    public void send(String message) {
        String signed = addSignature(message);
        super.send(signed);
    }
}
