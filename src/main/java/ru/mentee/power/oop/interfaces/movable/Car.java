package ru.mentee.power.oop.interfaces.movable;

public class Car implements Movable {
    private final String model;

    public Car(String model) {
        this.model = model;
    }

    @Override
    public void move() {
        System.out.println("Машина [" + model + "] едет по дороге");
    }

    // Используется default stop() из интерфейса
}
