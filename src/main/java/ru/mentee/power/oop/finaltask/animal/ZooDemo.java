package ru.mentee.power.oop.finaltask.animal;

import ru.mentee.power.oop.finaltask.behavior.impl.ChirpBehavior;
import ru.mentee.power.oop.finaltask.behavior.impl.FlyBehavior;

import java.util.ArrayList;
import java.util.List;

public class ZooDemo {
    public static void main(String[] args) {
        List<Animal> animals = new ArrayList<>();

        animals.add(new Lion("Lion", 5,"Orange"));
        animals.add(new Eagle("Eagle", 3));
        animals.add(new Snake("Snake", 2));

        for (Animal a : animals) {
            a.displayInfo();
            a.performMove();
            a.performSound();
            System.out.println();
        }

        System.out.println("=== Динамическая смена поведения ===");
        Animal snake = animals.stream()
                .filter(a -> a instanceof Animal)
                .findFirst()
                .orElse(null);

        if (snake != null) {
            System.out.println("Before change:");
            snake.displayInfo();
            snake.performMove();
            snake.performSound();

            snake.setMoveBehavior(new FlyBehavior());
            snake.setSoundBehavior(new ChirpBehavior());

            System.out.println("After change:");
            snake.performMove();
            snake.performSound();
        }

    }
}
