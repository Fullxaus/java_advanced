package ru.mentee.power.oop.composition.duck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.mentee.power.oop.composition.behavior.impl.FlyNoWay;
import ru.mentee.power.oop.composition.behavior.impl.MuteQuack;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Тесты для класса DecoyDuck")

public class DecoyDuckTest {
    @Test
    @DisplayName("Конструктор должен устанавливать FlyNoWay и MuteQuack")
    void constructorShouldSetBehaviors() {
        DecoyDuck decoy = new DecoyDuck();
        assertThat(decoy.flyBehavior).isInstanceOf(FlyNoWay.class);
        assertThat(decoy.quackBehavior).isInstanceOf(MuteQuack.class);
    }

    @Test
    @DisplayName("performFly не должен летать")
    void performFlyShouldPrintNoWay() {
        DecoyDuck decoy = new DecoyDuck();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        try {
            decoy.performFly();
        } finally {
            System.setOut(originalOut);
        }
        String out = outContent.toString().trim();
        assertThat(out).isNotEmpty();
        assertThat(out).containsIgnoringCase("I can't fly").contains("I can't fly").contains("I can't fly.");
    }

    @Test
    @DisplayName("performQuack должен молчать (MuteQuack)")
    void performQuackShouldBeSilent() {
        DecoyDuck decoy = new DecoyDuck();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        try {
            decoy.performQuack();
        } finally {
            System.setOut(originalOut);
        }
        String out = outContent.toString().trim();

        assertThat(out).satisfiesAnyOf(
                s -> assertThat(s).isEmpty(),
                s -> assertThat(s).contains("Silence"),
                s -> assertThat(s).containsIgnoringCase("silence")
        );
    }

    @Test
    @DisplayName("display должен печатать информацию о DecoyDuck")
    void displayShouldPrintInfo() {
        DecoyDuck decoy = new DecoyDuck();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        try {
            decoy.display();
        } finally {
            System.setOut(originalOut);
        }
        String out = outContent.toString().trim();
        assertThat(out).containsIgnoringCase("decoy duck");
    }
}
