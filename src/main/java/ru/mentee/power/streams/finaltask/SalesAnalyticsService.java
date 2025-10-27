package ru.mentee.power.streams.finaltask;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SalesAnalyticsService {

    public Optional<Sale> findSaleById(List<Sale> sales, String saleId) {
        return sales.stream()
                .filter(s -> s.getSaleId().equals(saleId))
                .findFirst();
    }

    public Set<String> findProductNamesSoldToCustomer(List<Sale> sales, String customerId) {
        return sales.stream()
                .filter(s -> s.getCustomer().getId().equals(customerId))
                .map(s -> s.getProduct().getName())
                .collect(Collectors.toSet());
    }

    public Set<Customer> findCustomersByCity(List<Sale> sales, String city) {
        return sales.stream()
                .map(Sale::getCustomer)
                .filter(c -> c.getCity().equals(city))
                .collect(Collectors.toSet());
    }

    public double calculateTotalRevenue(List<Sale> sales) {
        return sales.stream()
                .mapToDouble(s -> s.getQuantity() * s.getProduct().getPrice())
                .sum();
    }

    public double calculateTotalRevenueByCategory(List<Sale> sales, String category) {
        return sales.stream()
                .filter(s -> s.getProduct().getCategory().equals(category))
                .mapToDouble(s -> s.getQuantity() * s.getProduct().getPrice())
                .sum();
    }

    public Optional<Product> findMostExpensiveProductSold(List<Sale> sales) {
        return sales.stream()
                .map(Sale::getProduct)
                .max(Comparator.comparingDouble(Product::getPrice));
    }

    public Map<Customer, List<Sale>> groupSalesByCustomer(List<Sale> sales) {
        return sales.stream()
                .collect(Collectors.groupingBy(Sale::getCustomer));
    }

    public IntSummaryStatistics getProductSalesStatistics(List<Sale> sales, String productId) {
        return sales.stream()
                .filter(s -> s.getProduct().getId().equals(productId))
                .mapToInt(Sale::getQuantity)
                .summaryStatistics();
    }

    public String getCustomerCities(List<Sale> sales) {
        return sales.stream()
                .map(Sale::getCustomer)
                .map(Customer::getCity)
                .distinct()
                .sorted()
                .collect(Collectors.joining(", "));
    }

    public boolean checkIfProductWasSoldInCategory(List<Sale> sales, String category, String productName) {
        return sales.stream()
                .anyMatch(s -> s.getProduct().getCategory().equals(category) && s.getProduct().getName().equals(productName));
    }
}


