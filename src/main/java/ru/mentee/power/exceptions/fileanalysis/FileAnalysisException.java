package ru.mentee.power.exceptions.fileanalysis;

/**
 * Базовое исключение для всех исключений, связанных с анализом файлов
 */
public class FileAnalysisException extends Exception {
    public FileAnalysisException(String message) {
        super(message);
    }

    public FileAnalysisException(String message, Throwable cause) {
        super(message, cause);
    }
}