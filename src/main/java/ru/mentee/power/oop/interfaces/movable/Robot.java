package ru.mentee.power.oop.interfaces.movable;

public class Robot implements Movable {
    private final String serialNumber;

    public Robot(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Override
    public void move() {
        System.out.println("Робот [" + serialNumber + "] шагает");
    }

    @Override
    public void stop() {
        System.out.println("Робот [" + serialNumber + "] прекращает движение!");
    }
}
