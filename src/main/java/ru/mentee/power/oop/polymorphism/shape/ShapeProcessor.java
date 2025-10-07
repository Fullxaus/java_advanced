package ru.mentee.power.oop.polymorphism.shape;

import java.util.ArrayList;
import java.util.List;

public class ShapeProcessor {
    public static void main(String[] args) {
        List<Shape> shapes = new ArrayList<>();

        shapes.add(new Circle("Red", 3.0));
        shapes.add(new Rectangle("Blue", 4.0, 5.0));
        shapes.add(new Triangle("Green", 3.0, 4.0, 5.0));
        // добавьте ещё при желании
        processShapes(shapes);
    }

    public static void processShapes(List<Shape> shapes) {
        for (Shape s : shapes) {
            s.displayInfo();
            double area = s.getArea();
            double perimeter = s.getPerimeter();
            System.out.printf("Area: %.4f%n", area);
            System.out.printf("Perimeter: %.4f%n", perimeter);
            System.out.println("---------------------------");
        }
    }
}
