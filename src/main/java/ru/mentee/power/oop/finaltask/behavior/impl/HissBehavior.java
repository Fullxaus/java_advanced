package ru.mentee.power.oop.finaltask.behavior.impl;

import ru.mentee.power.oop.finaltask.behavior.SoundBehavior;

public class HissBehavior implements SoundBehavior {
    @Override
    public void makeSound() {
        System.out.println("Hiss...");
    }
}