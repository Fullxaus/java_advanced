package ru.mentee.power.oop.exceptions_design.zoo.exceptions;

public class AnimalNotFoundException extends AnimalOperationException {
    private final String animalIdentifier;

    public AnimalNotFoundException(String animalIdentifier, String message) {
        super(message);
        this.animalIdentifier = animalIdentifier;
    }

    public String getAnimalIdentifier() {
        return animalIdentifier;
    }
}
