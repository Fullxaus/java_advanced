package ru.mentee.power.exceptions;

/**
 * Исключение, выбрасываемое при попытке доступа к заблокированному файлу
 */
public class FileLockedException extends FileProcessingException {

    private final String filePath;
    private final String lockOwner;

    public FileLockedException(String filePath, String lockOwner) {
        super(String.format("Файл '%s' заблокирован процессом '%s'", filePath, lockOwner));
        this.filePath = filePath;
        this.lockOwner = lockOwner;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getLockOwner() {
        return lockOwner;
    }
}
