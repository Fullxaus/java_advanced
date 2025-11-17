package ru.mentee.power.mvc.refactoring.finaltask.model;

import ru.mentee.power.mvc.refactoring.finaltask.behavior.MoveBehavior;
import ru.mentee.power.mvc.refactoring.finaltask.behavior.SoundBehavior;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class Animal {
    private final String name;
    private final int age;

    private MoveBehavior moveBehavior;
    private SoundBehavior soundBehavior;

    protected Animal(String name, int age) {
        this.name = Objects.requireNonNull(name);
        this.age = age;
    }

    public String getName() { return name; }
    public int getAge() { return age; }

    public MoveBehavior getMoveBehavior() { return moveBehavior; }
    public SoundBehavior getSoundBehavior() { return soundBehavior; }

    // DI setters for strategies
    public void setMoveBehavior(MoveBehavior moveBehavior) {
        this.moveBehavior = moveBehavior;
    }
    public void setSoundBehavior(SoundBehavior soundBehavior) {
        this.soundBehavior = soundBehavior;
    }

    // perform methods delegate to strategies and return strings (Model doesn't print)
    public String performMove() {
        if (moveBehavior == null) return name + " has no move behavior.";
        return moveBehavior.move();
    }
    public String performSound() {
        if (soundBehavior == null) return name + " has no sound behavior.";
        return soundBehavior.makeSound();
    }

    // Provide DTO for views (Model->View boundary)
    public AnimalDto toDto() {
        Map<String, String> extra = new HashMap<>();
        fillExtraProperties(extra);
        return new AnimalDto(getClass().getSimpleName(), name, age, extra);
    }

    // subclasses override to add their properties
    protected void fillExtraProperties(Map<String, String> extra) { }

}
