package ru.mentee.power.patterns.decorator.notifier.decorator.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import ru.mentee.power.patterns.decorator.notifier.Notifier;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

public class SignatureDecoratorTest {
    @Test
    @DisplayName("SignatureDecorator должен добавлять подпись и вызывать wrapped.send() с измененным сообщением")
    void shouldAddSignatureAndCallWrapped() {
        Notifier mockNotifier = Mockito.mock(Notifier.class);
        Notifier signatureDecorator = new SignatureDecorator(mockNotifier);
        String originalMessage = "Обычное сообщение";
        String expectedSignature = "\n--\nС уважением, Команда";

        signatureDecorator.send(originalMessage);

        ArgumentCaptor<String> messageCaptor = ArgumentCaptor.forClass(String.class);
        verify(mockNotifier).send(messageCaptor.capture());
        assertThat(messageCaptor.getValue()).isEqualTo(originalMessage + expectedSignature);
    }
}
