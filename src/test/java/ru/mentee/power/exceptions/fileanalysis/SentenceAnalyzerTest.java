package ru.mentee.power.exceptions.fileanalysis;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.*;

public class SentenceAnalyzerTest {
    @TempDir
    Path tempDir;

    @Test
    void shouldCountSentencesCorrectly() throws Exception {
        Path file = tempDir.resolve("sentences.txt");
        String content = "Hello world. How are you? I'm fine! Short.";
        Files.writeString(file, content, StandardCharsets.UTF_8);

        SentenceAnalyzer analyzer = new SentenceAnalyzer();
        AnalysisResult result = analyzer.analyze(file);

        assertThat(result.getMetric("sentenceCount")).isEqualTo(4L);

        String longest = (String) result.getMetric("longestSentence");
        String shortest = (String) result.getMetric("shortestSentence");
        double avg = (Double) result.getMetric("averageSentenceLength");

        assertThat(longest).isNotEmpty();
        assertThat(shortest).isNotEmpty();
        assertThat(longest.length()).isGreaterThanOrEqualTo(shortest.length());
        assertThat(avg).isGreaterThan(0.0);
    }

    @Test
    void shouldHandleComplexSentences() throws Exception {
        Path file = tempDir.resolve("complex.txt");
        // contains abbreviation "Dr.", ellipsis, and question
        String content = "Dr. Smith arrived at 10 a.m. He said: 'Wait... what?'. Then he left.";
        Files.writeString(file, content, StandardCharsets.UTF_8);

        SentenceAnalyzer analyzer = new SentenceAnalyzer();
        AnalysisResult result = analyzer.analyze(file);

        // We expect at least 2 sentences (exact splitting depends on pattern)
        assertThat((Long) result.getMetric("sentenceCount")).isGreaterThanOrEqualTo(2L);

        // Ensure longest/shortest are present
        assertThat(result.getMetric("longestSentence")).isInstanceOf(String.class);
        assertThat(result.getMetric("shortestSentence")).isInstanceOf(String.class);
    }

    @Test
    void shouldHandleBinaryFileEitherByThrowingOrReturningResult() throws Exception {
        Path file = tempDir.resolve("binary.dat");
        byte[] data = new byte[]{0x00, 0x01, 0x02, 0x03, 0x04};
        Files.write(file, data);

        SentenceAnalyzer analyzer = new SentenceAnalyzer();

        try {
            AnalysisResult r = analyzer.analyze(file);
            // Если реализация возвращает результат, проверяем, что он не null (или другие ожидания)
            assertThat(r).isNotNull();
        } catch (InvalidFileFormatException | FileReadException e) {
            // Ожидаемые исключения — тест проходит
            assertThat(e).isInstanceOfAny(InvalidFileFormatException.class, FileReadException.class);
        }
    }

    @Test
    void shouldRespectMaxLinesLimit() throws Exception {
        Path file = tempDir.resolve("manylines.txt");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append("Sentence number ").append(i).append(". ");
            sb.append("Extra text to make it longer.\n");
        }
        Files.writeString(file, sb.toString(), StandardCharsets.UTF_8);

        // limit to first 3 lines
        SentenceAnalyzer analyzer = new SentenceAnalyzer().withMaxLines(3);
        AnalysisResult result = analyzer.analyze(file);

        // At least one sentence should be present; ensure not all 10 were counted.
        long count = (Long) result.getMetric("sentenceCount");
        assertThat(count).isGreaterThanOrEqualTo(1L).isLessThanOrEqualTo(10L);

        // Heuristic: since we limited to 3 lines, sentence count must be <= sentences produced by full file.
        SentenceAnalyzer full = new SentenceAnalyzer();
        AnalysisResult fullResult = full.analyze(file);
        long fullCount = (Long) fullResult.getMetric("sentenceCount");
        assertThat(count).isLessThanOrEqualTo(fullCount);
    }
}
