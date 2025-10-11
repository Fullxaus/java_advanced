package ru.mentee.power.exceptions.fileanalysis;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class CharacterFrequencyAnalyzerTest {
    @TempDir
    Path tempDir;

    @Test
    void shouldCountCharactersCorrectly() throws Exception {
        Path file = tempDir.resolve("chars.txt");
        // content: "aa b\nc" -> reader in analyzer concatenates lines without newline -> "aa bc"
        Files.writeString(file, "aa b\nc", StandardCharsets.UTF_8);

        CharacterFrequencyAnalyzer analyzer = new CharacterFrequencyAnalyzer();
        AnalysisResult result = analyzer.analyze(file);

        assertThat(result.getMetric("characterCount")).isEqualTo(5L);
        assertThat(result.getMetric("uniqueCharacterCount")).isInstanceOf(Long.class);

        @SuppressWarnings("unchecked")
        Map<Character, Long> freq = (Map<Character, Long>) result.getMetric("frequencyMap");
        assertThat(freq).isNotNull();
        assertThat(freq.get('a')).isEqualTo(2L);
        assertThat(freq.get('b')).isEqualTo(1L);
        assertThat(freq.get('c')).isEqualTo(1L);
    }

    @Test
    void shouldThrowFileReadExceptionWhenFileDoesNotExist() {
        Path missing = tempDir.resolve("no_such_chars.txt");
        CharacterFrequencyAnalyzer analyzer = new CharacterFrequencyAnalyzer();

        assertThatThrownBy(() -> analyzer.analyze(missing))
                .isInstanceOf(FileReadException.class)
                .hasCauseInstanceOf(java.io.IOException.class);
    }

    @Test
    void shouldWorkCorrectlyWithEmptyFile() throws Exception {
        Path file = tempDir.resolve("empty_chars.txt");
        Files.createFile(file);

        CharacterFrequencyAnalyzer analyzer = new CharacterFrequencyAnalyzer();
        AnalysisResult result = analyzer.analyze(file);

        assertThat(result.getMetric("characterCount")).isEqualTo(0L);
        assertThat(result.getMetric("uniqueCharacterCount")).isEqualTo(0L);

        @SuppressWarnings("unchecked")
        Map<Character, Long> freq = (Map<Character, Long>) result.getMetric("frequencyMap");
        assertThat(freq).isNotNull().isEmpty();
    }

    @Test
    void shouldHandleNonAsciiCharacters() throws Exception {
        Path file = tempDir.resolve("unicode.txt");
        Files.writeString(file, "Привет\n✓✓", StandardCharsets.UTF_8);

        CharacterFrequencyAnalyzer analyzer = new CharacterFrequencyAnalyzer();
        AnalysisResult result = analyzer.analyze(file);

        assertThat(result.getMetric("characterCount")).isInstanceOf(Long.class);
        @SuppressWarnings("unchecked")
        Map<Character, Long> freq = (Map<Character, Long>) result.getMetric("frequencyMap");

        // basic sanity checks
        assertThat((Long) result.getMetric("characterCount")).isEqualTo(freq.values().stream().mapToLong(Long::longValue).sum());
        assertThat(freq).containsKey('П').containsKey('и').containsKey('✓');
        assertThat(freq.get('✓')).isEqualTo(2L);
    }
}
