package ru.mentee.power.oop.polymorphism.shape;

public class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle(String color, double width, double height) {
        super(color);
        if(width<=0||height<=0){
            throw new IllegalArgumentException("width and height must be positive");
        }
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public double getPerimeter() {
        return 2 * (width + height);
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Type: Rectangle");
        System.out.println("Width: " + width + ", Height: " + height);
    }
}
