package ru.mentee.power.solid.isp.task.solution.device;

import ru.mentee.power.solid.isp.task.solution.interf.Printable;
import ru.mentee.power.solid.isp.task.solution.interf.Scannable;
import ru.mentee.power.solid.isp.task.solution.interf.Faxable;
import ru.mentee.power.solid.isp.task.solution.interf.Staplable;

public class MFUWithStapler implements Printable, Scannable, Faxable, Staplable {
    @Override
    public void print(String document) {
        System.out.println("MFUWithStapler: Печать - " + document);
    }

    @Override
    public void scan(String document) {
        System.out.println("MFUWithStapler: Сканирование - " + document);
    }

    @Override
    public void fax(String document) {
        System.out.println("MFUWithStapler: Факс - " + document);
    }

    @Override
    public void staple(String document) {
        System.out.println("MFUWithStapler: Скрепление степлером - " + document);
    }
}
