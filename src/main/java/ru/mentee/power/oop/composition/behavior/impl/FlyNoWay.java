package ru.mentee.power.oop.composition.behavior.impl;

import ru.mentee.power.oop.composition.behavior.FlyBehavior;

public class FlyNoWay implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("I can't fly.");
    }
}