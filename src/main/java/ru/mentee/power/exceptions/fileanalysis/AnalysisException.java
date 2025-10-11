package ru.mentee.power.exceptions.fileanalysis;

/**
 * Базовое исключение для всех исключений, связанных с процессом анализа
 */
public class AnalysisException extends FileAnalysisException {
    public AnalysisException(String message) {
        super(message);
    }

    public AnalysisException(String message, Throwable cause) {
        super(message, cause);
    }
}