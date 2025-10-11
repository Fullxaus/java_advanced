package ru.mentee.power.exceptions.fileanalysis;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class SentenceAnalyzer implements FileAnalyzer {
    private int maxLines = Integer.MAX_VALUE;
    private static final Pattern SENTENCE_PATTERN = Pattern.compile("[.!?]\\s+");

    public SentenceAnalyzer withMaxLines(int maxLines) {
        this.maxLines = maxLines;
        return this;
    }

    @Override
    public String getName() {
        return "Sentence Analyzer";
    }

    @Override
    public AnalysisResult analyze(Path filePath) throws FileAnalysisException {
        AnalysisResult result = new AnalysisResult(getName(), filePath);

        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            StringBuilder sb = new StringBuilder();
            String line;
            int linesRead = 0;
            while ((line = reader.readLine()) != null && linesRead < maxLines) {
                linesRead++;
                if (sb.length() > 0) sb.append(' ');
                sb.append(line.trim());
            }

            String all = sb.toString().trim();
            if (all.isEmpty()) {
                result.addMetric("sentenceCount", 0L);
                result.addMetric("averageSentenceLength", 0.0);
                result.addMetric("longestSentence", "");
                result.addMetric("shortestSentence", "");
                return result;
            }

            String[] sentences = SENTENCE_PATTERN.split(all);
            List<String> cleaned = new ArrayList<>();
            for (String s : sentences) {
                String t = s.trim();
                if (!t.isEmpty()) cleaned.add(t);
            }

            int count = cleaned.size();
            long totalLength = 0L;
            String longest = "";
            String shortest = count > 0 ? cleaned.get(0) : "";

            for (String s : cleaned) {
                int len = s.length();
                totalLength += len;
                if (len > longest.length()) longest = s;
                if (len < shortest.length()) shortest = s;
            }

            double average = count == 0 ? 0.0 : ((double) totalLength) / count;

            result.addMetric("sentenceCount", (long) count);
            result.addMetric("averageSentenceLength", average);
            result.addMetric("longestSentence", longest);
            result.addMetric("shortestSentence", shortest);

            return result;
        } catch (IOException ioe) {
            throw new FileReadException("Failed to read file for sentence analysis: " + filePath, ioe);
        } catch (Exception e) {
            throw new AnalysisException("Unexpected error during sentence analysis of: " + filePath, e);
        }
    }
}
