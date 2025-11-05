package ru.mentee.power.refactoring.notifier.base;

public class EmailNotifierFactory extends NotifierFactory {

    protected Notifier getBaseNotifier() {
        return new EmailNotifier();
    }
}
