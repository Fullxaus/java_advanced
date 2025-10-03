package ru.mentee.power.oop.interfaces.movable;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тесты для класса Robot")
public class RobotTest {

    private Robot testRobot;
    private final String serial = "SN-007";

    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUp() {
        testRobot = new Robot(serial);
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("move() должен выводить корректное сообщение")
    void moveShouldPrintCorrectMessage() {
        testRobot.move();
        String output = outContent.toString().trim();
        assertEquals("Робот [" + serial + "] шагает", output);
    }

    @Test
    @DisplayName("stop() должен выводить переопределенное сообщение")
    void stopShouldPrintOverriddenMessage() {
        testRobot.stop();
        String output = outContent.toString().trim();
        assertEquals("Робот [" + serial + "] прекращает движение!", output);
    }
}
