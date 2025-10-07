package ru.mentee.power.oop.polymorphism.shape;

public class Triangle extends Shape {
    private double sideA;
    private double sideB;
    private double sideC;

    public Triangle(String color, double sideA, double sideB, double sideC) {
        super(color);
        if(sideA<=0||sideB<=0||sideC<=0){
            throw new IllegalArgumentException("sideA,sideB,sideC must be positive");
        }
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
    }

    public double getSideA() {
        return sideA;
    }

    public double getSideB() {
        return sideB;
    }

    public double getSideC() {
        return sideC;
    }

    @Override
    public double getArea() {
        double p = (sideA + sideB + sideC) / 2.0;
        return Math.sqrt(p * (p - sideA) * (p - sideB) * (p - sideC));
    }

    @Override
    public double getPerimeter() {
        return sideA + sideB + sideC;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Type: Triangle");
        System.out.println("Sides: a=" + sideA + ", b=" + sideB + ", c=" + sideC);
    }
}
