package ru.mentee.power.finaltask.model.exceptions;

public class DataFetchingException extends QuoteAppException {
    public DataFetchingException(String message, Throwable cause) { super(message, cause); }
    public DataFetchingException(String message) { super(message); }
}
