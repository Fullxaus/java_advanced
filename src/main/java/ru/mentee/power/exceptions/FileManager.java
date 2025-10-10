package ru.mentee.power.exceptions;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Менеджер файлов с продвинутой обработкой исключений
 */
public class FileManager {

    private static final Logger logger = Logger.getLogger(FileManager.class.getName());
    private static final long MIN_FREE_SPACE = 1024 * 1024; // 1MB минимум свободного места

    /**
     * Читает текстовый файл и возвращает его содержимое в виде строки
     *
     * @param filePath путь к файлу
     * @return содержимое файла
     * @throws FileProcessingException если произошла ошибка при чтении файла
     * @throws FileLockedException     если файл заблокирован другим процессом
     */
    public String readTextFile(String filePath) throws FileProcessingException, FileLockedException {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            String msg = "Файл не найден: " + filePath;
            logger.warning(msg);
            throw new FileProcessingException(msg);
        }

        if (isFileLocked(filePath)) {
            String owner = getLockOwner(filePath);
            logger.warning("Файл заблокирован: " + filePath + " владельцем: " + owner);
            throw new FileLockedException(filePath, owner);
        }

        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append(System.lineSeparator());
            }
            return sb.toString();
        } catch (IOException e) {
            String msg = "Ошибка чтения файла: " + filePath;
            logger.log(Level.SEVERE, msg, e);
            throw new FileProcessingException(msg, e);
        }
    }

    /**
     * Записывает текст в файл
     *
     * @param filePath путь к файлу
     * @param content  содержимое для записи
     * @throws FileProcessingException если произошла ошибка при записи файла
     * @throws FileLockedException     если файл заблокирован другим процессом
     * @throws StorageFullException    если недостаточно места для записи
     */
    public void writeTextFile(String filePath, String content)
            throws FileProcessingException, FileLockedException, StorageFullException {
        Path path = Paths.get(filePath);
        try {
            Path parent = path.getParent() != null ? path.getParent() : Paths.get(".");
            long required = content.getBytes().length + MIN_FREE_SPACE;
            checkFreeSpace(parent.toString(), required);
        } catch (StorageFullException e) {
            logger.log(Level.WARNING, "Недостаточно места для записи файла: " + filePath, e);
            throw e;
        }

        if (isFileLocked(filePath)) {
            String owner = getLockOwner(filePath);
            logger.warning("Невозможно записать — файл заблокирован: " + filePath + " владельцем: " + owner);
            throw new FileLockedException(filePath, getLockOwner(filePath));
        }

        // try-with-resources для безопасной записи
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            writer.write(content);
            writer.flush();
        } catch (IOException e) {
            String msg = "Ошибка записи файла: " + filePath;
            logger.log(Level.SEVERE, msg, e);
            throw new FileProcessingException(msg, e);
        }
    }

    /**
     * Копирует файл с проверкой наличия свободного места
     *
     * @param sourcePath      путь к исходному файлу
     * @param destinationPath путь к файлу назначения
     * @throws FileProcessingException если произошла ошибка при копировании
     * @throws FileLockedException     если один из файлов заблокирован
     * @throws StorageFullException    если недостаточно места для копирования
     */
    public void copyFile(String sourcePath, String destinationPath)
            throws FileProcessingException, FileLockedException, StorageFullException {
        Path src = Paths.get(sourcePath);
        Path dst = Paths.get(destinationPath);

        if (!Files.exists(src)) {
            String msg = "Исходный файл не найден: " + sourcePath;
            logger.warning(msg);
            throw new FileProcessingException(msg);
        }

        if (isFileLocked(sourcePath)) {
            String owner = getLockOwner(sourcePath);
            logger.warning("Исходный файл заблокирован: " + sourcePath + " владельцем: " + owner);
            throw new FileLockedException(sourcePath, owner);
        }

        if (isFileLocked(destinationPath)) {
            String owner = getLockOwner(destinationPath);
            logger.warning("Файл назначения заблокирован: " + destinationPath + " владельцем: " + owner);
            throw new FileLockedException(destinationPath, owner);
        }

        try {
            long size = Files.size(src);
            Path dstParent = dst.getParent() != null ? dst.getParent() : Paths.get(".");
            checkFreeSpace(dstParent.toString(), size + MIN_FREE_SPACE);
        } catch (IOException e) {
            String msg = "Не удалось получить размер файла: " + sourcePath;
            logger.log(Level.SEVERE, msg, e);
            throw new FileProcessingException(msg, e);
        }

        try {
            Files.copy(src, dst, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            String msg = "Ошибка при копировании файла: " + sourcePath + " -> " + destinationPath;
            logger.log(Level.SEVERE, msg, e);
            throw new FileProcessingException(msg, e);
        }
    }

    /**
     * Загружает свойства из файла .properties
     *
     * @param filePath путь к файлу свойств
     * @return объект Properties с загруженными свойствами
     * @throws FileProcessingException если произошла ошибка при чтении файла
     * @throws InvalidFileFormatException если файл имеет неверный формат
     */
    public Properties loadProperties(String filePath) throws FileProcessingException {
        if (filePath == null || !filePath.toLowerCase().endsWith(".properties")) {
            String msg = "Ожидался файл с расширением .properties: " + filePath;
            logger.warning(msg);
            throw new InvalidFileFormatException(filePath, ".properties", filePath == null ? "null" : filePath);
        }

        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            String msg = "Файл свойств не найден: " + filePath;
            logger.warning(msg);
            throw new FileProcessingException(msg);
        }

        if (isFileLocked(filePath)) {
            String owner = getLockOwner(filePath);
            logger.warning("Файл свойств заблокирован: " + filePath + " владельцем: " + owner);
            throw new FileLockedException(filePath, owner);
        }

        Properties props = new Properties();
        try (InputStream in = Files.newInputStream(path)) {
            props.load(in);
            return props;
        } catch (IOException e) {
            String msg = "Ошибка загрузки свойств из файла: " + filePath;
            logger.log(Level.SEVERE, msg, e);
            throw new FileProcessingException(msg, e);
        } catch (IllegalArgumentException e) {
            // Properties.load может бросить IllegalArgumentException при ошибках формата
            String msg = "Неверный формат файла свойств: " + filePath;
            logger.log(Level.WARNING, msg, e);
            throw new InvalidFileFormatException(filePath, ".properties", "invalid content");
        }
    }

    /**
     * Проверяет, заблокирован ли файл другим процессом
     *
     * @param filePath путь к файлу
     * @return true, если файл заблокирован, иначе false
     */
    private boolean isFileLocked(String filePath) {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            return false;
        }

        // Упрощённая проверка: пытаемся открыть файл для записи с опцией APPEND, если не удаётся — возможно заблокирован
        try (FileChannel channel = FileChannel.open(path, StandardOpenOption.WRITE, StandardOpenOption.APPEND)) {
            // удачно открыли — считаем не заблокированным
            return false;
        } catch (IOException e) {
            logger.fine("Файл, возможно, заблокирован (IOException при открытии на запись): " + filePath + " - " + e.getMessage());
            return true;
        }
    }

    /**
     * Проверяет наличие достаточного свободного места для файла указанного размера
     *
     * @param directoryPath путь к директории
     * @param requiredSpace требуемое пространство в байтах
     * @throws StorageFullException если свободного места недостаточно
     */
    private void checkFreeSpace(String directoryPath, long requiredSpace) throws StorageFullException {
        try {
            Path dir = Paths.get(directoryPath);
            if (!Files.exists(dir)) {
                dir = dir.getParent() != null ? dir.getParent() : Paths.get(".");
            }
            FileStore store = Files.getFileStore(dir);
            long available = store.getUsableSpace();
            if (available < requiredSpace) {
                logger.warning(String.format("Недостаточно места: требуется %d, доступно %d", requiredSpace, available));
                throw new StorageFullException(requiredSpace, available);
            }
        } catch (IOException e) {
            String msg = "Не удалось проверить доступное место на диске для пути: " + directoryPath;
            logger.log(Level.SEVERE, msg, e);
            // В случае ошибки проверки места — обертка в FileProcessingException бы была уместна,
            // но по контракту мы должны бросать StorageFullException на отсутствие места, поэтому
            // пробрасываем StorageFullException с available=0
            throw new StorageFullException(requiredSpace, 0);
        }
    }

    /**
     * Получает ID процесса, блокирующего файл (упрощенная реализация)
     *
     * @param filePath путь к файлу
     * @return ID блокирующего процесса или "Unknown"
     */
    private String getLockOwner(String filePath) {
        // В реальном приложении здесь был бы код для определения блокирующего процесса
        return "Unknown Process";
    }
}
