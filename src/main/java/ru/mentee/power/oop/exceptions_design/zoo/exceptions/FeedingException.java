package ru.mentee.power.oop.exceptions_design.zoo.exceptions;

public class FeedingException extends ZooException {
    public FeedingException(String message) {
        super(message);
    }

    public FeedingException(String message, Throwable cause) {
        super(message, cause);
    }
}
