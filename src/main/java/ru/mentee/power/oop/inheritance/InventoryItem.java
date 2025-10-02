package ru.mentee.power.oop.inheritance;
import java.util.Objects;

public class InventoryItem {
    protected String sku; // Изменим на protected для доступа из наследников
    protected String name;
    protected int quantity;
    protected double unitPrice;

    public InventoryItem() {
        this("UNKNOWN_SKU", "Unnamed Item");
        System.out.println("[DEBUG] InventoryItem: Конструктор по умолчанию вызван.");
    }

    @Override
    public String toString() {
        return "InventoryItem{" +
                "sku='" + sku + '\'' +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                '}';
    }

    public InventoryItem(String sku, String name) {
        this(sku, name, 0, 0.0);
        System.out.println("[DEBUG] InventoryItem: Конструктор (sku, name) вызван.");
    }

    public InventoryItem(String sku, String name, int quantity, double unitPrice) {
        this.sku = Objects.requireNonNull(sku, "SKU не может быть null");
        this.name = Objects.requireNonNull(name, "Имя не может быть null");
        this.quantity = quantity >= 0 ? quantity : 0;
        this.unitPrice = unitPrice >= 0 ? unitPrice : 0.0;
        System.out.println("[DEBUG] InventoryItem: Основной конструктор (4 параметра) вызван для " + sku);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventoryItem that = (InventoryItem) o;
        return quantity == that.quantity && Double.compare(unitPrice, that.unitPrice) == 0 && Objects.equals(sku, that.sku) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku, name, quantity, unitPrice);
    }

    // Геттеры (можно сделать public)
    public String getSku() { return sku; }
    public String getName() { return name; }
    public int getQuantity() { return quantity; }
    public double getUnitPrice() { return unitPrice; }


    public void displayDetails() {
        System.out.println("--- Детали Товара ---");
        System.out.println("SKU: " + sku);
        System.out.println("Название: " + name);
        System.out.println("Количество: " + quantity);
        System.out.printf("Цена за единицу: %.2f ", unitPrice);
                System.out.printf("Общая стоимость: %.2f ", getTotalValue());
    }

    public void addStock(int amount) {
        if (amount > 0) {
            this.quantity += amount;
            System.out.println(amount + " единиц добавлено. Новое количество " + this.name + ": " + this.quantity);
        } else {
            System.err.println("Ошибка: Количество для добавления должно быть положительным.");
        }
    }

    public boolean removeStock(int amount) {
        if (amount <= 0) {
            System.err.println("Ошибка: Количество для снятия должно быть положительным.");
            return false;
        }
        if (this.quantity >= amount) {
            this.quantity -= amount;
            System.out.println(amount + " единиц снято. Новое количество " + this.name + ": " + this.quantity);
            return true;
        } else {
            System.err.println("Ошибка: Недостаточно товара '" + this.name + "' на складе (в наличии: " + this.quantity + ", требуется: " + amount + ").");
            return false;
        }
    }

    public double getTotalValue() {
        return this.quantity * this.unitPrice;
    }


}
