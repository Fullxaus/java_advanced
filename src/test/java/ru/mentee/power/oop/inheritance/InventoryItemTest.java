package ru.mentee.power.oop.inheritance;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryItemTest {

    InventoryItem itemDefault;
    InventoryItem itemFull;

    @BeforeEach
    void setUp() {
        itemDefault = new InventoryItem(); // UNKNOWN_SKU, Unnamed Item
        itemFull = new InventoryItem("SKU123", "Widget", 10, 2.5);
    }

    @Test
    @DisplayName("Конструктор по умолчанию задаёт UNKNOWN_SKU, Unnamed Item и нули")
    void defaultConstructor_setsDefaults() {
        assertNotNull(itemDefault.getSku());
        assertNotNull(itemDefault.getName());
        assertEquals(0, itemDefault.getQuantity());
        assertEquals(0.0, itemDefault.getUnitPrice(), 1e-9);
        assertEquals("UNKNOWN_SKU", itemDefault.getSku());
        assertEquals("Unnamed Item", itemDefault.getName());
    }

    @Test
    @DisplayName("Конструктор с параметрами и геттеры инициализируют поля")
    void threeArgConstructor_andGetters() {
        assertEquals("SKU123", itemFull.getSku());
        assertEquals("Widget", itemFull.getName());
        assertEquals(10, itemFull.getQuantity());
        assertEquals(2.5, itemFull.getUnitPrice(), 1e-9);
    }

    @Test
    @DisplayName("Отрицательные quantity и unitPrice приводятся к нулю")
    void constructor_negativeValues_clampsToZero() {
        InventoryItem negative = new InventoryItem("N1", "Neg", -5, -3.0);
        assertEquals(0, negative.getQuantity());
        assertEquals(0.0, negative.getUnitPrice(), 1e-9);
    }

    @Test
    @DisplayName("Сеттеры обновляют sku, name, quantity и unitPrice")
    void setAndGetters_work() {
        InventoryItem it = new InventoryItem("A", "B", 1, 1.0);
        it.setSku("NEW");
        it.setName("NewName");
        it.setQuantity(5);
        it.setUnitPrice(4.75);

        assertEquals("NEW", it.getSku());
        assertEquals("NewName", it.getName());
        assertEquals(5, it.getQuantity());
        assertEquals(4.75, it.getUnitPrice(), 1e-9);
    }

    @Test
    @DisplayName("addStock с положительным значением увеличивает количество")
    void addStock_positiveIncreasesQuantity() {
        itemFull.addStock(5);
        assertEquals(15, itemFull.getQuantity());
    }

    @Test
    @DisplayName("addStock с неположительным значением не изменяет количество")
    void addStock_nonPositive_doesNotChangeQuantity() {
        int before = itemFull.getQuantity();
        itemFull.addStock(0);
        assertEquals(before, itemFull.getQuantity());
        itemFull.addStock(-3);
        assertEquals(before, itemFull.getQuantity());
    }

    @Test
    @DisplayName("removeStock при достаточном запасе уменьшает количество и возвращает true")
    void removeStock_successfulReduction() {
        boolean result = itemFull.removeStock(4);
        assertTrue(result);
        assertEquals(6, itemFull.getQuantity());
    }

    @Test
    @DisplayName("removeStock при недостаточном запасе возвращает false и не меняет количество")
    void removeStock_insufficientStock_returnsFalseAndUnchanged() {
        InventoryItem small = new InventoryItem("S", "Small", 2, 1.0);
        boolean result = small.removeStock(5);
        assertFalse(result);
        assertEquals(2, small.getQuantity());
    }

    @Test
    @DisplayName("removeStock с неположительным значением возвращает false и не меняет количество")
    void removeStock_nonPositive_returnsFalseAndUnchanged() {
        InventoryItem it = new InventoryItem("T", "Test", 3, 1.0);
        assertFalse(it.removeStock(0));
        assertFalse(it.removeStock(-1));
        assertEquals(3, it.getQuantity());
    }

    @Test
    @DisplayName("getTotalValue возвращает произведение quantity на unitPrice")
    void getTotalValue_computesCorrectly() {
        InventoryItem it = new InventoryItem("V", "Val", 4, 2.5);
        assertEquals(10.0, it.getTotalValue(), 1e-9);
    }

    @Test
    @DisplayName("equals и hashCode считают объекты с одинаковыми полями равными")
    void equals_and_hashCode_contract() {
        InventoryItem a = new InventoryItem("E1", "Eq", 3, 1.5);
        InventoryItem b = new InventoryItem("E1", "Eq", 3, 1.5);
        InventoryItem c = new InventoryItem("E1", "Eq", 4, 1.5);

        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());

        assertNotEquals(a, c);
        assertNotEquals(a.hashCode(), c.hashCode());
        assertNotEquals(a, null);
        assertNotEquals(a, "some string");
    }

    @Test
    @DisplayName("toString содержит sku, name, quantity и unitPrice")
    void toString_containsKeyFields() {
        String s = itemFull.toString();
        assertTrue(s.contains("SKU123"));
        assertTrue(s.contains("Widget"));
        assertTrue(s.contains("10"));
        assertTrue(s.contains("2.5"));
    }
}
