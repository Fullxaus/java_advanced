package ru.mentee.power.exceptions.fileanalysis;

/**
 * Исключение, выбрасываемое при ошибке анализа частоты символов
 */
public class CharacterFrequencyException extends AnalysisException {
    public CharacterFrequencyException(String message) {
        super(message);
    }

    public CharacterFrequencyException(String message, Throwable cause) {
        super(message, cause);
    }
}