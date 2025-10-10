package ru.mentee.power.oop.exceptions_design.zoo.exceptions;

public class HabitatException extends ZooException {
    public HabitatException(String message) {
        super(message);
    }

    public HabitatException(String message, Throwable cause) {
        super(message, cause);
    }
}
