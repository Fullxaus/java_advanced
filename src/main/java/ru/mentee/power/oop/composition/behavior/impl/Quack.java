package ru.mentee.power.oop.composition.behavior.impl;

import ru.mentee.power.oop.composition.behavior.QuackBehavior;

public class Quack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("Quack");
    }
}
