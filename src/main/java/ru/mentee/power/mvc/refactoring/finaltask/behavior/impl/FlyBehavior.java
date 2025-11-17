package ru.mentee.power.mvc.refactoring.finaltask.behavior.impl;

import ru.mentee.power.mvc.refactoring.finaltask.behavior.MoveBehavior;

public class FlyBehavior implements MoveBehavior {
    @Override
    public String move() {
        return "Flying through the sky.";
    }
}
