package ru.mentee.power.oop.finaltask.behavior;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.mentee.power.oop.finaltask.behavior.impl.ChirpBehavior;
import ru.mentee.power.oop.finaltask.behavior.impl.FlyBehavior;
import ru.mentee.power.oop.finaltask.behavior.impl.HissBehavior;
import ru.mentee.power.oop.finaltask.behavior.impl.RoarBehavior;
import ru.mentee.power.oop.finaltask.behavior.impl.SlitherBehavior;
import ru.mentee.power.oop.finaltask.behavior.impl.WalkBehavior;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тесты реализаций поведений")
public class AnimalBehaviorTest {

    @Test
    @DisplayName("WalkBehavior выводит сообщение")
    void walkBehaviorPrints() {
        WalkBehavior b = new WalkBehavior();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        b.move();
        assertThat(out.toString().trim()).isEqualTo("Walking on the ground.");
    }

    @Test
    @DisplayName("FlyBehavior выводит сообщение")
    void flyBehaviorPrints() {
        FlyBehavior b = new FlyBehavior();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        b.move();
        assertThat(out.toString().trim()).isEqualTo("Flying through the sky.");
    }

    @Test
    @DisplayName("SlitherBehavior выводит сообщение")
    void slitherBehaviorPrints() {
        SlitherBehavior b = new SlitherBehavior();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        b.move();
        assertThat(out.toString().trim()).isEqualTo("Slithering along the ground.");
    }

    @Test
    @DisplayName("RoarBehavior выводит сообщение")
    void roarBehaviorPrints() {
        RoarBehavior b = new RoarBehavior();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        b.makeSound();
        assertThat(out.toString().trim()).isEqualTo("Roar!");
    }

    @Test
    @DisplayName("ChirpBehavior выводит сообщение")
    void chirpBehaviorPrints() {
        ChirpBehavior b = new ChirpBehavior();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        b.makeSound();
        assertThat(out.toString().trim()).isEqualTo("Krrr Krrrr!");
    }

    @Test
    @DisplayName("HissBehavior выводит сообщение")
    void hissBehaviorPrints() {
        HissBehavior b = new HissBehavior();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        b.makeSound();
        assertThat(out.toString().trim()).isEqualTo("Hiss...");
    }
}

