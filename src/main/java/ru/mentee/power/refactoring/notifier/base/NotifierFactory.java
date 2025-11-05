package ru.mentee.power.refactoring.notifier.base;

import java.util.Set;

public class NotifierFactory {
    public static final String OPT_SMS = "SMS";
    public static final String OPT_ENCRYPT = "ENCRYPT";
    public static final String OPT_SIGN = "SIGN";

    public Notifier createNotifier(Set<String> options) {
        Notifier notifier = new EmailNotifier();
        if (options == null || options.isEmpty()) return notifier;

        // Порядок важен: ENCRYPT -> SIGN -> SMS (ENCRYPT ближе к базе)
        if (options.contains(OPT_ENCRYPT)) {
            notifier = new EncryptionDecorator(notifier);
        }
        if (options.contains(OPT_SIGN)) {
            notifier = new SignatureDecorator(notifier);
        }
        if (options.contains(OPT_SMS)) {
            notifier = new SmsNotifierDecorator(notifier);
        }
        return notifier;
    }
}
