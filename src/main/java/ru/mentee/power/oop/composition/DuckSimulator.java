package ru.mentee.power.oop.composition;

import ru.mentee.power.oop.composition.behavior.impl.FlyWithWings;
import ru.mentee.power.oop.composition.duck.*;
public class DuckSimulator {
    public static void main(String[] args) {
        Duck mallard = new MallardDuck();
        Duck rubber  = new RubberDuck();
        Duck decoy   = new DecoyDuck();
        Duck model   = new ModelDuck();

        mallard.display();
        mallard.performQuack();
        mallard.performFly();
        mallard.swim();
        System.out.println("----");

        rubber.display();
        rubber.performQuack();
        rubber.performFly();
        rubber.swim();
        System.out.println("----");

        decoy.display();
        decoy.performQuack();
        decoy.performFly();
        decoy.swim();
        System.out.println("----");

        model.display();
        model.performQuack();
        model.performFly();
        model.swim();
        System.out.println("-> Now teach the model duck to fly with a rocket!");
        model.setFlyBehavior(new FlyWithWings());
        model.performFly();
    }
}
