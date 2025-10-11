package ru.mentee.power.exceptions.multilevel;

public class FileOperationException extends OrderProcessingException {
    public FileOperationException() { super(); }
    public FileOperationException(String message) { super(message); }
    public FileOperationException(String message, Throwable cause) { super(message, cause); }
    public FileOperationException(Throwable cause) { super(cause); }
}

