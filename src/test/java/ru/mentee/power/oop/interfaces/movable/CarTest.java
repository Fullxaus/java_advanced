package ru.mentee.power.oop.interfaces.movable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тесты для класса Car")
public class CarTest {

    private Car testCar;
    private final String model = "Testla";

    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUp() {
        testCar = new Car(model);
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    // Восстановим System.out после каждого теста
    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("move() должен выводить корректное сообщение")
    void moveShouldPrintCorrectMessage() {
        testCar.move();
        String output = outContent.toString().trim();
        assertEquals("Машина [" + model + "] едет по дороге", output);
    }

    @Test
    @DisplayName("stop() должен выводить сообщение по умолчанию из интерфейса")
    void stopShouldPrintDefaultMessage() {
        testCar.stop(); // использует default stop() из интерфейса
        String output = outContent.toString().trim();
        assertEquals("Остановка объекта...", output);
    }
}
