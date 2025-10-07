package ru.mentee.power.oop.finaltask.behavior.impl;
import ru.mentee.power.oop.finaltask.behavior.MoveBehavior;

public class SlitherBehavior implements MoveBehavior {
    @Override
    public void move() {
        System.out.println("Slithering along the ground.");
    }
}
