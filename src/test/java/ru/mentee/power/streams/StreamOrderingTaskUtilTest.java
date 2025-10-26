package ru.mentee.power.streams;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

public class StreamOrderingTaskUtilTest {

    private List<Transaction> testTransactions;
    private Transaction tx1, tx2, tx3, tx1Dup, tx4, tx5, tx6, tx7, tx8, tx9;

    @BeforeEach
    void setUp() {
        tx1 = new Transaction("tx1", 100.0, "USD", "New York");
        tx2 = new Transaction("tx2", 250.5, "EUR", "London");
        tx3 = new Transaction("tx3", 100.0, "USD", "Tokyo");
        tx1Dup = new Transaction("tx1", 150.0, "USD", "New York"); // Дубликат ID
        tx4 = new Transaction("tx4", 500.0, "USD", "London");
        tx5 = new Transaction("tx5", 50.0, "EUR", "Paris");
        tx6 = new Transaction("tx6", 75.0, "USD", "Tokyo");
        tx7 = new Transaction("tx7", 300.0, "USD", "New York");
        tx8 = new Transaction("tx8", 120.0, "GBP", "London");
        tx9 = new Transaction("tx9", 200.0, "USD", "Paris");

        testTransactions = List.of(tx1, tx2, tx3, tx1Dup, tx4, tx5, tx6, tx7, tx8, tx9);
    }

    @Test
    @DisplayName("getUniqueTransactionIds: Возвращает уникальные ID")
    void getUniqueTransactionIds_ShouldReturnUniqueIds() {
        // Вызовите StreamOrderingTaskUtil.getUniqueTransactionIds(testTransactions)
        List<String> result = StreamOrderingTaskUtil.getUniqueTransactionIds(testTransactions);

        // Проверьте, что результат содержит все ID без дубликатов
        List<String> expectedIds = List.of("tx1", "tx2", "tx3", "tx4", "tx5", "tx6", "tx7", "tx8", "tx9");
        assertThat(result).containsExactlyInAnyOrderElementsOf(expectedIds);
    }

    @Test
    @DisplayName("getUniqueTransactionIds: Пустой или null список")
    void getUniqueTransactionIds_EmptyOrNull_ShouldReturnEmptyList() {
        // Проверьте с пустым списком
        List<Transaction> emptyList = Collections.emptyList();
        List<String> resultEmpty = StreamOrderingTaskUtil.getUniqueTransactionIds(emptyList);
        assertThat(resultEmpty).isEmpty();

        // Проверьте с null списком
        List<String> resultNull = StreamOrderingTaskUtil.getUniqueTransactionIds(null);
        assertThat(resultNull).isEmpty();
    }

    @Test
    @DisplayName("getTopNTransactionsByCurrency: Находит топ N USD транзакций")
    void getTopNTransactionsByCurrency_Usd_ShouldReturnTopN() {
        // Вызовите StreamOrderingTaskUtil.getTopNTransactionsByCurrency(testTransactions, "USD", 3)
        List<Transaction> result = StreamOrderingTaskUtil.getTopNTransactionsByCurrency(testTransactions, "USD", 3);

        // Проверьте, что в списке 3 транзакции
        assertThat(result).hasSize(3);

        // Все транзакции должны быть в USD и отсортированы по убыванию amount
        assertThat(result).allSatisfy(t -> assertThat(t.getCurrency()).isEqualTo("USD"));
        assertThat(result).isSortedAccordingTo(Comparator.comparingDouble(Transaction::getAmount).reversed());
    }

    @Test
    @DisplayName("getTopNTransactionsByCurrency: Несуществующая валюта или topN<=0")
    void getTopNTransactionsByCurrency_InvalidParams_ShouldReturnEmptyList() {
        // 1. Проверьте с несуществующей валютой ("XYZ")
        List<Transaction> resultInvalidCurrency = StreamOrderingTaskUtil.getTopNTransactionsByCurrency(testTransactions, "XYZ", 3);
        assertThat(resultInvalidCurrency).isEmpty();

        // 2. Проверьте с topN = 0
        List<Transaction> resultTopNZero = StreamOrderingTaskUtil.getTopNTransactionsByCurrency(testTransactions, "USD", 0);
        assertThat(resultTopNZero).isEmpty();

        // 3. Проверьте с topN < 0
        List<Transaction> resultTopNNegative = StreamOrderingTaskUtil.getTopNTransactionsByCurrency(testTransactions, "USD", -1);
        assertThat(resultTopNNegative).isEmpty();

        // 4. Проверьте с null currency
        List<Transaction> resultNullCurrency = StreamOrderingTaskUtil.getTopNTransactionsByCurrency(testTransactions, null, 3);
        assertThat(resultNullCurrency).isEmpty();

        // 5. Проверьте с null списком транзакций
        List<Transaction> resultNullTransactions = StreamOrderingTaskUtil.getTopNTransactionsByCurrency(null, "USD", 3);
        assertThat(resultNullTransactions).isEmpty();
    }

    @Test
    @DisplayName("getSkippedTransactionsSortedByCity: Пропуск и сортировка по городу")
    void getSkippedTransactionsSortedByCity_ShouldSkipAndSort() {
        // 1. Вызовите StreamOrderingTaskUtil.getSkippedTransactionsSortedByCity(testTransactions, 2)
        List<Transaction> result = StreamOrderingTaskUtil.getSkippedTransactionsSortedByCity(testTransactions, 2);

        // 2. Проверьте, что оставшиеся отсортированы по городу
        List<String> cities = result.stream().map(Transaction::getCity).collect(Collectors.toList());
        assertThat(cities).isSorted();

        // Проверьте содержимое result
        List<Transaction> expectedTransactions = testTransactions.stream()
                .sorted(Comparator.comparing(Transaction::getCity))
                .skip(2)
                .collect(Collectors.toList());
        assertThat(result).isEqualTo(expectedTransactions);
    }

    @Test
    @DisplayName("getSkippedTransactionsSortedByCity: SkipCount >= size или < 0, или null список")
    void getSkippedTransactionsSortedByCity_InvalidParams_ShouldHandleCorrectly() {
        // 1. Проверьте, когда skipCount равен количеству транзакций (ожидается пустой список)
        List<Transaction> resultSkipCountEqualsSize = StreamOrderingTaskUtil.getSkippedTransactionsSortedByCity(testTransactions, testTransactions.size());
        assertThat(resultSkipCountEqualsSize).isEmpty();

        // 2. Проверьте, когда skipCount больше количества транзакций (ожидается пустой список)
        List<Transaction> resultSkipCountGreaterThanSize = StreamOrderingTaskUtil.getSkippedTransactionsSortedByCity(testTransactions, testTransactions.size() + 1);
        assertThat(resultSkipCountGreaterThanSize).isEmpty();

        // 3. Проверьте, когда skipCount < 0 (ожидается пустой список согласно Javadoc)
        List<Transaction> resultSkipCountNegative = StreamOrderingTaskUtil.getSkippedTransactionsSortedByCity(testTransactions, -1);
        assertThat(resultSkipCountNegative).isEmpty();

        // 4. Проверьте с null списком транзакций
        List<Transaction> resultNullTransactions = StreamOrderingTaskUtil.getSkippedTransactionsSortedByCity(null, 2);
        assertThat(resultNullTransactions).isEmpty();
    }
}
