package ru.mentee.power.streams;

import java.util.Objects;

public class Transaction {
    private String id;
    private double amount;
    private String currency;
    private String city;

    public Transaction(String id, double amount, String currency, String city) {
        this.id = id;
        this.amount = amount;
        this.currency = currency;
        this.city = city;
    }

    public String getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getCity() {
        return city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}

