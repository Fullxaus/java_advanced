package ru.mentee.power.exceptions.fileanalysis;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

public class FileAnalysisDemo {
    public static void main(String[] args) {
// 1. Настройка анализаторов
        WordCountAnalyzer wordAnalyzer = new WordCountAnalyzer().withMaxLines(1000);
        CharacterFrequencyAnalyzer charAnalyzer = new CharacterFrequencyAnalyzer().withMaxLines(1000);
        SentenceAnalyzer sentenceAnalyzer = new SentenceAnalyzer().withMaxLines(1000);
        // 2. Регистрация в сервисе
        FileAnalysisService service = new FileAnalysisService()
                .registerAnalyzer(wordAnalyzer)
                .registerAnalyzer(charAnalyzer)
                .registerAnalyzer(sentenceAnalyzer);

        // 3. Путь к существующему файлу (замените на реальный путь при запуске)
        Path existingFile = Paths.get("example.txt");

        // 3a. Демонстрация анализа существующего файла (если файл отсутствует — будет показана обработка исключения)
        try {
            if (Files.exists(existingFile)) {
                List<AnalysisResult> results = service.analyzeFile(existingFile);
                System.out.println("Analysis results for " + existingFile + ":");
                results.forEach(r -> System.out.println(r));
            } else {
                System.out.println(existingFile + " does not exist — create this file to see analysis results.");
            }
        } catch (FileAnalysisException fae) {
            System.err.println("Error during analysis: " + fae.getMessage());
            fae.printStackTrace(System.err);
        }

        // 4. Демонстрация обработки ошибки: несуществующий файл
        Path missingFile = Paths.get("this_file_does_not_exist.txt");
        try {
            service.analyzeFile(missingFile);
        } catch (FileAnalysisException fae) {
            System.out.println("Handled missing-file case: " + fae.getMessage());
        }

        // 5. Использование Optional для поиска и вызова конкретного анализатора
        Optional<FileAnalyzer> maybeWord = service.findAnalyzerByName("Word Count Analyzer");
        maybeWord.ifPresent(analyzer -> {
            try {
                AnalysisResult r = analyzer.analyze(existingFile);
                System.out.println("Word analyzer single result: " + r);
            } catch (FileAnalysisException e) {
                System.out.println("Error running word analyzer: " + e.getMessage());
            }
        });

        // 6. Пример безопасного поиска несуществующего анализатора
        Optional<FileAnalyzer> maybeUnknown = service.findAnalyzerByName("Nonexistent Analyzer");
        if (maybeUnknown.isEmpty()) {
            System.out.println("Analyzer 'Nonexistent Analyzer' not found.");
        }

    }
}
