package ru.mentee.power.oop.exceptions_design.zoo.manager;

import ru.mentee.power.oop.exceptions_design.zoo.domain.Animal;
import ru.mentee.power.oop.exceptions_design.zoo.domain.Habitat;
import ru.mentee.power.oop.exceptions_design.zoo.exceptions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ZooManager {
    private final List<Animal> allAnimals = new ArrayList<>();
    private final List<Habitat> allHabitats = new ArrayList<>();

    public void addAnimal(Animal animal) {
        allAnimals.add(animal);
    }

    public void addHabitat(Habitat habitat) {
        allHabitats.add(habitat);
    }

    public Animal findAnimalByName(String name) throws AnimalNotFoundException {
        Optional<Animal> found = allAnimals.stream()
                .filter(a -> a.getName().equalsIgnoreCase(name))
                .findFirst();
        if (found.isPresent()) {
            return found.get();
        }
        throw new AnimalNotFoundException(name, "Животное с именем '" + name + "' не найдено");
    }

    public Habitat findHabitatByType(String habitatType) throws HabitatException {
        Optional<Habitat> found = allHabitats.stream()
                .filter(h -> h.getType().equalsIgnoreCase(habitatType))
                .findFirst();
        if (found.isPresent()) {
            return found.get();
        }
        throw new HabitatException("Вольер типа '" + habitatType + "' не найден");
    }

    public void placeAnimalInHabitat(String animalName, String habitatType) {
        try {
            Animal animal = findAnimalByName(animalName);
            Habitat habitat = findHabitatByType(habitatType);
            habitat.addAnimal(animal);
        } catch (AnimalNotFoundException e) {
            System.err.println("Не найдено животное: " + e.getAnimalIdentifier());
        } catch (IncompatibleHabitatException e) {
            System.err.println("Несовместимость: " + e.getMessage()
                    + " (animal=" + e.getAnimalType() + ", habitat=" + e.getHabitatType() + ")");
        } catch (HabitatException e) {
            System.err.println("Ошибка вольера: " + e.getMessage());
        }
    }

    public void feedAnimal(String animalName, String foodType) {
        try {
            Animal animal = findAnimalByName(animalName);
            animal.performEat(foodType); // UnsupportedFoodException обрабатывается внутри performEat
        } catch (AnimalNotFoundException e) {
            System.err.println("Невозможно накормить: " + e.getMessage() + " (id=" + e.getAnimalIdentifier() + ")");
        }
    }

    // Геттеры для демонстрации/тестов
    public List<Animal> getAllAnimals() {
        return allAnimals;
    }

    public List<Habitat> getAllHabitats() {
        return allHabitats;
    }
}
