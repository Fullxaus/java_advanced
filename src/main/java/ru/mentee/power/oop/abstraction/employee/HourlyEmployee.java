package ru.mentee.power.oop.abstraction.employee;

public class HourlyEmployee extends Employee {
    private double hourlyRate;
    private int hoursWorked;

    public double getHourlyRate() {
        return hourlyRate;
    }

    public int getHoursWorked() {
        return hoursWorked;
    }

    public HourlyEmployee(int id, String name, double hourlyRate) {
        super(id, name);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = 0;
    }

    @Override
    public double calculateMonthlySalary() {
        return hourlyRate * hoursWorked;
    }

    public void logHours(int hours) {
        if (hours <= 0) {
            System.out.println("logHours: hours must be > 0. Ignored: " + hours);
            return;
        }
        this.hoursWorked += hours;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Type: HourlyEmployee, Hourly Rate: " + hourlyRate + ", Hours Worked: " + hoursWorked);
    }
}
