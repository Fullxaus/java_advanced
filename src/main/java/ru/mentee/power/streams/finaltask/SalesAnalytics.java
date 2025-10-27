package ru.mentee.power.streams.finaltask;

import java.util.*;

public class SalesAnalytics {

    public static void main(String[] args) {

        Customer alice = new Customer("C1", "Alice", "New York");
        Customer bob = new Customer("C2", "Bob", "London");
        Customer charlie = new Customer("C3", "Charlie", "New York");
        Customer diana = new Customer("C4", "Diana", "Paris");

        Product laptop = new Product("P1", "Laptop", "Electronics", 1200.0);
        Product mouse = new Product("P2", "Mouse", "Electronics", 25.0);
        Product keyboard = new Product("P3", "Keyboard", "Electronics", 75.0);
        Product book = new Product("P4", "Java Book", "Books", 50.0);
        Product monitor = new Product("P5", "Monitor", "Electronics", 300.0);
        Product chair = new Product("P6", "Office Chair", "Furniture", 150.0);

        List<Sale> salesData = List.of(
                new Sale("S1", alice, laptop, 1),
                new Sale("S2", bob, mouse, 2),
                new Sale("S3", alice, keyboard, 1),
                new Sale("S4", charlie, mouse, 1),
                new Sale("S5", bob, book, 3),
                new Sale("S6", alice, mouse, 1),
                new Sale("S7", diana, monitor, 1),
                new Sale("S8", bob, keyboard, 1),
                new Sale("S9", charlie, book, 1),
                new Sale("S10", alice, monitor, 1),
                new Sale("S11", diana, chair, 2)
        );


        SalesAnalyticsService service = new SalesAnalyticsService();

        System.out.println("--- Демонстрация методов анализа ---");

        System.out.println("\n1. Поиск продажи S7:");
        service.findSaleById(salesData, "S7").ifPresentOrElse(
                s -> System.out.println("   Найдено: " + s),
                () -> System.out.println("   Продажа S7 не найдена.")
        );
        System.out.println("\n   Поиск продажи S99:");
        service.findSaleById(salesData, "S99").ifPresentOrElse(
                s -> System.out.println("   Найдено: " + s),
                () -> System.out.println("   Продажа S99 не найдена.")
        );

        System.out.println("\n2. Товары, купленные Bob (C2):");
        System.out.println("   " + service.findProductNamesSoldToCustomer(salesData, "C2"));

        System.out.println("\n3. Покупатели из New York:");
        System.out.println("   " + service.findCustomersByCity(salesData, "New York"));
        System.out.println("\n   Покупатели из Paris:");
        System.out.println("   " + service.findCustomersByCity(salesData, "Paris"));

        System.out.println("\n4. Общая выручка:");
        System.out.printf("   %.2f%n", service.calculateTotalRevenue(salesData));

        System.out.println("\n5. Выручка по категории Electronics:");
        System.out.printf("   %.2f%n", service.calculateTotalRevenueByCategory(salesData, "Electronics"));
        System.out.println("\n   Выручка по категории Books:");
        System.out.printf("   %.2f%n", service.calculateTotalRevenueByCategory(salesData, "Books"));

        System.out.println("\n6. Самый дорогой проданный товар:");
        service.findMostExpensiveProductSold(salesData).ifPresentOrElse(
                p -> System.out.println("   " + p),
                () -> System.out.println("   Товары не найдены.")
        );

        System.out.println("\n7. Продажи по покупателям:");
        service.groupSalesByCustomer(salesData).forEach((customer, salesList) -> {
            System.out.println("   " + customer.getName() + ":");
            salesList.forEach(s -> System.out.println("     " + s.getSaleId() + " - " + s.getProduct().getName() + " (" + s.getQuantity() + ")"));
        });

        System.out.println("\n8. Статистика продаж Мыши (P2):");
        System.out.println("   " + service.getProductSalesStatistics(salesData, "P2"));
        System.out.println("\n   Статистика продаж Книги (P4):");
        System.out.println("   " + service.getProductSalesStatistics(salesData, "P4"));

        System.out.println("\n9. Города покупателей (уникальные, сортированные):");
        System.out.println("   " + service.getCustomerCities(salesData));

        System.out.println("\n10. Продан ли Laptop в категории Electronics?");
        System.out.println("    " + service.checkIfProductWasSoldInCategory(salesData, "Electronics", "Laptop"));
        System.out.println("\n    Продан ли Book в категории Electronics?");
        System.out.println("    " + service.checkIfProductWasSoldInCategory(salesData, "Electronics", "Java Book"));
        System.out.println("\n    Продан ли Chair в категории Furniture?");
        System.out.println("    " + service.checkIfProductWasSoldInCategory(salesData, "Furniture", "Office Chair"));

    }
}
