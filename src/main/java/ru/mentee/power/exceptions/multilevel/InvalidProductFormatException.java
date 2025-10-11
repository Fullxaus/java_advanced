package ru.mentee.power.exceptions.multilevel;

public class InvalidProductFormatException extends DataFormatException {
    private final String line;
    private final int lineNumber;

    public InvalidProductFormatException(String message, String line, int lineNumber) {
        super(message);
        this.line = line;
        this.lineNumber = lineNumber;
    }

    public InvalidProductFormatException(String message, String line, int lineNumber, Throwable cause) {
        super(message, cause);
        this.line = line;
        this.lineNumber = lineNumber;
    }

    public String getLine() { return line; }
    public int getLineNumber() { return lineNumber; }
}

