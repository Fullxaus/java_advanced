package ru.mentee.power.finaltask.model;

import org.junit.jupiter.api.Test;
import ru.mentee.power.finaltask.model.exceptions.DataFetchingException;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonQuoteDataSourceTest {

    @Test
    void fetchQuotes_shouldParseJsonFile() throws Exception {
        String json = "[" +
                "{\"ticker\":\"AAPL\",\"price\":175.50,\"lastUpdate\":\"2023-10-27T10:30:00\"}," +
                "{\"ticker\":\"GOOGL\",\"price\":2700.10,\"lastUpdate\":\"2023-10-27T10:31:00\"}" +
                "]";

        Path tempFile = Files.createTempFile("quotes-test", ".json");
        Files.writeString(tempFile, json);

        JsonQuoteDataSource ds = new JsonQuoteDataSource(tempFile.toString());
        List<Quote> quotes = ds.fetchQuotes();

        assertNotNull(quotes);
        assertEquals(2, quotes.size());

        Quote aapl = quotes.stream().filter(q -> "AAPL".equals(q.getTicker())).findFirst().orElse(null);
        assertNotNull(aapl);
        assertEquals(0, new BigDecimal("175.50").compareTo(aapl.getPrice()));
        assertEquals(LocalDateTime.of(2023, 10, 27, 10, 30, 0), aapl.getLastUpdate());

        // cleanup
        Files.deleteIfExists(tempFile);
    }

    @Test
    void fetchQuotes_nonExistingFile_shouldThrowDataFetchingException() {
        JsonQuoteDataSource ds = new JsonQuoteDataSource("non-existent-file-xyz.json");
        assertThrows(DataFetchingException.class, ds::fetchQuotes);
    }

    @Test
    void fetchQuotes_malformedJson_shouldThrowDataFetchingException() throws IOException {
        Path tempFile = Files.createTempFile("quotes-bad", ".json");
        Files.writeString(tempFile, "{ bad json ]");

        JsonQuoteDataSource ds = new JsonQuoteDataSource(tempFile.toString());
        try {
            assertThrows(DataFetchingException.class, ds::fetchQuotes);
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }
}

