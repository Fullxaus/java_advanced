package ru.mentee.power.streams.finaltask;

import java.util.Objects;

public class Sale {
    private String saleId;
    private Customer customer;
    private Product product;
    private int quantity;

    public Sale(String saleId, Customer customer, Product product, int quantity) {
        this.saleId = saleId;
        this.customer = customer;
        this.product = product;
        this.quantity = quantity;
    }

    public String getSaleId() {
        return saleId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sale sale = (Sale) o;
        return Objects.equals(saleId, sale.saleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(saleId);
    }

    @Override
    public String toString() {
        return "Sale{" +
                "saleId='" + saleId + '\'' +
                ", customer=" + customer +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
