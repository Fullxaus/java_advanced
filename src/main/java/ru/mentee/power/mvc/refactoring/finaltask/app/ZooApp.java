package ru.mentee.power.mvc.refactoring.finaltask.app;

import ru.mentee.power.mvc.refactoring.finaltask.controller.ZooController;
import ru.mentee.power.mvc.refactoring.finaltask.factory.AnimalFactory;
import ru.mentee.power.mvc.refactoring.finaltask.factory.DefaultAnimalFactory;
import ru.mentee.power.mvc.refactoring.finaltask.view.AnimalView;
import ru.mentee.power.mvc.refactoring.finaltask.view.ConsoleAnimalView;

public class ZooApp {
    public static void main(String[] args) {
        AnimalView view = new ConsoleAnimalView();
        AnimalFactory factory = new DefaultAnimalFactory();
        ZooController controller = new ZooController(view, factory);

        controller.initDemo();

        controller.changeBehavior("Lion",
                "Running",
                "Rrrrr");
    }
}
