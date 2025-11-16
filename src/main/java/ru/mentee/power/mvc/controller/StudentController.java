package ru.mentee.power.mvc.controller;

import ru.mentee.power.mvc.model.Student;
import ru.mentee.power.mvc.model.StudentModel;
import ru.mentee.power.mvc.view.StudentConsoleView;

import java.util.List;

public class StudentController {
    private final StudentModel model;
    private final StudentConsoleView view;

    public StudentController(StudentModel model, StudentConsoleView view) {
        this.model = model;
        this.view = view;
    }

    public void run() {
        boolean running = true;
        while (running) {
            view.displayMenu();
            String command = view.getCommand();

            switch (command) {
                case "1":
                    String name = view.promptForStudentName();
                    if (name != null && !name.isBlank()) {
                        Student newStudent = Student.builder().name(name).build();
                        model.addStudent(newStudent);
                        long id = findStudentIdByName(name);
                        view.displayMessage("Студент \"" + name + "\" успешно добавлен с ID: " + id);
                    } else {
                        view.displayError("Имя студента не может быть пустым. Попробуйте еще раз.");
                    }
                    break;
                case "2":
                    List<Student> students = model.getAllStudents();
                    view.displayStudentList(students);
                    break;
                case "0":
                    running = false;
                    view.displayMessage("Спасибо за использование! До новых встреч!");
                    break;
                default:
                    view.displayError("Неизвестная команда: " + command + ". Пожалуйста, выберите пункт из меню.");
                    break;
            }
        }
    }

    private long findStudentIdByName(String name) {
        return model.getAllStudents().stream()
                .filter(s -> s.getName().equals(name))
                .mapToLong(Student::getId)
                .findFirst()
                .orElse(-1L);
    }
}
