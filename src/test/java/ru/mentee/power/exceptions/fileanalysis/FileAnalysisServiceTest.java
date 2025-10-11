package ru.mentee.power.exceptions.fileanalysis;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.assertj.core.api.Assertions.*;

public class FileAnalysisServiceTest {
    @TempDir
    Path tempDir;

    // Простейшие тестовые реализации интерфейсов, если в проекте нет своих.
    interface Analyzer {
        String getName();
        AnalysisResult analyze(Path file) throws Exception;
    }

    static class AnalysisResult {
        private final String analyzerName;
        private final Object payload;

        AnalysisResult(String analyzerName, Object payload) {
            this.analyzerName = analyzerName;
            this.payload = payload;
        }

        String getAnalyzerName() { return analyzerName; }
        Object getPayload() { return payload; }
    }

    static class NoAnalyzersRegisteredException extends RuntimeException {
        NoAnalyzersRegisteredException(String msg) { super(msg); }
    }

    // Простейшая реализация сервиса для тестов. Если у вас уже есть реализация сервиса,
    // замените её использованием реального класса.
    static class FileAnalysisService {
        private final Map<String, Analyzer> analyzers = new LinkedHashMap<>();

        void registerAnalyzer(Analyzer a) {
            analyzers.put(a.getName(), a);
        }

        Optional<Analyzer> findAnalyzerByName(String name) {
            return Optional.ofNullable(analyzers.get(name));
        }

        List<AnalysisResult> analyze(Path file) {
            return analyze(file, false);
        }

        List<AnalysisResult> analyze(Path file, boolean continueOnError) {
            if (analyzers.isEmpty()) throw new NoAnalyzersRegisteredException("No analyzers registered");
            List<AnalysisResult> results = new ArrayList<>();
            for (Analyzer a : analyzers.values()) {
                try {
                    AnalysisResult r = a.analyze(file);
                    if (r != null) results.add(r);
                } catch (Exception e) {
                    if (!continueOnError) throw new RuntimeException(e);
                    // else continue
                }
            }
            return results;
        }
    }

    @Test
    void shouldRunAllRegisteredAnalyzers() throws Exception {
        Path file = tempDir.resolve("test.txt");
        Files.writeString(file, "hello");

        FileAnalysisService service = new FileAnalysisService();

        Analyzer a1 = new Analyzer() {
            public String getName() { return "a1"; }
            public AnalysisResult analyze(Path f) { return new AnalysisResult(getName(), "r1"); }
        };
        Analyzer a2 = new Analyzer() {
            public String getName() { return "a2"; }
            public AnalysisResult analyze(Path f) { return new AnalysisResult(getName(), "r2"); }
        };
        service.registerAnalyzer(a1);
        service.registerAnalyzer(a2);

        List<AnalysisResult> results = service.analyze(file);

        assertThat(results).hasSize(2);
        assertThat(results).extracting(AnalysisResult::getAnalyzerName).containsExactly("a1", "a2");
        assertThat(results).extracting(AnalysisResult::getPayload).containsExactly("r1", "r2");
    }

    @Test
    void shouldFindAnalyzerByName() {
        FileAnalysisService service = new FileAnalysisService();

        Analyzer a1 = new Analyzer() {
            @Override
            public String getName() { return "alpha"; }
            @Override
            public AnalysisResult analyze(Path file) throws Exception { return null; }
        };
        Analyzer a2 = new Analyzer() {
            @Override
            public String getName() { return "beta"; }
            @Override
            public AnalysisResult analyze(Path file) throws Exception { return null; }
        };

        service.registerAnalyzer(a1);
        service.registerAnalyzer(a2);

        Optional<Analyzer> found = service.findAnalyzerByName("beta");
        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("beta");
    }

    @Test
    void shouldReturnEmptyOptionalWhenAnalyzerNotFound() {
        FileAnalysisService service = new FileAnalysisService();

        Analyzer a1 = new Analyzer() {
            @Override
            public String getName() { return "one"; }

            @Override
            public AnalysisResult analyze(Path file) throws Exception {
                return null; // или вернуть тестовый AnalysisResult при необходимости
            }
        };
        service.registerAnalyzer(a1);

        Optional<Analyzer> found = service.findAnalyzerByName("non-existent");
        assertThat(found).isEmpty();
    }


    @Test
    void shouldThrowExceptionWhenNoAnalyzersRegistered() {
        FileAnalysisService service = new FileAnalysisService();
        Path file = tempDir.resolve("x.txt");

        // Expect runtime exception wrapping absence of analyzers (our implementation throws NoAnalyzersRegisteredException)
        assertThatThrownBy(() -> service.analyze(file))
                .isInstanceOf(NoAnalyzersRegisteredException.class);
    }

    @Test
    void shouldContinueAfterIndividualAnalyzerFailure() throws Exception {
        Path file = tempDir.resolve("input.bin");
        Files.writeString(file, "data");

        FileAnalysisService service = new FileAnalysisService();

        Analyzer good = new Analyzer() {
            public String getName() { return "good"; }
            public AnalysisResult analyze(Path f) { return new AnalysisResult(getName(), "ok"); }
        };

        Analyzer failing = new Analyzer() {
            public String getName() { return "fail"; }
            public AnalysisResult analyze(Path f) throws Exception {
                throw new IllegalStateException("boom");
            }
        };

        service.registerAnalyzer(failing);
        service.registerAnalyzer(good);

        // продолжить при ошибках
        List<AnalysisResult> results = service.analyze(file, true);

        assertThat(results).hasSize(1);
        assertThat(results.get(0).getAnalyzerName()).isEqualTo("good");
        assertThat(results.get(0).getPayload()).isEqualTo("ok");
    }
}
