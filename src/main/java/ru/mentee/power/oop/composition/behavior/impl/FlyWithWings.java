package ru.mentee.power.oop.composition.behavior.impl;

import ru.mentee.power.oop.composition.behavior.FlyBehavior;

public class FlyWithWings implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("I'm flying with wings!");
    }
}