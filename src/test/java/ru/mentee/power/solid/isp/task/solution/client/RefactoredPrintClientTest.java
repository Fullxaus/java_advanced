package ru.mentee.power.solid.isp.task.solution.client;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.mentee.power.solid.isp.task.solution.interf.Printable;
import static org.mockito.Mockito.verify;

public class RefactoredPrintClientTest {
    private final RefactoredPrintClient printClient = new RefactoredPrintClient();

    @Test
    @DisplayName("Должен вызывать print() у Printable устройства")
    void shouldCallPrintOnPrintableDevice() {
        Printable mockPrintable = Mockito.mock(Printable.class);
        String document = "mydoc.txt";
        printClient.sendToPrint(mockPrintable, document);
        verify(mockPrintable).print(document);
    }
}

