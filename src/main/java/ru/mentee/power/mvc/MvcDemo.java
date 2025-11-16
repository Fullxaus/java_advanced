package ru.mentee.power.mvc;

import ru.mentee.power.mvc.controller.StudentController;
import ru.mentee.power.mvc.model.InMemoryStudentModel;
import ru.mentee.power.mvc.model.StudentModel;
import ru.mentee.power.mvc.view.StudentConsoleView;

public class MvcDemo {
    public static void main(String[] args) {
        System.out.println("--- Запуск MVC приложения (Консоль) --- ");
        StudentModel model = new InMemoryStudentModel();
        StudentConsoleView view = new StudentConsoleView();
        StudentController controller = new StudentController(model, view);
        controller.run();
        System.out.println("--- Приложение завершено --- ");
    }
}
