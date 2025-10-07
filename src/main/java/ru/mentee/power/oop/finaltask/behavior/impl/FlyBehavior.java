package ru.mentee.power.oop.finaltask.behavior.impl;
import ru.mentee.power.oop.finaltask.behavior.MoveBehavior;

public class FlyBehavior implements MoveBehavior {
    @Override
    public void move() {
        System.out.println("Flying through the sky.");
    }
}
