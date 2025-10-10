package ru.mentee.power.oop.exceptions_design.zoo.domain;

import ru.mentee.power.oop.exceptions_design.zoo.exceptions.IncompatibleHabitatException;

import java.util.ArrayList;
import java.util.List;

public class Habitat {
    private final String type; // "Саванна", "Джунгли", "Аквариум"
    private final List<Animal> animalsInHabitat = new ArrayList<>();

    public Habitat(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public List<Animal> getAnimalsInHabitat() {
        return animalsInHabitat;
    }

    public void addAnimal(Animal animal) throws IncompatibleHabitatException {
        // Простая совместимость по типам: пример правил
        // Lion -> Саванна
        // Monkey -> Джунгли
        // Рыбы (если бы были) -> Аквариум
        String animalType = animal.getType();
        boolean compatible = isCompatible(animalType, this.type);
        if (!compatible) {
            throw new IncompatibleHabitatException(animalType, this.type,"Животное типа '" + animalType + "' несовместимо с вольером типа '" + this.type + "'");
        }
        animalsInHabitat.add(animal);
        System.out.println(animal + " помещено в вольер '" + type + "'.");
    }

    private boolean isCompatible(String animalType, String habitatType) {
        switch (animalType) {
            case "Lion":
                return "Саванна".equalsIgnoreCase(habitatType) || "Savanna".equalsIgnoreCase(habitatType);
            case "Monkey":
                return "Джунгли".equalsIgnoreCase(habitatType) || "Jungle".equalsIgnoreCase(habitatType);
            // другие типы можно расширить
            default:
                return false;
        }
    }

    @Override
    public String toString() {
        return "Habitat('" + type + "')";
    }
}
