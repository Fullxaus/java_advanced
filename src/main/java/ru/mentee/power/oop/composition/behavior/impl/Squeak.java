package ru.mentee.power.oop.composition.behavior.impl;

import ru.mentee.power.oop.composition.behavior.QuackBehavior;

public class Squeak implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("Squeak");
    }
}
