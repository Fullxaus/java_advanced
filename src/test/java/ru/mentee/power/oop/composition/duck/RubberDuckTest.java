package ru.mentee.power.oop.composition.duck;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.mentee.power.oop.composition.behavior.impl.FlyNoWay;
import ru.mentee.power.oop.composition.behavior.impl.Squeak;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Тесты для класса RubberDuck")
public class RubberDuckTest {
    @Test
    @DisplayName("Конструктор должен устанавливать FlyNoWay и Squeak")
    void constructorShouldSetBehaviors() {
        RubberDuck rubber = new RubberDuck();
        assertThat(rubber.flyBehavior).isInstanceOf(FlyNoWay.class);
        assertThat(rubber.quackBehavior).isInstanceOf(Squeak.class);
    }

    @Test
    @DisplayName("performFly не должен летать (вывод соответствует FlyNoWay)")
    void performFlyShouldPrintNoWay() {
        RubberDuck rubber = new RubberDuck();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        try {
            rubber.performFly();
        } finally {
            System.setOut(originalOut);
        }
        String out = outContent.toString().trim();
        assertThat(out).isNotEmpty();
        assertThat(out).containsIgnoringCase("can't fly").contains("I can't fly").contains("I can't fly.");
    }

    @Test
    @DisplayName("performQuack должен вызывать Squeak")
    void performQuackShouldSqueak() {
        RubberDuck rubber = new RubberDuck();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        try {
            rubber.performQuack();
        } finally {
            System.setOut(originalOut);
        }
        String out = outContent.toString().trim();
        assertThat(out).isNotEmpty();
        assertThat(out).containsIgnoringCase("squeak");
    }

    @Test
    @DisplayName("display должен печатать информацию о резиновой утке")
    void displayShouldPrintInfo() {
        RubberDuck rubber = new RubberDuck();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        try {
            rubber.display();
        } finally {
            System.setOut(originalOut);
        }
        String out = outContent.toString().trim();
        assertThat(out).containsIgnoringCase("rubber duck");
    }
}
