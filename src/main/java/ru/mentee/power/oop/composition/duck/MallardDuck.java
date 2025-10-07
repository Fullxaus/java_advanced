package ru.mentee.power.oop.composition.duck;

import ru.mentee.power.oop.composition.behavior.impl.FlyWithWings;
import ru.mentee.power.oop.composition.behavior.impl.Quack;

public class MallardDuck extends Duck {
    public MallardDuck() {
        this.flyBehavior = new FlyWithWings();
        this.quackBehavior = new Quack();
    }

    @Override
    public void display() {
        System.out.println("I'm a real Mallard duck");
    }
}
