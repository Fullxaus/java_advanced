package ru.mentee.power.mvc.refactoring.finaltask.factory;

import ru.mentee.power.mvc.refactoring.finaltask.model.Animal;
import ru.mentee.power.mvc.refactoring.finaltask.model.Eagle;
import ru.mentee.power.mvc.refactoring.finaltask.model.Lion;
import ru.mentee.power.mvc.refactoring.finaltask.model.Snake;


import java.util.Map;

public class DefaultAnimalFactory implements AnimalFactory {

    @Override
    public Animal create(String type, Map<String, Object> params) {
        String name = (String) params.getOrDefault("name", "Unknown");
        Object ageObj = params.getOrDefault("age", 0);
        int age = 0;
        if (ageObj instanceof Integer) age = (Integer) ageObj;
        else {
            try { age = Integer.parseInt(String.valueOf(ageObj)); } catch (Exception ignored) {}
        }

        switch (type.toLowerCase()) {
            case "lion":
                String maneColor = (String) params.getOrDefault("maneColor", "unknown");
                return new Lion(name, age, maneColor);
            case "eagle":
                return new Eagle(name, age);
            case "snake":
                return new Snake(name, age);
            default:
                throw new IllegalArgumentException("Unknown animal type: " + type);
        }
    }
}
