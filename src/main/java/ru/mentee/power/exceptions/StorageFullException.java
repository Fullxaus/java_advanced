package ru.mentee.power.exceptions;

/**
 * Исключение, выбрасываемое при недостатке места для операции
 */
public class StorageFullException extends FileProcessingException {

    private final long requiredSpace;
    private final long availableSpace;

    public StorageFullException(long requiredSpace, long availableSpace) {
        super(String.format("Недостаточно места для выполнения операции: требуется %d байт, доступно %d байт",
                requiredSpace, availableSpace));
        this.requiredSpace = requiredSpace;
        this.availableSpace = availableSpace;
    }

    public long getRequiredSpace() {
        return requiredSpace;
    }

    public long getAvailableSpace() {
        return availableSpace;
    }

    public long getDeficit() {
        return requiredSpace - availableSpace;
    }
}
