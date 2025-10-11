package ru.mentee.power.exceptions.fileanalysis;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.*;

public class WordCountAnalyzerTest {
    @TempDir
    Path tempDir;

    @Test
    void shouldCountWordsCorrectly() throws Exception {
        Path file = tempDir.resolve("words.txt");
        // 5 words: hello world this is test
        Files.writeString(file, "hello world\nthis is test", StandardCharsets.UTF_8);

        WordCountAnalyzer analyzer = new WordCountAnalyzer();
        AnalysisResult result = analyzer.analyze(file);

        assertThat(result.getMetric("totalWords")).isEqualTo(5L);

        Object avgObj = result.getMetric("averageWordLength");
        assertThat(avgObj).isInstanceOf(Double.class);
        double avg = (Double) avgObj;
        // expected average: lengths = [5,5,4,2,4] => sum=20 => avg=4.0
        assertThat(avg).isEqualTo(4.0);
    }

    @Test
    void shouldThrowFileReadExceptionWhenFileDoesNotExist() {
        Path missing = tempDir.resolve("no_such_file.txt");
        WordCountAnalyzer analyzer = new WordCountAnalyzer();

        assertThatThrownBy(() -> analyzer.analyze(missing))
                .isInstanceOf(FileReadException.class)
                .hasCauseInstanceOf(java.io.IOException.class);
    }

    @Test
    void shouldWorkCorrectlyWithEmptyFile() throws Exception {
        Path file = tempDir.resolve("empty.txt");
        Files.createFile(file);

        WordCountAnalyzer analyzer = new WordCountAnalyzer();
        AnalysisResult result = analyzer.analyze(file);

        assertThat(result.getMetric("totalWords")).isEqualTo(0L);
        assertThat(result.getMetric("averageWordLength")).isEqualTo(0.0);
    }

    @Test
    void shouldRespectMaxLinesLimit() throws Exception {
        Path file = tempDir.resolve("manylines.txt");
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 10; i++) {
            sb.append("word").append(i).append("\n"); // each line contains one word
        }
        Files.writeString(file, sb.toString(), StandardCharsets.UTF_8);

        WordCountAnalyzer analyzer = new WordCountAnalyzer().withMaxLines(5);
        AnalysisResult result = analyzer.analyze(file);

        // With maxLines=5 and one word per line, expect 5 words
        assertThat(result.getMetric("totalWords")).isEqualTo(5L);
    }
}
