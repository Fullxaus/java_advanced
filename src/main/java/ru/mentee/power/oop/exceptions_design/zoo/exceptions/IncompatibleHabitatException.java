package ru.mentee.power.oop.exceptions_design.zoo.exceptions;

public class IncompatibleHabitatException extends HabitatException {
    private final String animalType;
    private final String habitatType;

    public IncompatibleHabitatException(String animalType, String habitatType, String message) {
        super(message);
        this.animalType = animalType;
        this.habitatType = habitatType;
    }

    public String getAnimalType() { return animalType; }
    public String getHabitatType() { return habitatType; }
}
