package ru.mentee.power.oop.constructors;

public class InventoryItem {

    String sku;
    String name;
    int quantity;
    double unitPrice;

    // Основной конструктор
    public InventoryItem(String sku, String name, int quantity, double unitPrice) {
        this.sku = sku;
        this.name = name;
        // валидация: quantity и unitPrice не могут быть отрицательными
        this.quantity = quantity < 0 ? 0 : quantity;
        this.unitPrice = unitPrice < 0 ? 0.0 : unitPrice;
    }

    // Конструктор (sku, name) — вызывает основной, передавая 0 для quantity и unitPrice
    public InventoryItem(String sku, String name) {
        this(sku, name, 0, 0.0);
    }

    // Конструктор по умолчанию — вызывает (sku, name) с значениями по умолчанию
    public InventoryItem() {
        this("UNKNOWN_SKU", "Unnamed Item");
    }

    // displayDetails(): вывод информации о товаре
    public void displayDetails() {
        System.out.println("SKU: " + sku);
        System.out.println("Name: " + name);
        System.out.println("Quantity: " + quantity);
        System.out.println("Unit Price: " + unitPrice);
        System.out.println("Total Value: " + getTotalValue());
        System.out.println("-----------------------------");
    }

    // addStock(int amount): увеличивает количество (если amount > 0)
    public boolean addStock(int amount) {
        if (amount <= 0) {
            System.out.println("addStock: amount must be > 0");
            return false;
        }
        this.quantity += amount;
        return true;
    }

    // removeStock(int amount): уменьшает количество (если amount > 0 и достаточно на складе)
    public boolean removeStock(int amount) {
        if (amount <= 0) {
            System.out.println("removeStock: amount must be > 0");
            return false;
        }
        if (this.quantity < amount) {
            System.out.println("removeStock: not enough stock to remove " + amount);
            return false;
        }
        this.quantity -= amount;
        return true;
    }

    // getTotalValue(): возвращает quantity * unitPrice
    public double getTotalValue() {
        return this.quantity * this.unitPrice;
    }
}
