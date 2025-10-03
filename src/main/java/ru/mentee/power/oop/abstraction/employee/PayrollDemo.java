package ru.mentee.power.oop.abstraction.employee;

import java.util.ArrayList;
import java.util.List;

public class PayrollDemo {
    public static void main(String[] args) {
        System.out.println("--- Расчетный Лист ---");
        List<Employee> employees = new ArrayList<>();

        employees.add(new SalariedEmployee(1, "Иван Иванов", 50000.0));
        employees.add(new SalariedEmployee(2, "Мария Петрова", 65000.0));
        employees.add(new HourlyEmployee(3, "Пётр Сидоров", 250.0));
        employees.add(new HourlyEmployee(4, "Анна Смирнова", 300.0));

        // Логируем часы для почасовых
        for (Employee e : employees) {
            if (e instanceof HourlyEmployee) {
                HourlyEmployee he = (HourlyEmployee) e;
                // Пример: добавим разные часы
                if (he.getId() == 3) he.logHours(160); // Пётр — 160 часов
                if (he.getId() == 4) he.logHours(120); // Анна — 120 часов
            }
        }

        System.out.println("\n--- Информация о работниках и их ЗП за месяц ---");
        for (Employee e : employees) {
            e.displayInfo();
            System.out.println("Monthly salary: " + e.calculateMonthlySalary());
            System.out.println("----------------------------");
        }

        System.out.println("\n--- Расчет завершен ---");
    }
}