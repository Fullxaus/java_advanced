package ru.mentee.power.exceptions.multilevel;

public class FileWriteException extends FileOperationException {
    public FileWriteException() { super(); }
    public FileWriteException(String message) { super(message); }
    public FileWriteException(String message, Throwable cause) { super(message, cause); }
    public FileWriteException(Throwable cause) { super(cause); }
}

