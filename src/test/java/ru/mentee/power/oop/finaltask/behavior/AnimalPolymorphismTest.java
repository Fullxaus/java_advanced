package ru.mentee.power.oop.finaltask.behavior;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.mentee.power.oop.finaltask.animal.Lion;
import ru.mentee.power.oop.finaltask.animal.Snake;
import ru.mentee.power.oop.finaltask.behavior.impl.ChirpBehavior;
import ru.mentee.power.oop.finaltask.behavior.impl.FlyBehavior;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тесты полиморфизма и смены поведения животных")
public class AnimalPolymorphismTest {

    private Lion testLion;
    private Snake testSnake;

    @BeforeEach
    void setUp() {
        testLion = new Lion("TestLion", 4, "dark");
        testSnake = new Snake("TestSnake", 1);
    }

    @Test
    @DisplayName("performMove должен вызывать правильную стратегию движения")
    void performMoveShouldDelegateToCorrectStrategy() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        testLion.performMove();
        assertThat(out.toString().trim()).isEqualTo("Walking on the ground.");

        out.reset();
        testSnake.performMove();
        assertThat(out.toString().trim()).isEqualTo("Slithering along the ground.");
    }

    @Test
    @DisplayName("performSound должен вызывать правильную стратегию звука")
    void performSoundShouldDelegateToCorrectStrategy() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        testLion.performSound();
        assertThat(out.toString().trim()).isEqualTo("Roar!");

        out.reset();
        testSnake.performSound();
        assertThat(out.toString().trim()).isEqualTo("Hiss...");
    }

    @Test
    @DisplayName("setMoveBehavior должен изменять стратегию движения")
    void setMoveBehaviorShouldChangeStrategy() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        // Сначала лев ходит
        testLion.performMove();
        assertThat(out.toString().trim()).isEqualTo("Walking on the ground.");

        // Меняем стратегию на FlyBehavior
        testLion.setMoveBehavior(new FlyBehavior());
        out.reset();
        testLion.performMove();
        assertThat(out.toString().trim()).isEqualTo("Flying through the sky.");
    }

    @Test
    @DisplayName("setSoundBehavior должен изменять стратегию звука")
    void setSoundBehaviorShouldChangeStrategy() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        // Сначала лев рычит
        testLion.performSound();
        assertThat(out.toString().trim()).isEqualTo("Roar!");

        // Меняем стратегию на ChirpBehavior
        testLion.setSoundBehavior(new ChirpBehavior());
        out.reset();
        testLion.performSound();
        assertThat(out.toString().trim()).isEqualTo("Krrr Krrrr!");
    }
}
