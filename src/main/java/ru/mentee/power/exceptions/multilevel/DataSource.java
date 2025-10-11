package ru.mentee.power.exceptions.multilevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class DataSource {
    private static final Logger LOG = Logger.getLogger(DataSource.class.getName());
    private final Path filePath;

    public DataSource(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    public List<String> readLines() throws FileReadException {
        List<String> lines = new ArrayList<>();
        if (!Files.exists(filePath)) {
            String msg = "Файл не найден: " + filePath;
            LOG.warning(msg);
            throw new FileReadException(msg, filePath);
        }

        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            String msg = "Ошибка чтения файла: " + filePath;
            LOG.severe(msg + " - " + e.getMessage());
            throw new FileReadException(msg, filePath, e);
        }

        return lines;
    }
}
