package ru.mentee.power.oop.abstraction.employee;

public class SalariedEmployee extends Employee {
    public double getMonthlySalary() {
        return monthlySalary;
    }

    private double monthlySalary;

    public SalariedEmployee(int id, String name, double monthlySalary) {
        super(id, name);
        this.monthlySalary = monthlySalary;
    }

    @Override
    public double calculateMonthlySalary() {
        return monthlySalary;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Type: SalariedEmployee, Monthly Salary: " + monthlySalary);
    }
}