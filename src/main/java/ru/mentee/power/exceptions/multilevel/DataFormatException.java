package ru.mentee.power.exceptions.multilevel;

public class DataFormatException extends OrderProcessingException {
    public DataFormatException() { super(); }
    public DataFormatException(String message) { super(message); }
    public DataFormatException(String message, Throwable cause) { super(message, cause); }
    public DataFormatException(Throwable cause) { super(cause); }
}

