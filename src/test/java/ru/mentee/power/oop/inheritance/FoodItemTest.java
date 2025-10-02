package ru.mentee.power.oop.inheritance;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Тесты для класса FoodItem")
public class FoodItemTest {

    private FoodItem testFoodItem;
    private final String sku = "FOOD01";
    private final String name = "Йогурт";
    private final int quantity = 20;
    private final double price = 55.0;
    private final LocalDate expiryDate = LocalDate.now().plusDays(10);

    @BeforeEach
    void setUp() {
        testFoodItem = new FoodItem(sku, name, quantity, price, expiryDate);
    }

    @Test
    @DisplayName("Конструктор должен корректно инициализировать все поля")
    void constructorShouldInitializeFieldsCorrectly() {
        assertThat(testFoodItem).isNotNull();
        assertThat(testFoodItem.getSku()).isEqualTo(sku);
        assertThat(testFoodItem.getName()).isEqualTo(name);
        assertThat(testFoodItem.getQuantity()).isEqualTo(quantity);
        assertThat(testFoodItem.getExpirationDate()).isEqualTo(expiryDate);
        assertThat(testFoodItem.getUnitPrice()).isEqualTo(price);
    }

    @Test
    @DisplayName("Метод displayDetails должен включать информацию о сроке годности")
    void displayDetailsShouldIncludeExpiryDate() {
        // Перехватываем System.out и проверяем, что в выводе присутствует дата истечения
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(out));
        try {
            testFoodItem.displayDetails();
        } finally {
            System.setOut(originalOut);
        }
        String printed = out.toString();
        assertThat(printed).contains(expiryDate.toString());
        // Дополнительно можно проверить имя и SKU в выводе, если требуется
        assertThat(printed).contains(sku).contains(name);
    }

    @Test
    @DisplayName("Унаследованные методы addStock и getTotalValue должны работать корректно")
    void inheritedMethodsShouldWork() {
        int initialQuantity = testFoodItem.getQuantity();
        int amountToAdd = 5;

        testFoodItem.addStock(amountToAdd);
        double totalValue = testFoodItem.getTotalValue();

        assertThat(testFoodItem.getQuantity()).isEqualTo(initialQuantity + amountToAdd);
        assertThat(totalValue).isEqualTo((initialQuantity + amountToAdd) * price);
    }

    @Test
    @DisplayName("Конструктор должен выбрасывать NullPointerException, если expirationDate == null")
    void constructorShouldThrowExceptionIfDateIsNull() {
        assertThatThrownBy(() -> new FoodItem(sku, name, quantity, price, null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("expiration"); // адаптируйте сообщение под реализацию ("Срок годности" и т.п.)
    }
}
