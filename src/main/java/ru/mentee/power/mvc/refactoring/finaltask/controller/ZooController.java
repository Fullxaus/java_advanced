package ru.mentee.power.mvc.refactoring.finaltask.controller;

import ru.mentee.power.mvc.refactoring.finaltask.model.Animal;
import ru.mentee.power.mvc.refactoring.finaltask.model.AnimalDto;
import ru.mentee.power.mvc.refactoring.finaltask.behavior.MoveBehavior;
import ru.mentee.power.mvc.refactoring.finaltask.behavior.SoundBehavior;
import ru.mentee.power.mvc.refactoring.finaltask.factory.AnimalFactory;
import ru.mentee.power.mvc.refactoring.finaltask.view.AnimalView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ZooController {
    private final AnimalView view;
    private final AnimalFactory factory;
    private final List<Animal> animals = new ArrayList<>();

    public ZooController(AnimalView view, AnimalFactory factory) {
        this.view = view;
        this.factory = factory;
    }

    // initialize demo animals
    public void initDemo() {
        animals.clear();
        animals.add(factory.create("lion", Map.of("name", "Lion", "age", 5, "maneColor", "Orange")));
        animals.add(factory.create("eagle", Map.of("name", "Eagle", "age", 3)));
        animals.add(factory.create("snake", Map.of("name", "Snake", "age", 2)));

        List<AnimalDto> dtos = animals.stream().map(Animal::toDto).toList();
        view.showList(dtos);

        for (Animal a : animals) {
            view.showMessage(a.performMove());
            view.showMessage(a.performSound());
            view.showMessage("");
        }
    }

    public List<AnimalDto> listAnimals() {
        List<AnimalDto> dtos = animals.stream().map(Animal::toDto).toList();
        view.showList(dtos);
        return dtos;
    }

    public boolean changeBehavior(String animalName, String newMoveBehaviorClassName, String newSoundBehaviorClassName) {
        Optional<Animal> opt = animals.stream().filter(a -> a.getName().equalsIgnoreCase(animalName)).findFirst();
        if (opt.isEmpty()) return false;
        Animal a = opt.get();

        try {
            // dynamic load by class name (simple adapter to set new strategy)
            if (newMoveBehaviorClassName != null && !newMoveBehaviorClassName.isBlank()) {
                Class<?> c = Class.forName(newMoveBehaviorClassName);
                Object obj = c.getDeclaredConstructor().newInstance();
                if (obj instanceof MoveBehavior) a.setMoveBehavior((MoveBehavior) obj);
            }
            if (newSoundBehaviorClassName != null && !newSoundBehaviorClassName.isBlank()) {
                Class<?> c = Class.forName(newSoundBehaviorClassName);
                Object obj = c.getDeclaredConstructor().newInstance();
                if (obj instanceof SoundBehavior) a.setSoundBehavior((SoundBehavior) obj);
            }

            view.showMessage("Behavior changed for " + a.getName());
            view.showMessage(a.performMove());
            view.showMessage(a.performSound());
            return true;
        } catch (Exception e) {
            view.showMessage("Failed to change behavior: " + e.getMessage());
            return false;
        }
    }

    // expose animals for tests if needed
    public List<Animal> getAnimals() { return List.copyOf(animals); }
}
