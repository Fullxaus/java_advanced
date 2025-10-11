package ru.mentee.power.exceptions.fileanalysis;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class CharacterFrequencyAnalyzer implements FileAnalyzer {
    private int maxLines = Integer.MAX_VALUE;

    public CharacterFrequencyAnalyzer withMaxLines(int maxLines) {
        this.maxLines = maxLines;
        return this;
    }

    @Override
    public String getName() {
        return "Character Frequency Analyzer";
    }

    @Override
    public AnalysisResult analyze(Path filePath) throws FileAnalysisException {
        AnalysisResult result = new AnalysisResult(getName(), filePath);

        Map<Character, Long> freq = new HashMap<>();
        long characterCount = 0L;

        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            int linesRead = 0;
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null && linesRead < maxLines) {
                linesRead++;
                sb.append(line);
            }

            String all = sb.toString();
            for (int i = 0; i < all.length(); i++) {
                char c = all.charAt(i);
                characterCount++;
                freq.put(c, freq.getOrDefault(c, 0L) + 1L);
            }

            result.addMetric("characterCount", characterCount);
            result.addMetric("uniqueCharacterCount", (long) freq.size());
            result.addMetric("frequencyMap", freq);

            return result;
        } catch (IOException ioe) {
            throw new FileReadException("Failed to read file for character frequency: " + filePath, ioe);
        } catch (RuntimeException re) {
            throw new CharacterFrequencyException("Unexpected runtime error during character frequency analysis of: " + filePath, re);
        } catch (Exception e) {
            throw new CharacterFrequencyException("Unexpected error during character frequency analysis of: " + filePath, e);
        }
    }
}
