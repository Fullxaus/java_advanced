package ru.mentee.power.refactoring.notifier.base;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;


public class EmailNotifierFactoryTest {

    private final NotifierFactory factory = new NotifierFactory();

    @Test
    @DisplayName("Должен создать базовый EmailNotifier, если нет опций")
    void shouldCreateBaseEmailNotifierWhenNoOptions() {
        Notifier notifier = factory.createNotifier(Set.of());

        assertThat(notifier).isInstanceOf(EmailNotifier.class);
    }

    @Test
    @DisplayName("Должен корректно применять декоратор SMS")
    void shouldApplySmsDecorator() {
        Notifier notifier = factory.createNotifier(Set.of("SMS"));
        assertThat(notifier).isInstanceOf(SmsNotifierDecorator.class);

        // Перехватываем вывод
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);
        try {
            notifier.send("test sms");
        } finally {
            System.setOut(old);
        }
        String out = baos.toString();

        assertThat(out).contains("Email sent: test sms");
        assertThat(out).contains("SMS sent: test sms");
    }

    @Test
    @DisplayName("Должен корректно применять декоратор ENCRYPT")
    void shouldApplyEncryptDecorator() {
        Notifier notifier = factory.createNotifier(Set.of("ENCRYPT"));

        assertThat(notifier).isInstanceOf(EncryptionDecorator.class);

    }

    @Test
    @DisplayName("Должен корректно применять декоратор SIGN")
    void shouldApplySignDecorator() {
        Notifier notifier = factory.createNotifier(Set.of("SIGN"));

        assertThat(notifier).isInstanceOf(SignatureDecorator.class);
    }

    @Test
    @DisplayName("Должен корректно применять несколько декораторов в правильном порядке")
    void shouldApplyMultipleDecoratorsInCorrectOrder() {
        // Порядок в каркасе фабрики: ENCRYPT -> SIGN -> SMS
        Notifier notifier = factory.createNotifier(Set.of("SMS", "ENCRYPT", "SIGN"));

        assertThat(notifier).isInstanceOf(SmsNotifierDecorator.class);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream oldOut = System.out;
        System.setOut(ps);
        try {
            notifier.send("super secret message");
        } finally {
            System.setOut(oldOut);
        }
        String out = baos.toString();

        assertThat(out).contains("Email sent: [ENCRYPTED]super secret message [Signed]");
        assertThat(out).contains("SMS sent: super secret message");

        // Дополнительно — проверка вложенности типов через рефлексию (необязательно, безопасно)
        Notifier current = notifier;
        assertThat(current).isInstanceOf(SmsNotifierDecorator.class);
        try {
            java.lang.reflect.Field wrappedField = current.getClass().getSuperclass().getDeclaredField("wrapped");
            wrappedField.setAccessible(true);
            Object inner1 = wrappedField.get(current);
            assertThat(inner1).isInstanceOf(SignatureDecorator.class);

            Object inner2 = wrappedField.get(inner1);
            assertThat(inner2).isInstanceOf(EncryptionDecorator.class);

            Object inner3 = wrappedField.get(inner2);
            assertThat(inner3).isInstanceOf(EmailNotifier.class);
        } catch (NoSuchFieldException | IllegalAccessException ignored) {

        }
    }
    @Test
    @DisplayName("Должен возвращать базовый уведомитель, если опции неизвестны")
    void shouldReturnBaseNotifierForUnknownOptions() {
        Notifier notifier = factory.createNotifier(Set.of("UNKNOWN_OPTION"));
        assertThat(notifier).isInstanceOf(EmailNotifier.class);
    }
}
