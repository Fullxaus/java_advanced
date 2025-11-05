package ru.mentee.power.refactoring.notifier.base;

public abstract class NotifierDecorator implements Notifier {
    protected final Notifier wrapped;

    protected NotifierDecorator(Notifier wrapped) {
        this.wrapped = wrapped;
    }
}
