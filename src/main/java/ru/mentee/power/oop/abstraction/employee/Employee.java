package ru.mentee.power.oop.abstraction.employee;

    import java.util.Objects;

public abstract class Employee {
    private final int id;
    private String name;

    public Employee(int id, String name) {
        if (id <= 0) {
            throw new IllegalArgumentException("id must be > 0");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name must not be null or blank");
        }
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public abstract double calculateMonthlySalary();

    public void displayInfo() {
        System.out.println("ID: " + id + ", Name: " + name);
    }

    @Override
    public String toString() {
        return "Employee{id=" + id + ", name='" + name + "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return id == employee.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
