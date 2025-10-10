package ru.mentee.power.oop.exceptions_design.zoo.domain;

import ru.mentee.power.oop.exceptions_design.zoo.exceptions.UnsupportedFoodException;

public abstract class Animal {
    private final String name;
    private final String type; // e.g., "Lion", "Monkey"

    protected Animal(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public abstract void eat(String foodType) throws UnsupportedFoodException;

    public void performEat(String foodType) {
        try {
            eat(foodType);
            System.out.println(name + " успешно съел(а) " + foodType + ".");
        } catch (UnsupportedFoodException e) {
            System.err.println("Ошибка кормления: " + e.getMessage()
                    + " (animal=" + e.getAnimalName() + ", food=" + e.getFoodType() + ")");
        }
    }

    @Override
    public String toString() {
        return type + "('" + name + "')";
    }
}
