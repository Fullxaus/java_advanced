package ru.mentee.power.streams;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamOrderingTaskUtil {

    public static List<String> getUniqueTransactionIds(List<Transaction> transactions) {
        if (transactions == null) {
            return Collections.emptyList();
        }
        return transactions.stream()
                .map(Transaction::getId)
                .distinct()
                .collect(Collectors.toList());
    }

    public static List<Transaction> getTopNTransactionsByCurrency(List<Transaction> transactions, String currency, int topN) {
        if (transactions == null || topN <= 0 || currency == null) {
            return Collections.emptyList();
        }
        return transactions.stream()
                .filter(t -> t.getCurrency().equals(currency))
                .sorted(Comparator.comparingDouble(Transaction::getAmount).reversed())
                .limit(topN)
                .collect(Collectors.toList());
    }

    public static List<Transaction> getTop3UsdTransactions(List<Transaction> transactions) {
        return getTopNTransactionsByCurrency(transactions, "USD", 3);
    }

    public static List<Transaction> getSkippedTransactionsSortedByCity(List<Transaction> transactions, int skipCount) {
        if (transactions == null || skipCount < 0) {
            return Collections.emptyList();
        }
        return transactions.stream()
                .sorted(Comparator.comparing(Transaction::getCity))
                .skip(skipCount)
                .collect(Collectors.toList());
    }
}

