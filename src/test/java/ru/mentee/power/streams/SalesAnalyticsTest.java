package ru.mentee.power.streams;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.mentee.power.streams.finaltask.Customer;
import ru.mentee.power.streams.finaltask.Product;
import ru.mentee.power.streams.finaltask.Sale;
import ru.mentee.power.streams.finaltask.SalesAnalyticsService;
import java.util.*;
import static org.assertj.core.api.Assertions.*;

public class SalesAnalyticsTest {

    private static List<Sale> testSales;
    private static Customer alice, bob, charlie, diana;
    private static Product laptop, mouse, keyboard, book, monitor, chair;

    // Сервис будет создаваться перед каждым тестом
    private SalesAnalyticsService service;

    @BeforeAll
    static void setUpData() {
        // Инициализируем тестовые данные один раз
        alice = new Customer("C1", "Alice", "New York");
        bob = new Customer("C2", "Bob", "London");
        charlie = new Customer("C3", "Charlie", "New York");
        diana = new Customer("C4", "Diana", "Paris");

        laptop = new Product("P1", "Laptop", "Electronics", 1200.0);
        mouse = new Product("P2", "Mouse", "Electronics", 25.0);
        keyboard = new Product("P3", "Keyboard", "Electronics", 75.0);
        book = new Product("P4", "Java Book", "Books", 50.0);
        monitor = new Product("P5", "Monitor", "Electronics", 300.0);
        chair = new Product("P6", "Office Chair", "Furniture", 150.0);

        testSales = List.of(
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

    }

    @BeforeEach // Создаем новый экземпляр сервиса перед каждым тестом
    void setUpService() {
        service = new SalesAnalyticsService();
    }
    @Test
    @DisplayName("1. Найти продажу по ID")
    void findSaleByIdTest() {
        Optional<Sale> saleS7 = service.findSaleById(testSales, "S7");
        assertThat(saleS7).isPresent();
        assertThat(saleS7.get().getCustomer()).isEqualTo(diana);
        assertThat(saleS7.get().getProduct()).isEqualTo(monitor);

        Optional<Sale> saleS99 = service.findSaleById(testSales, "S99");
        assertThat(saleS99).isEmpty();
    }

    @Test
    @DisplayName("2. Найти товары покупателя")
    void findProductNamesSoldToCustomerTest() {
        Set<String> aliceProducts = service.findProductNamesSoldToCustomer(testSales, "C1");
        assertThat(aliceProducts).containsExactlyInAnyOrder("Laptop", "Keyboard", "Mouse", "Monitor");

        Set<String> unknownProducts = service.findProductNamesSoldToCustomer(testSales, "C99");
        assertThat(unknownProducts).isEmpty();
    }

    @Test
    @DisplayName("3. Найти покупателей по городу")
    void findCustomersByCityTest() {
        Set<Customer> nyCustomers = service.findCustomersByCity(testSales, "New York");
        assertThat(nyCustomers).containsExactlyInAnyOrder(alice, charlie);

        Set<Customer> tokyoCustomers = service.findCustomersByCity(testSales, "Tokyo");
        assertThat(tokyoCustomers).isEmpty();
    }

    @Test
    @DisplayName("4. Общая выручка")
    void calculateTotalRevenueTest() {
        double totalRevenue = service.calculateTotalRevenue(testSales);
        assertThat(totalRevenue).isEqualTo(2550.0);
    }

    @Test
    @DisplayName("5. Выручка по категории")
    void calculateTotalRevenueByCategoryTest() {
        double electronicsRevenue = service.calculateTotalRevenueByCategory(testSales, "Electronics");
        assertThat(electronicsRevenue).isEqualTo(2050.0);

        double toysRevenue = service.calculateTotalRevenueByCategory(testSales, "Toys");
        assertThat(toysRevenue).isEqualTo(0.0);
    }

    @Test
    @DisplayName("6. Самый дорогой проданный товар")
    void findMostExpensiveProductSoldTest() {
        Optional<Product> mostExpensive = service.findMostExpensiveProductSold(testSales);
        assertThat(mostExpensive).isPresent().contains(laptop);
    }

    @Test
    @DisplayName("7. Группировка продаж по покупателю")
    void groupSalesByCustomerTest() {
        Map<Customer, List<Sale>> salesByCustomer = service.groupSalesByCustomer(testSales);
        assertThat(salesByCustomer).hasSize(4);
        assertThat(salesByCustomer.get(alice)).hasSize(4);
        assertThat(salesByCustomer.get(bob)).hasSize(3);
        assertThat(salesByCustomer.get(charlie)).hasSize(2);
        assertThat(salesByCustomer.get(diana)).hasSize(2);
    }

    @Test
    @DisplayName("8. Статистика продаж продукта")
    void getProductSalesStatisticsTest() {
        IntSummaryStatistics mouseStats = service.getProductSalesStatistics(testSales, "P2");
        assertThat(mouseStats.getCount()).isEqualTo(3);
        assertThat(mouseStats.getSum()).isEqualTo(4);
        assertThat(mouseStats.getMin()).isEqualTo(1);
        assertThat(mouseStats.getMax()).isEqualTo(2);
        assertThat(mouseStats.getAverage()).isEqualTo(4.0 / 3.0);

        IntSummaryStatistics unknownStats = service.getProductSalesStatistics(testSales, "P99");
        assertThat(unknownStats.getCount()).isZero();
    }

    @Test
    @DisplayName("9. Города покупателей")
    void getCustomerCitiesTest() {
        String cities = service.getCustomerCities(testSales);
        assertThat(cities).isEqualTo("London, New York, Paris");
    }

    @Test
    @DisplayName("10. Проверка продажи товара в категории")
    void checkIfProductWasSoldInCategoryTest() {
        boolean laptopSold = service.checkIfProductWasSoldInCategory(testSales, "Electronics", "Laptop");
        assertThat(laptopSold).isTrue();

        boolean mouseInBooks = service.checkIfProductWasSoldInCategory(testSales, "Books", "Mouse");
        assertThat(mouseInBooks).isFalse();

        boolean chairSold = service.checkIfProductWasSoldInCategory(testSales, "Furniture", "Office Chair");
        assertThat(chairSold).isTrue();
    }
}
