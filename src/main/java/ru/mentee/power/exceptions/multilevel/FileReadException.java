package ru.mentee.power.exceptions.multilevel;

import java.nio.file.Path;

public class FileReadException extends FileOperationException {
    private final Path path;

    public FileReadException(String message, Path path) {
        super(message);
        this.path = path;
    }

    public FileReadException(String message, Path path, Throwable cause) {
        super(message, cause);
        this.path = path;
    }

    public Path getPath() { return path; }
}

