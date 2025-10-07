package ru.mentee.power.oop.polymorphism.shape;

public abstract class Shape {
    private String color;

    public Shape(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public abstract double getArea();

    public abstract double getPerimeter();

    public void displayInfo() {
        System.out.println("Shape color: " + color);
    }
}
