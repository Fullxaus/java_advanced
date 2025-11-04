package ru.mentee.power.solid.dip.task.solution.abstraction;

public interface ReportDestination {
    /**
     * @param content         Содержимое отчета
     * @param destinationHint Подсказка назначения (например, имя файла)
     */
    void write(String content, String destinationHint);
}
