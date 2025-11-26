package ru.mentee.power.mvc.refactoring.finaltask.model;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

public class AnimalDto {
    private final String type;
    private final String name;
    private final int age;
    private final Map<String, String> extraProperties;

    public AnimalDto(String type, String name, int age, Map<String, String> extraProperties) {
        this.type = Objects.requireNonNull(type);
        this.name = Objects.requireNonNull(name);
        this.age = age;
        this.extraProperties = extraProperties == null ? Collections.emptyMap() : Collections.unmodifiableMap(extraProperties);
    }

    public String getType() { return type; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public Map<String, String> getExtraProperties() { return extraProperties; }
}
