package ru.mentee.power.solid.isp.task.solution.device;

import ru.mentee.power.solid.isp.task.solution.interf.Printable;

public class RefactoredSimplePrinter implements Printable {
    @Override
    public void print(String document) {
        System.out.println("RefactoredSimplePrinter: Печать документа - " + document);
    }
}
