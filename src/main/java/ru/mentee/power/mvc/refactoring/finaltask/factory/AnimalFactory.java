package ru.mentee.power.mvc.refactoring.finaltask.factory;

import ru.mentee.power.mvc.refactoring.finaltask.model.Animal;

import java.util.Map;

public interface AnimalFactory {
    /**
     * Create animal of given type with params.
     * type examples: "lion", "eagle", "snake"
     * params map keys: name (required), age (optional, as Integer or String), other keys depend on type
     */
    Animal create(String type, Map<String, Object> params);
}
