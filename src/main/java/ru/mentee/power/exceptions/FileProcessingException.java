package ru.mentee.power.exceptions;

/**
 * Базовое исключение для операций с файлами
 */
public class FileProcessingException extends Exception {

    public FileProcessingException(String message) {
        super(message);
    }

    public FileProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
