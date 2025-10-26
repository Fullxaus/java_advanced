package ru.mentee.power.streams;

import java.util.List;

public class StreamOrderingTask {

    public static void main(String[] args) {
        List<Transaction> transactions = List.of(
                new Transaction("tx1", 100.0, "USD", "New York"),
                new Transaction("tx2", 250.5, "EUR", "London"),
                new Transaction("tx3", 100.0, "USD", "Tokyo"),
                new Transaction("tx1", 150.0, "USD", "New York"), // Дубликат ID, другая сумма/валюта
                new Transaction("tx4", 500.0, "USD", "London"),
                new Transaction("tx5", 50.0, "EUR", "Paris"),
                new Transaction("tx6", 75.0, "USD", "Tokyo"),
                new Transaction("tx7", 300.0, "USD", "New York"),
                new Transaction("tx8", 120.0, "GBP", "London"),
                new Transaction("tx9", 200.0, "USD", "Paris")
        );

        System.out.println("--- Исходные транзакции ---");
        transactions.forEach(System.out::println);

        // --- Задача 1: Уникальные ID транзакций ---
        System.out.println("\n--- Уникальные ID транзакций --- ");
        List<String> uniqueTransactionIds = StreamOrderingTaskUtil.getUniqueTransactionIds(transactions);
        System.out.println(uniqueTransactionIds);

        // --- Задача 2: Топ 3 самых крупных транзакций в USD ---
        System.out.println("\n--- Топ 3 самых крупных транзакций в USD --- ");
        List<Transaction> top3UsdTransactions = StreamOrderingTaskUtil.getTop3UsdTransactions(transactions);
        top3UsdTransactions.forEach(System.out::println);

        // --- Задача 3: Транзакции, пропустив первые 2, отсортированные по городу ---
        System.out.println("\n--- Транзакции (пропущены 2, сорт. по городу) --- ");
        List<Transaction> sortedSkippedTransactions = StreamOrderingTaskUtil.getSkippedTransactionsSortedByCity(transactions, 2);
        sortedSkippedTransactions.forEach(System.out::println);

        System.out.println("\n--- Транзакции (пропущены 0, сорт. по городу) --- ");
        List<Transaction> sortedSkippedZero = StreamOrderingTaskUtil.getSkippedTransactionsSortedByCity(transactions, 0);
        sortedSkippedZero.forEach(System.out::println);

        System.out.println("\n--- Транзакции (пропущено больше чем есть, сорт. по городу) --- ");
        List<Transaction> sortedSkippedTooMany = StreamOrderingTaskUtil.getSkippedTransactionsSortedByCity(transactions, 20);
        System.out.println("Результат (должен быть пустым): " + sortedSkippedTooMany);
    }
}
