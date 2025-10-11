package ru.mentee.power.exceptions.fileanalysis;

/**
 * Исключение, выбрасываемое при неверном формате файла
 */
public class InvalidFileFormatException extends FileAnalysisException {
    public InvalidFileFormatException(String message) {
        super(message);
    }

    public InvalidFileFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}