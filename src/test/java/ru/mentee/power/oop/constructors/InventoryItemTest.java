package ru.mentee.power.oop.constructors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Тесты для класса InventoryItem (с конструкторами)")
public class InventoryItemTest {
    @Test
    @DisplayName("Основной конструктор инициализирует все поля корректно")
    void mainConstructorShouldInitializeFields() {
        // Arrange
        String sku = "SKU001";
        String name = "Тестовый Товар";
        int quantity = 10;
        double price = 99.99;

        // Act
        InventoryItem item = new InventoryItem(sku, name, quantity, price);

        // Assert
        assertThat(item).isNotNull();
        assertThat(item.sku).isEqualTo(sku);
        assertThat(item.name).isEqualTo(name);
        assertThat(item.quantity).isEqualTo(quantity);
        assertThat(item.unitPrice).isEqualTo(price);
    }

    @Test
    @DisplayName("Конструктор (sku, name) устанавливает quantity и price в 0")
    void skuNameConstructorShouldSetDefaults() {
        // Arrange
        String sku = "SKU002";
        String name = "Товар Без Цены";

        // Act
        InventoryItem item = new InventoryItem(sku, name);

        // Assert
        assertThat(item).isNotNull();
        assertThat(item.sku).isEqualTo(sku);
        assertThat(item.name).isEqualTo(name);
        assertThat(item.quantity).isZero();
        assertThat(item.unitPrice).isZero();
    }

    @Test
    @DisplayName("Конструктор по умолчанию устанавливает дефолтные sku и name")
    void defaultConstructorShouldSetDefaults() {
        // Act
        InventoryItem item = new InventoryItem();

        // Assert
        assertThat(item).isNotNull();
        assertThat(item.sku).isEqualTo("UNKNOWN_SKU");
        assertThat(item.name).isEqualTo("Unnamed Item");
        assertThat(item.quantity).isZero();
        assertThat(item.unitPrice).isZero();
    }

    @Test
    @DisplayName("Основной конструктор обрабатывает отрицательные quantity и price")
    void mainConstructorShouldHandleNegativeValues() {
        // Act
        InventoryItem item = new InventoryItem("SKU003", "Негатив", -5, -10.0);

        // Assert
        assertThat(item).isNotNull();
        assertThat(item.quantity).isZero();
        assertThat(item.unitPrice).isZero();
    }

    // Дополнительные тесты (опционально)
    @Test
    @DisplayName("addStock увеличивает количество только при положительном значении")
    void addStockShouldIncreaseWhenPositive() {
        InventoryItem item = new InventoryItem("SKU010", "Добавка", 5, 1.0);

        boolean ok = item.addStock(10);
        assertThat(ok).isTrue();
        assertThat(item.quantity).isEqualTo(15);

        boolean bad = item.addStock(0);
        assertThat(bad).isFalse();
        assertThat(item.quantity).isEqualTo(15);
    }

    @Test
    @DisplayName("removeStock уменьшает количество при достаточном запасе и позитивном значении")
    void removeStockShouldWorkCorrectly() {
        InventoryItem item = new InventoryItem("SKU011", "Убавка", 8, 2.0);

        boolean ok = item.removeStock(3);
        assertThat(ok).isTrue();
        assertThat(item.quantity).isEqualTo(5);

        boolean notEnough = item.removeStock(10);
        assertThat(notEnough).isFalse();
        assertThat(item.quantity).isEqualTo(5);

        boolean bad = item.removeStock(0);
        assertThat(bad).isFalse();
        assertThat(item.quantity).isEqualTo(5);
    }

    @Test
    @DisplayName("getTotalValue возвращает quantity * unitPrice")
    void getTotalValueShouldCalculateCorrectly() {
        InventoryItem item = new InventoryItem("SKU012", "Стоимость", 4, 2.5);
        assertThat(item.getTotalValue()).isEqualTo(10.0);
    }

}