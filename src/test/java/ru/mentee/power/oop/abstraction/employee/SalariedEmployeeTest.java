package ru.mentee.power.oop.abstraction.employee;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Тесты для SalariedEmployee")
public class SalariedEmployeeTest {
    @Test
    @DisplayName("calculateMonthlySalary должен возвращать фиксированный оклад")
    void calculateMonthlySalaryShouldReturnFixedSalary() {
        // Arrange
        SalariedEmployee emp = new SalariedEmployee(1, "Иван", 50000.0);

        // Act
        double salary = emp.calculateMonthlySalary();

        // Assert
        assertThat(salary).isEqualTo(50000.0);
    }

    @Test
    @DisplayName("Конструктор принимает отрицательный оклад (аксептируем текущее поведение)")
    void constructorAllowsNegativeSalary() {
        SalariedEmployee emp = new SalariedEmployee(2, "Пётр", -1000.0);
        assertThat(emp.getMonthlySalary()).isEqualTo(-1000.0);
    }
}
