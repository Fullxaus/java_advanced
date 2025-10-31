package ru.mentee.power.solid.srp.task.solution;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReportingServiceTest {

    @Mock
    private DataFilter mockDataFilter;

    @Mock
    private ReportFormatter mockReportFormatter;

    @Mock
    private ReportPrinter mockReportPrinter;

    @InjectMocks
    private ReportingService reportingService;

    @Test
    @DisplayName("Должен вызывать все компоненты в правильной последовательности")
    void shouldCallAllComponentsInOrder() {
        List<ReportData> testData = List.of(new ReportData("Test", 100.0));
        double threshold = 50.0;
        List<ReportData> filteredData = List.of(new ReportData("Filtered Test", 100.0));
        String formattedReport = "Formatted Report String";

        // Используем ArgumentMatchers.eq для точного соответствия типов
        when(mockDataFilter.filterByThreshold(eq(testData), eq(threshold))).thenReturn(filteredData);
        when(mockReportFormatter.formatToString(filteredData)).thenReturn(formattedReport);

        reportingService.generateAndPrintReport(testData, threshold);

        verify(mockDataFilter, times(1)).filterByThreshold(testData, threshold);
        verify(mockReportFormatter, times(1)).formatToString(filteredData);
        verify(mockReportPrinter, times(1)).printToConsole(formattedReport);
    }
}