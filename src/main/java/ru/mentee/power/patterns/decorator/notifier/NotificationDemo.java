package ru.mentee.power.patterns.decorator.notifier;

import ru.mentee.power.patterns.decorator.notifier.impl.EmailNotifier;
import ru.mentee.power.patterns.decorator.notifier.decorator.impl.SmsNotifierDecorator;
import ru.mentee.power.patterns.decorator.notifier.decorator.impl.EncryptionDecorator;
import ru.mentee.power.patterns.decorator.notifier.decorator.impl.SignatureDecorator;

public class NotificationDemo {
    public static void main(String[] args) {
        String message = "Важное уведомление о вашем аккаунте.";

        Notifier basicNotifier = new EmailNotifier();
        System.out.println("--- Простое Email Уведомление ---");
        basicNotifier.send(message);

        Notifier emailAndSms = new SmsNotifierDecorator(new EmailNotifier());
        System.out.println("\n--- Email + SMS ---");
        emailAndSms.send(message);

        Notifier encryptedEmail = new EncryptionDecorator(new EmailNotifier());
        System.out.println("\n--- Email + Шифрование ---");
        encryptedEmail.send(message);

        Notifier combinedNotifier = new EmailNotifier();
        combinedNotifier = new EncryptionDecorator(combinedNotifier);
        combinedNotifier = new SignatureDecorator(combinedNotifier);
        combinedNotifier = new SmsNotifierDecorator(combinedNotifier);
        System.out.println("\n--- Комбинация: Email -> Шифрование -> Подпись -> SMS ---");
        combinedNotifier.send(message);

        // 5) Другая комбинация (порядок оборачивания другой)
        Notifier combinedNotifier2 = new SmsNotifierDecorator(
                new SignatureDecorator(
                        new EncryptionDecorator(
                                new EmailNotifier()
                        )
                )
        );
        System.out.println("\n--- Другая комбинация ---");
        combinedNotifier2.send(message);
    }
}
