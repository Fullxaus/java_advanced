package ru.mentee.power.mvc.refactoring.finaltask.model;


import ru.mentee.power.mvc.refactoring.finaltask.behavior.impl.HissBehavior;
import ru.mentee.power.mvc.refactoring.finaltask.behavior.impl.SlitherBehavior;

public class Snake extends Reptile {
    public Snake(String name, int age) {
        super(name, age);
        setMoveBehavior(new SlitherBehavior());
        setSoundBehavior(new HissBehavior());
    }

    @Override
    protected void fillExtraProperties(java.util.Map<String, String> extra) {
        // no extra for now
    }
}
