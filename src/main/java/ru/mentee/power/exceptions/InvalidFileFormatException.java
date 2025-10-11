package ru.mentee.power.exceptions;

/**
 * Исключение, выбрасываемое при неверном формате файла
 */
public class InvalidFileFormatException extends RuntimeException {

    private final String filePath;
    private final String expectedFormat;
    private final String actualFormat;

    public InvalidFileFormatException(String filePath, String expectedFormat, String actualFormat) {
        super(String.format("Неверный формат файла '%s': ожидается '%s', получен '%s'",
                filePath, expectedFormat, actualFormat));
        this.filePath = filePath;
        this.expectedFormat = expectedFormat;
        this.actualFormat = actualFormat;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getExpectedFormat() {
        return expectedFormat;
    }

    public String getActualFormat() {
        return actualFormat;
    }
}
