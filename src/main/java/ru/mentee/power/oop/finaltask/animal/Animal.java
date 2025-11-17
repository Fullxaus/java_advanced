package ru.mentee.power.oop.finaltask.animal;

import ru.mentee.power.oop.finaltask.behavior.MoveBehavior;
import ru.mentee.power.oop.finaltask.behavior.SoundBehavior;

public abstract class Animal {
    private final String name;
    private final int age;

    private MoveBehavior moveBehavior;
    private SoundBehavior soundBehavior;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    // стратегии
    public void setMoveBehavior(MoveBehavior moveBehavior) {
        this.moveBehavior = moveBehavior;
    }
    public void setSoundBehavior(SoundBehavior soundBehavior) {
        this.soundBehavior = soundBehavior;
    }
    public void performMove() {
        if (moveBehavior != null) {
            moveBehavior.move();
        } else {
            System.out.println(name + " has no move behavior.");
        }
    }
    public void performSound() {
        if (soundBehavior != null) {
            soundBehavior.makeSound();
        } else {
            System.out.println(name + " has no sound behavior.");
        }
    }
    public abstract void displayInfo();
}
