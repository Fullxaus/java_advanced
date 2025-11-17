package ru.mentee.power.mvc.view;

import ru.mentee.power.mvc.model.Student;

import java.util.List;
import java.util.Scanner;

public class StudentConsoleView {
    private final Scanner scanner;

    public StudentConsoleView() {
        this.scanner = new Scanner(System.in, "UTF-8");
    }

    public void displayMenu() {
        System.out.println("\n--- 👨‍🎓 Меню Управления Студентами 👩‍🎓 ---");
        System.out.println("1. Добавить студента");
        System.out.println("2. Показать всех студентов");
        System.out.println("0. Выход");
        System.out.print("Ваш выбор (введите номер): ");
    }

    public String getCommand() {
        return scanner.nextLine().trim();
    }

    public String promptForStudentName() {
        System.out.print("Введите имя студента: ");
        return scanner.nextLine().trim();
    }

    public void displayStudentList(List<Student> students) {
        System.out.println("\n--- 📜 Список студентов ---");
        if (students == null || students.isEmpty()) {
            System.out.println("Список пока пуст. Добавьте первого студента!");
        } else {
            students.forEach(s -> System.out.println("ID: " + s.getId() + ", Имя: " + s.getName()));
        }
        System.out.println("--------------------------");
    }

    public void displayMessage(String message) {
        System.out.println("✅ Сообщение: " + message);
    }

    public void displayError(String errorMessage) {
        System.err.println("❌ Ошибка: " + errorMessage);
    }
}
