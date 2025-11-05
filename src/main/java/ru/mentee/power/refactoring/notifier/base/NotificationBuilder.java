package ru.mentee.power.refactoring.notifier.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.UnaryOperator;

public class NotificationBuilder {

    private final List<UnaryOperator<Notifier>> wrappers = new ArrayList<>();
    private SupplierBaseNotifier baseSupplier = SupplierBaseNotifier.EMAIL;

    public enum SupplierBaseNotifier {
        EMAIL // можно расширить (PUSH, DB, и т.д.)
    }

    public NotificationBuilder withEmailBase() {
        this.baseSupplier = SupplierBaseNotifier.EMAIL;
        return this;
    }

    // Добавляем Encryption ближе к базе — реализуется как wrapper, но применится в порядке добавления.
    public NotificationBuilder withEncryption() {
        wrappers.add(n -> new EncryptionDecorator(n));
        return this;
    }

    public NotificationBuilder withSignature() {
        wrappers.add(n -> new SignatureDecorator(n));
        return this;
    }

    public NotificationBuilder withSms() {
        wrappers.add(n -> new SmsNotifierDecorator(n));
        return this;
    }

    // Можно добавлять произвольные обёртки (для расширяемости)
    public NotificationBuilder addWrapper(UnaryOperator<Notifier> wrapper) {
        Objects.requireNonNull(wrapper);
        wrappers.add(wrapper);
        return this;
    }

    public Notifier build() {
        Notifier notifier = createBase();
        // порядок применений: первый добавленный wrapper оборачивает базовый и далее последующие становятся внешними
        for (UnaryOperator<Notifier> wrapper : wrappers) {
            notifier = wrapper.apply(notifier);
        }
        return notifier;
    }

    private Notifier createBase() {
        switch (baseSupplier) {
            case EMAIL:
            default:
                return new EmailNotifier();
        }
    }
}
