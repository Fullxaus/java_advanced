package ru.mentee.power.exceptions.fileanalysis;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Анализатор, подсчитывающий количество слов в файле
 */
public class WordCountAnalyzer implements FileAnalyzer {
    private int maxLines = Integer.MAX_VALUE;

    /**
     * Устанавливает максимальное количество строк для анализа
     */
    public WordCountAnalyzer withMaxLines(int maxLines) {
        this.maxLines = maxLines;
        return this;
    }

    @Override
    public String getName() {
        return "Word Count Analyzer";
    }

    @Override
    public AnalysisResult analyze(Path filePath) throws FileAnalysisException {
        AnalysisResult result = new AnalysisResult(getName(), filePath);

        long totalWords = 0L;
        long totalWordLength = 0L;

        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            int linesRead = 0;
            while ((line = reader.readLine()) != null && linesRead < maxLines) {
                linesRead++;
                String trimmed = line.trim();
                if (trimmed.isEmpty()) continue;
                String[] tokens = trimmed.split("\\s+");
                for (String t : tokens) {
                    if (t.isEmpty()) continue;
                    totalWords++;
                    totalWordLength += t.length();
                }
            }

            double average = totalWords == 0 ? 0.0 : ((double) totalWordLength) / totalWords;
            result.addMetric("totalWords", totalWords);
            result.addMetric("averageWordLength", average);

            return result;
        } catch (IOException ioe) {
            throw new FileReadException("Failed to read file for word count: " + filePath, ioe);
        } catch (RuntimeException re) {
            // unexpected runtime errors (e.g., NPE from custom logic)
            throw new WordCountException("Unexpected runtime error during word count analysis of: " + filePath, re);
        } catch (Exception e) {
            // catch-all for other unexpected checked exceptions
            throw new WordCountException("Unexpected error during word count analysis of: " + filePath, e);
        }
    }
}
