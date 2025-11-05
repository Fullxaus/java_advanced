package ru.mentee.power.refactoring.notifier.base;

public class SignatureDecorator extends NotifierDecorator {
    public SignatureDecorator(Notifier wrapped) { super(wrapped); }

    private String sign(String msg) {
        return msg + " [Signed]";
    }

    @Override
    public void send(String message) {
        String signed = sign(message);
        wrapped.send(signed);
    }
}
