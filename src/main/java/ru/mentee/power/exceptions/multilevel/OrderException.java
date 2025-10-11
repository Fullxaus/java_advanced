package ru.mentee.power.exceptions.multilevel;

public class OrderException extends OrderProcessingException {
    public OrderException() { super(); }
    public OrderException(String message) { super(message); }
    public OrderException(String message, Throwable cause) { super(message, cause); }
    public OrderException(Throwable cause) { super(cause); }
}

