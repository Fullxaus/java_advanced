package ru.mentee.power.oop.finaltask.animal;

import ru.mentee.power.oop.finaltask.behavior.impl.HissBehavior;
import ru.mentee.power.oop.finaltask.behavior.impl.SlitherBehavior;

public class Snake extends Animal {
    private String maneColor;
    public Snake(String name, int age) {
        super(name, age);
        setMoveBehavior(new SlitherBehavior());
        setSoundBehavior(new HissBehavior());
    }
    public String getManeColor() {
        return maneColor;
    }
    @Override
    public void displayInfo() {
        System.out.println("Snake: name=" + getName() + ", age=" + getAge());
    }
}
