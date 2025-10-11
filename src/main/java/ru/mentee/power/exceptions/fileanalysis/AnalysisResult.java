package ru.mentee.power.exceptions.fileanalysis;

import java.nio.file.Path;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Результат анализа файла от одного анализатора.
 * Хранит имя анализатора, путь к файлу и набор метрик (ключ -> значение).
 *
 * Реализовано безопасное добавление метрик, получение неизменяемой карты метрик,
 * а также удобные геттеры и toString().
 */
public final class AnalysisResult {
    private final String analyzerName;
    private final Path filePath;
    private final Map<String, Object> metrics;

    /**
     * Создает пустой результат анализа.
     *
     * @param analyzerName имя анализатора (не null)
     * @param filePath     путь к файлу (может быть null)
     */
    public AnalysisResult(String analyzerName, Path filePath) {
        this.analyzerName = Objects.requireNonNull(analyzerName, "analyzerName must not be null");
        this.filePath = filePath;
        this.metrics = new LinkedHashMap<>();
    }

    /**
     * Добавляет метрику к результату. Если метрика с таким ключом уже существует, значение будет перезаписано.
     *
     * @param key   имя метрики (не null)
     * @param value значение метрики (может быть null)
     * @return this для удобства чейнинга
     */
    public synchronized AnalysisResult addMetric(String key, Object value) {
        Objects.requireNonNull(key, "metric key must not be null");
        metrics.put(key, value);
        return this;
    }

    /**
     * Возвращает значение метрики по ключу.
     *
     * @param key ключ метрики
     * @return значение или null, если метрика отсутствует
     */
    public synchronized Object getMetric(String key) {
        return metrics.get(key);
    }

    /**
     * Возвращает неизменяемую копию карты метрик.
     *
     * @return map метрик
     */
    public synchronized Map<String, Object> getMetrics() {
        return Collections.unmodifiableMap(new LinkedHashMap<>(metrics));
    }

    /**
     * Имя анализатора, сгенерировавшего результат.
     */
    public String getAnalyzerName() {
        return analyzerName;
    }

    /**
     * Путь к файлу, к которому относится результат.
     */
    public Path getFilePath() {
        return filePath;
    }

    /**
     * Удобный строковый вывод результата: имя анализатора, путь и метрики.
     */
    @Override
    public synchronized String toString() {
        return "AnalysisResult{" +
                "analyzerName='" + analyzerName + '\'' +
                ", filePath=" + filePath +
                ", metrics=" + metrics +
                '}';
    }

    @Override
    public synchronized boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AnalysisResult)) return false;
        AnalysisResult that = (AnalysisResult) o;
        return analyzerName.equals(that.analyzerName) &&
                Objects.equals(filePath, that.filePath) &&
                metrics.equals(that.metrics);
    }

    @Override
    public synchronized int hashCode() {
        return Objects.hash(analyzerName, filePath, metrics);
    }
}
