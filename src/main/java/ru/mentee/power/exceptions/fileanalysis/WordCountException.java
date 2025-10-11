package ru.mentee.power.exceptions.fileanalysis;

/**
 * Исключение, выбрасываемое при ошибке анализа количества слов
 */
public class WordCountException extends AnalysisException {
    public WordCountException(String message) {
        super(message);
    }

    public WordCountException(String message, Throwable cause) {
        super(message, cause);
    }
}