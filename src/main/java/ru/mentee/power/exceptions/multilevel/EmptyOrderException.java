package ru.mentee.power.exceptions.multilevel;

public class EmptyOrderException extends OrderException {
    public EmptyOrderException(String message) { super(message); }
    public EmptyOrderException(String message, Throwable cause) { super(message, cause); }
}

