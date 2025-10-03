package ru.mentee.power.oop.interfaces.movable;

import java.util.ArrayList;
import java.util.List;

public class MovableDemo {
    public static void main(String[] args) {
        Movable.describeInterface();

        List<Movable> movers = new ArrayList<>();
        movers.add(new Car("Lada Largus"));
        movers.add(new Robot("R2D2"));
        movers.add(new Car("Toyota Camry"));
        movers.add(new Robot("Atrox"));

        for (Movable m : movers) {
            m.move();
            m.stop();
            System.out.println("---");
        }
    }
}
