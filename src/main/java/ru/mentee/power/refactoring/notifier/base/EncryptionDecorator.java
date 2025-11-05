package ru.mentee.power.refactoring.notifier.base;

public class EncryptionDecorator extends NotifierDecorator {
    public EncryptionDecorator(Notifier wrapped) { super(wrapped); }

    private String encrypt(String msg) {
        return "[ENCRYPTED]" + msg;
    }

    @Override
    public void send(String message) {
        String encrypted = encrypt(message);
        wrapped.send(encrypted);
    }
}
