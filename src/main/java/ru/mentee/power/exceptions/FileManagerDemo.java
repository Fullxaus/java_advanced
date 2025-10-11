package ru.mentee.power.exceptions;

import java.util.Properties;

/**
 * Демонстрационное приложение для FileManager
 */
public class FileManagerDemo {

    public static void main(String[] args) {
        FileManager fileManager = new FileManager();

        try {
            // Демонстрация чтения файла
            String content = fileManager.readTextFile("sample.txt");
            System.out.println("Содержимое файла:");
            System.out.println(content);

            // Демонстрация записи в файл
            fileManager.writeTextFile("output.txt", "Привет, мир!");
            System.out.println("Файл успешно записан.");

            // Демонстрация копирования файла
            fileManager.copyFile("sample.txt", "sample_copy.txt");
            System.out.println("Файл успешно скопирован.");

            // Демонстрация загрузки свойств
            Properties props = fileManager.loadProperties("config.properties");
            System.out.println("Загруженные свойства:");
            props.list(System.out);

        } catch (FileLockedException e) {
            System.err.println("Ошибка блокировки: " + e.getMessage());
            System.err.println("Файл: " + e.getFilePath());
            System.err.println("Блокирующий процесс: " + e.getLockOwner());

        } catch (StorageFullException e) {
            System.err.println("Ошибка нехватки места: " + e.getMessage());
            System.err.println("Требуется: " + formatSize(e.getRequiredSpace()));
            System.err.println("Доступно: " + formatSize(e.getAvailableSpace()));
            System.err.println("Не хватает: " + formatSize(e.getDeficit()));

        } catch (InvalidFileFormatException e) {
            System.err.println("Ошибка формата файла: " + e.getMessage());
            System.err.println("Файл: " + e.getFilePath());
            System.err.println("Ожидаемый формат: " + e.getExpectedFormat());
            System.err.println("Фактический формат: " + e.getActualFormat());

        } catch (FileProcessingException e) {
            System.err.println("Ошибка обработки файла: " + e.getMessage());
            if (e.getCause() != null) {
                System.err.println("Первоначальная причина: " + e.getCause().getMessage());
            }
        }
    }

    private static String formatSize(long size) {
        final String[] units = {"B", "KB", "MB", "GB", "TB"};
        int unitIndex = 0;
        double sizeInUnits = size;

        while (sizeInUnits >= 1024 && unitIndex < units.length - 1) {
            sizeInUnits /= 1024;
            unitIndex++;
        }

        return String.format("%.2f %s", sizeInUnits, units[unitIndex]);
    }
}
