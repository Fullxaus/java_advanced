package ru.mentee.power.mvc.refactoring.finaltask.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mentee.power.mvc.refactoring.finaltask.factory.AnimalFactory;
import ru.mentee.power.mvc.refactoring.finaltask.model.Animal;
import ru.mentee.power.mvc.refactoring.finaltask.model.Eagle;
import ru.mentee.power.mvc.refactoring.finaltask.model.Lion;
import ru.mentee.power.mvc.refactoring.finaltask.model.Snake;
import ru.mentee.power.mvc.refactoring.finaltask.view.AnimalView;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ZooControllerTest {

    private AnimalFactory factory;
    private AnimalView view;
    private ZooController controller;

    @BeforeEach
    void setUp() {
        factory = mock(AnimalFactory.class);
        view = mock(AnimalView.class);
        controller = new ZooController(view, factory);
    }

    @Test
    void initDemoCreatesAnimalsAndCallsView() {

        Animal lion = new Lion("Lion", 5, "Orange");
        Animal eagle = new Eagle("Eagle", 3);
        Animal snake = new Snake("Snake", 2);

        when(factory.create(eq("lion"), anyMap())).thenReturn(lion);
        when(factory.create(eq("eagle"), anyMap())).thenReturn(eagle);
        when(factory.create(eq("snake"), anyMap())).thenReturn(snake);

        controller.initDemo();

        verify(view, times(1)).showList(anyList());

        List<Animal> stored = controller.getAnimals();
        assertEquals(3, stored.size());

        assertTrue(stored.stream().anyMatch(a -> a.getName().equals("Lion")));
        assertTrue(stored.stream().anyMatch(a -> a.getName().equals("Eagle")));
        assertTrue(stored.stream().anyMatch(a -> a.getName().equals("Snake")));
    }

    @Test
    void changeBehaviorReturnsFalseIfAnimalNotFound() {

        boolean result = controller.changeBehavior("NoSuch", null, null);
        assertFalse(result);
    }

    @Test
    void changeBehaviorAppliesNewStrategiesWhenPresent() {

        Animal snake = new Snake("Snake", 2);
        when(factory.create(eq("lion"), anyMap())).thenReturn(new Lion("Lion",1,"x"));
        when(factory.create(eq("eagle"), anyMap())).thenReturn(new Eagle("Eagle",1));
        when(factory.create(eq("snake"), anyMap())).thenReturn(snake);

        controller.initDemo();

        boolean ok = controller.changeBehavior("Snake",
                "ru.mentee.power.mvc.refactoring.finaltask.behavior.impl.FlyBehavior",
                "ru.mentee.power.mvc.refactoring.finaltask.behavior.impl.ChirpBehavior");

        assertTrue(ok);

        String move = snake.performMove();
        String sound = snake.performSound();
        assertTrue(move.toLowerCase().contains("fly") || move.toLowerCase().contains("flying"));
        assertTrue(sound.toLowerCase().contains("krrr") || sound.toLowerCase().contains("chirp") || sound.length() > 0);

        verify(view, atLeastOnce()).showMessage(contains("Behavior changed for"));
    }
}
