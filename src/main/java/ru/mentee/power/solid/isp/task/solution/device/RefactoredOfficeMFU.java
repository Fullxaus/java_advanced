package ru.mentee.power.solid.isp.task.solution.device;

import ru.mentee.power.solid.isp.task.solution.interf.Printable;
import ru.mentee.power.solid.isp.task.solution.interf.Scannable;
import ru.mentee.power.solid.isp.task.solution.interf.Faxable;

public class RefactoredOfficeMFU implements Printable, Scannable, Faxable {
    @Override
    public void print(String document) {
        System.out.println("RefactoredOfficeMFU: Печать - " + document);
    }

    @Override
    public void scan(String document) {
        System.out.println("RefactoredOfficeMFU: Сканирование - " + document);
    }

    @Override
    public void fax(String document) {
        System.out.println("RefactoredOfficeMFU: Отправка факса - " + document);
    }
}

