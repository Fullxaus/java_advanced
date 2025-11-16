package ru.mentee.power.mvc.refactoring.finaltask.behavior.impl;

import ru.mentee.power.mvc.refactoring.finaltask.behavior.SoundBehavior;

public class HissBehavior implements SoundBehavior {
    @Override
    public String makeSound() {
        return "Hiss...";
    }
}
