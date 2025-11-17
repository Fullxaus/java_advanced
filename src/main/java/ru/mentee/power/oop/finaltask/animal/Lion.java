package ru.mentee.power.oop.finaltask.animal;

import ru.mentee.power.oop.finaltask.behavior.impl.WalkBehavior;
import ru.mentee.power.oop.finaltask.behavior.impl.RoarBehavior;

public class Lion extends Animal {
    private String maneColor;

    public Lion(String name, int age, String maneColor) {
        super(name, age);
        this.maneColor = maneColor;
        setMoveBehavior(new WalkBehavior());
        setSoundBehavior(new RoarBehavior());
    }

    @Override
    public void displayInfo() {
        System.out.println("Lion: name=" + getName() + ", age=" + getAge() + ", maneColor=" + maneColor);
    }
    public String getManeColor() {
        return maneColor;
    }
}