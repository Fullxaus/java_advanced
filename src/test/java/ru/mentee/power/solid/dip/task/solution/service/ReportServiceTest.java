package ru.mentee.power.solid.dip.task.solution.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import ru.mentee.power.solid.dip.task.solution.abstraction.DataSource;
import ru.mentee.power.solid.dip.task.solution.abstraction.ReportDestination;
import ru.mentee.power.solid.dip.task.solution.abstraction.ReportFormatter;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ReportServiceTest {

    private DataSource mockDataSource;
    private ReportFormatter mockReportFormatter;
    private ReportDestination mockReportDestination;
    private ReportService reportService;

    @BeforeEach
    void setUp() {
        mockDataSource = Mockito.mock(DataSource.class);
        mockReportFormatter = Mockito.mock(ReportFormatter.class);
        mockReportDestination = Mockito.mock(ReportDestination.class);
        reportService = new ReportService(mockDataSource, mockReportFormatter, mockReportDestination);
    }

    @Test
    @DisplayName("Должен получать данные, форматировать и записывать отчет")
    void shouldFetchFormatAndWriteReport() {
        List<String> dummyData = List.of("data1", "data2");
        String formattedData = "<html>formatted data</html>";
        String destinationHint = "report.html";

        when(mockDataSource.getData()).thenReturn(dummyData);
        when(mockReportFormatter.format(dummyData)).thenReturn(formattedData);

        reportService.generateAndWriteReport(destinationHint);

        verify(mockDataSource, times(1)).getData();
        verify(mockReportFormatter, times(1)).format(dummyData);

        ArgumentCaptor<String> contentCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> hintCaptor = ArgumentCaptor.forClass(String.class);
        verify(mockReportDestination, times(1)).write(contentCaptor.capture(), hintCaptor.capture());

        assertThat(contentCaptor.getValue()).isEqualTo(formattedData);
        assertThat(hintCaptor.getValue()).isEqualTo(destinationHint);

        verifyNoMoreInteractions(mockDataSource, mockReportFormatter, mockReportDestination);
    }
}
