package ru.mentee.power.oop.inheritance;

import java.time.LocalDate;
import java.util.Objects;

public class FoodItem extends InventoryItem {
    private LocalDate expirationDate;

    public FoodItem(String sku, String name, int quantity, double unitPrice, LocalDate expirationDate) {
        super(sku, name, quantity, unitPrice);
        this.expirationDate = Objects.requireNonNull(expirationDate, "expirationDate не может быть null");
        System.out.println("[DEBUG] FoodItem: Конструктор вызван для " + sku);
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Срок годности: " + expirationDate);
    }

    @Override
    public String toString() {
        return "FoodItem{" +
                "sku='" + sku + '\'' +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", expirationDate=" + expirationDate +
                '}';
    }
}
