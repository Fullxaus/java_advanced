package ru.mentee.power.exceptions.multilevel;

public class InvalidPriceFormatException extends DataFormatException {
    private final String value;
    private final int lineNumber;

    public InvalidPriceFormatException(String message, String value, int lineNumber) {
        super(message);
        this.value = value;
        this.lineNumber = lineNumber;
    }

    public InvalidPriceFormatException(String message, String value, int lineNumber, Throwable cause) {
        super(message, cause);
        this.value = value;
        this.lineNumber = lineNumber;
    }

    public String getValue() { return value; }
    public int getLineNumber() { return lineNumber; }
}
