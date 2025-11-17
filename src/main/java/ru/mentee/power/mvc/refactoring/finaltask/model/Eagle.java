package ru.mentee.power.mvc.refactoring.finaltask.model;

import ru.mentee.power.mvc.refactoring.finaltask.behavior.impl.ChirpBehavior;
import ru.mentee.power.mvc.refactoring.finaltask.behavior.impl.FlyBehavior;

public class Eagle extends Bird {
    private final String wingSpan; // example extra property

    public Eagle(String name, int age) {
        super(name, age);
        this.wingSpan = "unknown";
        setMoveBehavior(new FlyBehavior());
        setSoundBehavior(new ChirpBehavior());
    }

    public String getWingSpan() { return wingSpan; }

    @Override
    protected void fillExtraProperties(java.util.Map<String, String> extra) {
        extra.put("wingSpan", wingSpan);
    }
}
