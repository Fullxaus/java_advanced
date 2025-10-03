package ru.mentee.power.oop.abstraction.employee;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Тесты для HourlyEmployee")
public class HourlyEmployeeTest {
    private HourlyEmployee emp;
    private final double hourlyRate = 450.0;

    @BeforeEach
    void setUp() {
        emp = new HourlyEmployee(1, "Ivan", hourlyRate);
    }

    @Test
    @DisplayName("calculateMonthlySalary должен возвращать 0, если часы не залогированы")
    void calculateSalaryShouldBeZeroIfNoHoursLogged() {
        assertThat(emp.getHoursWorked()).isEqualTo(0);
        assertThat(emp.calculateMonthlySalary()).isEqualTo(0.0);
    }

    @Test
    @DisplayName("calculateMonthlySalary должен корректно считать ЗП после логирования часов")
    void calculateSalaryShouldBeCorrectAfterLoggingHours() {
        emp.logHours(10);
        assertThat(emp.getHoursWorked()).isEqualTo(10);
        assertThat(emp.calculateMonthlySalary()).isEqualTo(10 * hourlyRate);
    }

    @Test
    @DisplayName("logHours должен игнорировать не положительные значения часов")
    void logHoursShouldIgnoreNonPositiveValues() {
        emp.logHours(-5);
        emp.logHours(0);
        // hours should remain 0
        assertThat(emp.getHoursWorked()).isEqualTo(0);
        assertThat(emp.calculateMonthlySalary()).isEqualTo(0.0);
    }

    @Test
    @DisplayName("logHours должен корректно суммировать часы")
    void logHoursShouldSumHoursCorrectly() {
        emp.logHours(3);
        emp.logHours(7);
        emp.logHours(5);
        assertThat(emp.getHoursWorked()).isEqualTo(15);
        assertThat(emp.calculateMonthlySalary()).isEqualTo(15 * hourlyRate);
    }

    @Test
    @DisplayName("Конструктор должен принимать отрицательную ставку (текущее поведение)")
    void constructorAllowsNegativeRate_currentBehavior() {
        HourlyEmployee e = new HourlyEmployee(2, "Petr", -100.0);
        assertThat(e).isNotNull();
        assertThat(e.getHourlyRate()).isEqualTo(-100.0);
        assertThat(e.getHoursWorked()).isEqualTo(0);
    }

}
