package ru.mentee.power.oop.inheritance;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Тесты для класса ElectronicsItem")
public class ElectronicsItemTest {
    private ElectronicsItem testElectronicsItem;
    private final String sku = "ELEC01";
    private final String name = "Смартфон";
    private final int quantity = 15;
    private final double price = 35000.0;
    private final int warranty = 12;

    @BeforeEach
    void setUp() {
        // Инициализируем testElectronicsItem перед каждым тестом
        testElectronicsItem = new ElectronicsItem(sku, name, quantity, price, warranty);
    }

    @Test
    @DisplayName("Конструктор должен корректно инициализировать все поля")
    void constructorShouldInitializeFieldsCorrectly() {
        // Assert: унаследованные поля
        assertThat(testElectronicsItem.getSku()).isEqualTo(sku);
        assertThat(testElectronicsItem.getName()).isEqualTo(name);
        assertThat(testElectronicsItem.getQuantity()).isEqualTo(quantity);
        assertThat(testElectronicsItem.getUnitPrice()).isEqualTo(price);

        // Assert: поле warrantyMonths
        assertThat(testElectronicsItem.getWarrantyMonths()).isEqualTo(warranty);
    }

    @Test
    @DisplayName("Конструктор должен устанавливать гарантию в 0 при отрицательном значении")
    void constructorShouldSetZeroWarrantyForNegativeInput() {
        // Arrange
        int negativeWarranty = -6;

        // Act
        ElectronicsItem itemWithNegativeWarranty = new ElectronicsItem(sku, name, quantity, price, negativeWarranty);

        // Assert
        assertThat(itemWithNegativeWarranty.getWarrantyMonths()).isZero();
    }

    @Test
    @DisplayName("Метод displayDetails должен выводить информацию о гарантии")
    void displayDetailsShouldIncludeWarranty() {
        // Этот тест проверяет, что метод выполняется без ошибок.
        // Опционально можно перенаправить вывод и проверить содержание, но здесь достаточно вызова.
        System.out.println("--- Тест displayDetails для ElectronicsItem ---");
        testElectronicsItem.displayDetails();
        System.out.println("--- Конец теста displayDetails ---");
    }

    @Test
    @DisplayName("Унаследованные методы должны работать корректно")
    void inheritedMethodsShouldWork() {
        // Arrange
        int initialQuantity = testElectronicsItem.getQuantity();
        int amountToRemove = 3;

        // Act
        boolean removed = testElectronicsItem.removeStock(amountToRemove);
        double totalValue = testElectronicsItem.getTotalValue();

        // Assert: удаление прошло успешно
        assertThat(removed).isTrue();

        // Assert: количество изменилось
        assertThat(testElectronicsItem.getQuantity()).isEqualTo(initialQuantity - amountToRemove);

        // Assert: общая стоимость рассчитывается верно
        assertThat(totalValue).isEqualTo((initialQuantity - amountToRemove) * price);
    }

}