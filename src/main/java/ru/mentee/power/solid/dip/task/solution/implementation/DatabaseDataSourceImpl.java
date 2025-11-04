package ru.mentee.power.solid.dip.task.solution.implementation;

import ru.mentee.power.solid.dip.task.solution.abstraction.DataSource;

import java.util.List;

public class DatabaseDataSourceImpl implements DataSource {
    @Override
    public List<String> getData() {
        System.out.println("DIP Solution: Получение данных из реальной БД...");
        // Имитация получения данных
        return List.of("DB Data 1 (DIP)", "DB Data 2 (DIP)", "DB Data 3 (DIP)");
    }
}
