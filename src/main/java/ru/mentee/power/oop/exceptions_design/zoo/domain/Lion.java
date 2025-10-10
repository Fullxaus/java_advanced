package ru.mentee.power.oop.exceptions_design.zoo.domain;

import ru.mentee.power.oop.exceptions_design.zoo.exceptions.UnsupportedFoodException;

public class Lion extends Animal {
    public Lion(String name) {
        super(name, "Lion");
    }

    @Override
    public void eat(String foodType) throws UnsupportedFoodException {
        // Лев ест "мясо"
        if (!"мясо".equalsIgnoreCase(foodType) && !"meat".equalsIgnoreCase(foodType)) {
            throw new UnsupportedFoodException(getName(), foodType,
                    "Лев '" + getName() + "' не может есть: " + foodType);
        }

    }
}
