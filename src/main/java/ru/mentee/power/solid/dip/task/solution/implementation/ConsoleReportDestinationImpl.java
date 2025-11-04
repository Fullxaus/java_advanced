package ru.mentee.power.solid.dip.task.solution.implementation;

import ru.mentee.power.solid.dip.task.solution.abstraction.ReportDestination;

public class ConsoleReportDestinationImpl implements ReportDestination {
    @Override
    public void write(String content, String destinationHint) {
        System.out.println("DIP Solution (ConsoleDestination): Вывод отчета в консоль (hint=" + destinationHint + "):");
        System.out.println(content);
    }
}
