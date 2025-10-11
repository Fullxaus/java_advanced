package ru.mentee.power.oop.exceptions_design.zoo.exceptions;

public class ZooException extends Exception {
    public ZooException(String message) {
        super(message);
    }

    public ZooException(String message, Throwable cause) {
        super(message, cause);
    }
}
