package ru.mentee.power.oop.finaltask.behavior.impl;

import ru.mentee.power.oop.finaltask.behavior.MoveBehavior;

public class WalkBehavior implements MoveBehavior {
    @Override
    public void move() {
        System.out.println("Walking on the ground.");
    }
}
