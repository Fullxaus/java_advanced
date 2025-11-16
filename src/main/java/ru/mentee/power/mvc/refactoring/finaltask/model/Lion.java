package ru.mentee.power.mvc.refactoring.finaltask.model;


import ru.mentee.power.mvc.refactoring.finaltask.behavior.impl.RoarBehavior;
import ru.mentee.power.mvc.refactoring.finaltask.behavior.impl.WalkBehavior;

public class Lion extends Mammal {
    private final String maneColor;

    public Lion(String name, int age, String maneColor) {
        super(name, age);
        this.maneColor = maneColor;
        // default strategies
        setMoveBehavior(new WalkBehavior());
        setSoundBehavior(new RoarBehavior());
    }

    public String getManeColor() { return maneColor; }

    @Override
    protected void fillExtraProperties(java.util.Map<String, String> extra) {
        extra.put("maneColor", maneColor);
    }
}
