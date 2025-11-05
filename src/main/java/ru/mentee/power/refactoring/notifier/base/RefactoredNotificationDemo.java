package ru.mentee.power.refactoring.notifier.base;

import java.util.Set;

public class RefactoredNotificationDemo {
    public static void main(String[] args) {
        String message = "Важное уведомление после рефакторинга.";

        // --- Builder ---
        Notifier viaBuilder = new NotificationBuilder()
                .withEmailBase()
                .withEncryption()
                .withSignature()
                .withSms()
                .build();

        System.out.println("--- Builder: Email + Encrypt + Sign + SMS ---");
        viaBuilder.send(message);

        // --- Factory ---
        NotifierFactory factory = new NotifierFactory();
        Notifier viaFactory = factory.createNotifier(Set.of(NotifierFactory.OPT_SMS, NotifierFactory.OPT_ENCRYPT, NotifierFactory.OPT_SIGN));
        System.out.println("\n--- Factory: Email + Encrypt + Sign + SMS ---");
        viaFactory.send(message);

        // --- Minimal: only email ---
        Notifier basic = new NotificationBuilder().withEmailBase().build();
        System.out.println("\n--- Builder: only Email ---");
        basic.send(message);
    }
}
