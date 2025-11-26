package ru.mentee.power.mvc.refactoring.finaltask.model;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тестовый подкласс Animal для проверок.
 */
class TestAnimal extends Animal {
    public TestAnimal(String name, int age) { super(name, age); }
    @Override protected void fillExtraProperties(java.util.Map<String, String> extra) { }
}

public class AnimalBehaviorTest {

    @Test
    void performMoveUsesInjectedStrategy() {
        TestAnimal a = new TestAnimal("T", 1);
        AtomicBoolean called = new AtomicBoolean(false);

        a.setMoveBehavior(() -> {
            called.set(true);
            return "moved";
        });

        String res = a.performMove();
        assertTrue(called.get(), "Должен быть вызван MoveBehavior");
        assertEquals("moved", res);
    }

    @Test
    void performSoundUsesInjectedStrategy() {
        TestAnimal a = new TestAnimal("T", 1);
        AtomicBoolean called = new AtomicBoolean(false);

        a.setSoundBehavior(() -> {
            called.set(true);
            return "sound";
        });

        String res = a.performSound();
        assertTrue(called.get(), "Должен быть вызван SoundBehavior");
        assertEquals("sound", res);
    }

    @Test
    void toDtoContainsTypeNameAndFields() {
        TestAnimal a = new TestAnimal("Bob", 7);
        AnimalDto dto = a.toDto();
        assertNotNull(dto);
        assertEquals("TestAnimal", dto.getType());
        assertEquals("Bob", dto.getName());
        assertEquals(7, dto.getAge());
        assertNotNull(dto.getExtraProperties());
    }
}
