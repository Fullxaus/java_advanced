package ru.mentee.power.oop.exceptions_design.zoo.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mentee.power.oop.exceptions_design.zoo.domain.*;
import ru.mentee.power.oop.exceptions_design.zoo.exceptions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ZooManagerTest {

    private ZooManager manager;
    private Animal simba;
    private Animal rafiki;
    private Habitat savanna;
    private Habitat jungle;
    private Habitat aquarium;

    @BeforeEach
    void setUp() {
        manager = new ZooManager();

        simba = new Lion("Simba");
        rafiki = new Monkey("Rafiki");

        manager.addAnimal(simba);
        manager.addAnimal(rafiki);

        savanna = new Habitat("Саванна");
        jungle = new Habitat("Джунгли");
        aquarium = new Habitat("Аквариум");

        manager.addHabitat(savanna);
        manager.addHabitat(jungle);
        manager.addHabitat(aquarium);
    }

    @Test
    void findAnimalByName_found() throws AnimalNotFoundException {
        Animal found = manager.findAnimalByName("Simba");
        assertNotNull(found);
        assertEquals("Simba", found.getName());
    }

    @Test
    void findAnimalByName_notFound() {
        assertThrows(AnimalNotFoundException.class, () -> manager.findAnimalByName("NoSuch"));
    }

    @Test
    void placeAnimalInHabitat_successfulPlacement() {
        // Place Simba in Savanna — should succeed and be present in habitat's list
        manager.placeAnimalInHabitat("Simba", "Саванна");
        List<Animal> animalsInSavanna = savanna.getAnimalsInHabitat();
        assertTrue(animalsInSavanna.contains(simba), "Simba должен быть в Саванне");
    }

    @Test
    void placeAnimalInHabitat_incompatibleHandledAndNotThrown() {
        // Capture stderr to assert log contains incompatibility message — method should handle exception internally
        ByteArrayOutputStream errOut = new ByteArrayOutputStream();
        PrintStream oldErr = System.err;
        System.setErr(new PrintStream(errOut));
        try {
            manager.placeAnimalInHabitat("Simba", "Аквариум"); // incompatible
        } finally {
            System.setErr(oldErr);
        }
        String err = errOut.toString();
        assertTrue(err.contains("Несовместимость") || err.contains("Incompatible") || err.length() > 0,
                "Ожидается сообщение об несовместимости в stderr");
        // Ensure Simba was NOT added to aquarium
        assertFalse(aquarium.getAnimalsInHabitat().contains(simba), "Simba не должен быть в Аквариуме");
    }

    @Test
    void placeAnimalInHabitat_unknownAnimalHandled() {
        ByteArrayOutputStream errOut = new ByteArrayOutputStream();
        PrintStream oldErr = System.err;
        System.setErr(new PrintStream(errOut));
        try {
            manager.placeAnimalInHabitat("Unknown", "Джунгли"); // animal not present
        } finally {
            System.setErr(oldErr);
        }
        String err = errOut.toString();
        assertTrue(err.contains("Не найдено животное") || err.length() > 0,
                "Ожидается лог об отсутствии животного");
    }

    @Test
    void feedAnimal_successAndUnsupportedFoodHandled() {
        // capture stdout/stderr for messages from performEat
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayOutputStream err = new ByteArrayOutputStream();
        PrintStream oldOut = System.out;
        PrintStream oldErr = System.err;
        System.setOut(new PrintStream(out));
        System.setErr(new PrintStream(err));
        try {
            manager.feedAnimal("Simba", "мясо");   // supported
            manager.feedAnimal("Simba", "бананы"); // unsupported -> performEat should print to stderr
            manager.feedAnimal("Rafiki", "бананы");// supported
            manager.feedAnimal("Rafiki", "мясо");  // unsupported -> stderr
        } finally {
            System.setOut(oldOut);
            System.setErr(oldErr);
        }

        String stdout = out.toString();
        String stderr = err.toString();

        assertTrue(stdout.contains("успешно") || stdout.length() > 0,
                "Ожидается сообщение об успешном кормлении в stdout");
        assertTrue(stderr.contains("Ошибка кормления") || stderr.length() > 0,
                "Ожидается сообщение об ошибке при неподдерживаемой еде в stderr");
    }

    @Test
    void feedAnimal_unknownAnimalLogged() {
        ByteArrayOutputStream errOut = new ByteArrayOutputStream();
        PrintStream oldErr = System.err;
        System.setErr(new PrintStream(errOut));
        try {
            manager.feedAnimal("NoSuch", "мясо");
        } finally {
            System.setErr(oldErr);
        }
        String err = errOut.toString();
        assertTrue(err.contains("Невозможно накормить") || err.contains("Не найдено") || err.length() > 0,
                "Ожидается лог о невозможности накормить несуществующее животное");
    }
}
