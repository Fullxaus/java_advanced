package ru.mentee.power.patterns.decorator.notifier.decorator.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import ru.mentee.power.patterns.decorator.notifier.Notifier;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

public class EncryptionDecoratorTest {
    @Test
    @DisplayName("EncryptionDecorator должен шифровать сообщение и вызывать wrapped.send() с шифрованным сообщением")
    void shouldEncryptMessageAndCallWrapped() {
        Notifier mockNotifier = Mockito.mock(Notifier.class);
        Notifier encryptionDecorator = new EncryptionDecorator(mockNotifier);
        String originalMessage = "Секретное сообщение";

        encryptionDecorator.send(originalMessage);

        ArgumentCaptor<String> messageCaptor = ArgumentCaptor.forClass(String.class);
        verify(mockNotifier).send(messageCaptor.capture());
        assertThat(messageCaptor.getValue()).startsWith("[Encrypted]");
        assertThat(messageCaptor.getValue()).contains(originalMessage);

    }
}
