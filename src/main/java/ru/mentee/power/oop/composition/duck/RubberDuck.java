package ru.mentee.power.oop.composition.duck;

import ru.mentee.power.oop.composition.behavior.impl.FlyNoWay;
import ru.mentee.power.oop.composition.behavior.impl.Squeak;

public class RubberDuck extends Duck {
    public RubberDuck() {
        this.flyBehavior = new FlyNoWay();
        this.quackBehavior = new Squeak();
    }

    @Override
    public void display() {
        System.out.println("I'm a rubber duck");
    }
}
