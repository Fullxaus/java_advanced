package ru.mentee.power.solid.isp.task.solution.client;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.mentee.power.solid.isp.task.solution.interf.Scannable;
import static org.mockito.Mockito.verify;

public class RefactoredScanClientTest {
    private final RefactoredScanClient scanClient = new RefactoredScanClient();

    @Test
    @DisplayName("Должен вызывать scan() у Scannable устройства")
    void shouldCallScanOnScannableDevice() {
        Scannable mockScannable = Mockito.mock(Scannable.class);
        String document = "scan_me.jpg";
        scanClient.scanDocument(mockScannable, document);
        verify(mockScannable).scan(document);
    }
}
