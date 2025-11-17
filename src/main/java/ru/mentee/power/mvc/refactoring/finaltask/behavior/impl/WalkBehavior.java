package ru.mentee.power.mvc.refactoring.finaltask.behavior.impl;

import ru.mentee.power.mvc.refactoring.finaltask.behavior.MoveBehavior;

public class WalkBehavior implements MoveBehavior {
    @Override
    public String move() {
        return "Walking on the ground.";
    }
}
