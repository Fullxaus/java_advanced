package ru.mentee.power.mvc.refactoring.finaltask.behavior.impl;

import ru.mentee.power.mvc.refactoring.finaltask.behavior.SoundBehavior;

public class ChirpBehavior implements SoundBehavior {
    @Override
    public String makeSound() {
        return "Krrr Krrrr!";
    }
}
