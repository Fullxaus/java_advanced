package ru.mentee.power.oop.exceptions_design.zoo.domain;

import ru.mentee.power.oop.exceptions_design.zoo.exceptions.UnsupportedFoodException;

public class Monkey extends Animal {
    public Monkey(String name) {
        super(name, "Monkey");
    }

    @Override
    public void eat(String foodType) throws UnsupportedFoodException {
        // Обезьяна ест "бананы"
        if (!"бананы".equalsIgnoreCase(foodType) && !"bananas".equalsIgnoreCase(foodType)) {
            throw new UnsupportedFoodException(getName(), foodType,
                    "Обезьяна '" + getName() + "' не может есть: " + foodType);
        }
    }
}
