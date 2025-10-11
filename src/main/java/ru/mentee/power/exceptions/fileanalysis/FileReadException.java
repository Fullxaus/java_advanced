package ru.mentee.power.exceptions.fileanalysis;

import java.io.IOException;

/**
 * Исключение, выбрасываемое при ошибке чтения файла
 */
public class FileReadException extends FileAnalysisException {
    public FileReadException(String message) {
        super(message);
    }

    public FileReadException(String message, IOException cause) {
        super(message, cause);
    }
}