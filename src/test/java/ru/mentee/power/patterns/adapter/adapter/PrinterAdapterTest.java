package ru.mentee.power.patterns.adapter.adapter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import ru.mentee.power.patterns.adapter.legacy.LegacyPrinter;
import ru.mentee.power.patterns.adapter.printer.ModernPrinter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

public class PrinterAdapterTest {

    @Test
    @DisplayName("PrinterAdapter должен вызывать printLegacyFormat у LegacyPrinter с преобразованными данными")
    void shouldCallPrintLegacyFormatOnAdaptee() {
        LegacyPrinter mockLegacyPrinter = Mockito.mock(LegacyPrinter.class);
        ModernPrinter adapter = new PrinterAdapter(mockLegacyPrinter);
        String testContent = "Test Document Content";
        char[] expectedCharArray = testContent.toCharArray();

        adapter.printDocument(testContent);

        ArgumentCaptor<char[]> charArrayCaptor = ArgumentCaptor.forClass(char[].class);
        verify(mockLegacyPrinter).printLegacyFormat(charArrayCaptor.capture());
        assertThat(charArrayCaptor.getValue()).isEqualTo(expectedCharArray);
    }
}