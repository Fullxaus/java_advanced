package ru.mentee.power.patterns.decorator.notifier.decorator.impl;

import ru.mentee.power.patterns.decorator.notifier.Notifier;
import ru.mentee.power.patterns.decorator.notifier.decorator.NotifierDecorator;

public class EncryptionDecorator extends NotifierDecorator {

    public EncryptionDecorator(Notifier wrappedNotifier) {
        super(wrappedNotifier);
    }

    private String encrypt(String message) {
        System.out.println("Шифрование сообщения...");
        return "[Encrypted] " + message;
    }

    @Override
    public void send(String message) {
        String encrypted = encrypt(message);
        super.send(encrypted);
    }
}

