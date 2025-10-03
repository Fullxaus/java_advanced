package ru.mentee.power.oop.inheritance;

import java.util.Objects;

public class ElectronicsItem extends InventoryItem {
    private int warrantyMonths;

    public ElectronicsItem(String sku, String name, int quantity, double unitPrice, int warrantyMonths) {
        super(sku, name, quantity, unitPrice);
        this.warrantyMonths = Math.max(0, warrantyMonths);
        System.out.println("[DEBUG] ElectronicsItem: Конструктор вызван для " + sku);
    }

    public int getWarrantyMonths() {
        return warrantyMonths;
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Гарантия (мес.): " + warrantyMonths);
    }

    @Override
    public String toString() {
        return "ElectronicsItem{" +
                "sku='" + sku + '\'' +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", warrantyMonths=" + warrantyMonths +
                '}';
    }
}
