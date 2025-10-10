package ru.mentee.power.exceptions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

import static org.assertj.core.api.Assertions.*;

public class FileManagerTest {
    private FileManager fileManager;

    @TempDir
    Path tempDir;

    private File testFile;
    private File propertiesFile;

    @BeforeEach
    void setUp() throws IOException {
        fileManager = new FileManager();

        // Создаем тестовый файл
        testFile = tempDir.resolve("test.txt").toFile();
        Files.write(testFile.toPath(), "Hello, world!".getBytes(StandardCharsets.UTF_8));

        // Создаем файл свойств
        propertiesFile = tempDir.resolve("test.properties").toFile();
        Properties props = new Properties();
        props.setProperty("key1", "value1");
        props.setProperty("key2", "value2");
        try (FileOutputStream out = new FileOutputStream(propertiesFile)) {
            props.store(out, "Test Properties");
        }
    }


    @Test
    void readTextFileShouldReturnFileContents() throws FileProcessingException, FileLockedException {
        String content = fileManager.readTextFile(testFile.getAbsolutePath());

        assertThat(content.trim()).isEqualTo("Hello, world!");
    }

    @Test
    void readTextFileShouldThrowFileProcessingExceptionForNonExistentFile() {
        assertThatExceptionOfType(FileProcessingException.class)
                .isThrownBy(() -> fileManager.readTextFile(tempDir.resolve("non_existent.txt").toString()))
                .withMessageContaining("Файл не найден")
                .withMessageContaining("non_existent.txt");
    }

    @Test
    void writeTextFileShouldCreateNewFile() throws FileProcessingException, FileLockedException, StorageFullException {
        File outputFile = tempDir.resolve("output.txt").toFile();

        fileManager.writeTextFile(outputFile.getAbsolutePath(), "New content");

        assertThat(outputFile).exists();
        assertThat(outputFile).hasContent("New content");
    }

    @Test
    void copyFileShouldCreateNewFileWithSameContent()
            throws FileProcessingException, FileLockedException, StorageFullException {
        File copyFile = tempDir.resolve("copy.txt").toFile();

        fileManager.copyFile(testFile.getAbsolutePath(), copyFile.getAbsolutePath());

        assertThat(copyFile).exists();
        assertThat(copyFile).hasSameTextualContentAs(testFile);
    }

    @Test
    void loadPropertiesShouldReturnPropertiesObject() throws FileProcessingException {
        Properties props = fileManager.loadProperties(propertiesFile.getAbsolutePath());

        assertThat(props).isNotNull();
        assertThat(props.getProperty("key1")).isEqualTo("value1");
        assertThat(props.getProperty("key2")).isEqualTo("value2");
    }

    @Test
    void loadPropertiesShouldThrowInvalidFileFormatExceptionForNonPropertiesFile() {
        assertThatExceptionOfType(InvalidFileFormatException.class)
                .isThrownBy(() -> fileManager.loadProperties(testFile.getAbsolutePath()))
                .withMessageContaining("Неверный формат файла")
                .withMessageContaining(".properties");
    }


    @Test
    void readTextFileShouldThrowFileLockedException() throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(testFile, "rw");
             FileLock lock = raf.getChannel().lock()) {

            // Проверим, что блокировка реально мешает другому RandomAccessFile (OS-dependent)
            boolean lockBlocksOthers;
            try (RandomAccessFile raf2 = new RandomAccessFile(testFile, "rw")) {
                lockBlocksOthers = false;
            } catch (IOException e) {
                lockBlocksOthers = true;
            }

            if (!lockBlocksOthers) {
                // Среда не блокирует — пропускаем тест
                return;
            }

            assertThatExceptionOfType(FileLockedException.class)
                    .isThrownBy(() -> fileManager.readTextFile(testFile.getAbsolutePath()))
                    .withMessageContaining("заблокирован");
        }
    }
}
