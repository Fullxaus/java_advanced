package ru.mentee.power.exceptions.fileanalysis;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FileAnalysisService {
    private final List<FileAnalyzer> analyzers = new ArrayList<>();

    public FileAnalysisService registerAnalyzer(FileAnalyzer analyzer) {
        analyzers.add(analyzer);
        return this;
    }

    public List<AnalysisResult> analyzeFile(Path filePath) throws FileAnalysisException {
        if (analyzers.isEmpty()) {
            throw new FileAnalysisException("No analyzers registered in FileAnalysisService");
        }

        List<AnalysisResult> results = new ArrayList<>();
        for (FileAnalyzer analyzer : analyzers) {
            try {
                AnalysisResult r = analyzer.analyze(filePath);
                results.add(r);
            } catch (FileAnalysisException fae) {
                // add context about which analyzer failed
                String msg = String.format("Analyzer '%s' failed for file %s: %s",
                        analyzer.getName(), filePath, fae.getMessage());
                throw new FileAnalysisException(msg, fae);
            } catch (Exception e) {
                String msg = String.format("Unexpected error in analyzer '%s' for file %s",
                        analyzer.getName(), filePath);
                throw new FileAnalysisException(msg, e);
            }
        }
        return results;
    }

    public Optional<FileAnalyzer> findAnalyzerByName(String name) {
        for (FileAnalyzer a : analyzers) {
            if (a.getName().equals(name)) {
                return Optional.of(a);
            }
        }
        return Optional.empty();
    }
}
