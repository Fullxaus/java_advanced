package ru.mentee.power.finaltask.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import ru.mentee.power.finaltask.model.exceptions.DataFetchingException;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonQuoteDataSource implements QuoteDataSource {
    private final String filePath;
    private final ObjectMapper objectMapper;

    public JsonQuoteDataSource(String filePath) {
        this.filePath = filePath;
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public List<Quote> fetchQuotes() throws DataFetchingException {
        try {
            File f = new File(filePath);
            if (!f.exists()) {
                throw new DataFetchingException("Файл данных не найден: " + filePath);
            }
            return objectMapper.readValue(f, new TypeReference<List<Quote>>() {
            });
        } catch (IOException e) {
            throw new DataFetchingException("Ошибка чтения/парсинга JSON: " + e.getMessage(), e);
        }
    }
}
