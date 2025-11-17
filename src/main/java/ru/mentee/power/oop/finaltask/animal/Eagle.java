package ru.mentee.power.oop.finaltask.animal;

import ru.mentee.power.oop.finaltask.behavior.impl.ChirpBehavior;
import ru.mentee.power.oop.finaltask.behavior.impl.FlyBehavior;
public class Eagle extends Bird {

    private String maneColor;

    public Eagle(String name, int age) {
        super(name, age);
        setMoveBehavior(new FlyBehavior());
        setSoundBehavior(new ChirpBehavior());
    }
    public String getManeColor() {
        return maneColor;
    }
    @Override
    public void displayInfo() {
        System.out.println("Eagle: name=" + getName() + ", age=" + getAge());
    }
}
