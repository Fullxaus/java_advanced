package ru.mentee.power.exceptions.fileanalysis;

import java.nio.file.Path;

/**
 * Интерфейс для анализаторов файлов
 */
public interface FileAnalyzer {
    /**
     * Анализирует файл и возвращает результат
     *
     * @param filePath путь к файлу
     * @return результат анализа
     * @throws FileAnalysisException если произошла ошибка при анализе
     */
    AnalysisResult analyze(Path filePath) throws FileAnalysisException;

    /**
     * Возвращает название анализатора
     */
    String getName();
}
