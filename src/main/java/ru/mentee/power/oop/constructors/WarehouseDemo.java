package ru.mentee.power.oop.constructors;

public class WarehouseDemo {
    public static void main(String[] args) {
        // 1) Использование основного конструктора
        InventoryItem item1 = new InventoryItem("SKU123", "USB Cable", 50, 2.99);
        item1.displayDetails();

        // 2) Использование конструктора (sku, name)
        InventoryItem item2 = new InventoryItem("SKU456", "Mouse");
        item2.displayDetails();

        // 3) Использование конструктора по умолчанию
        InventoryItem item3 = new InventoryItem();
        item3.displayDetails();

        // Демонстрация addStock / removeStock
        System.out.println("Adding 20 to item2:");
        item2.addStock(20);
        item2.displayDetails();

        System.out.println("Removing 10 from item1:");
        item1.removeStock(10);
        item1.displayDetails();

        System.out.println("Trying to remove too many from item3:");
        item3.removeStock(5); // ожидается сообщение об ошибке, т.к. quantity == 0

        System.out.println("Trying invalid add/remove:");
        item1.addStock(0);   // некорректно
        item1.removeStock(-5); // некорректно
    }
}
