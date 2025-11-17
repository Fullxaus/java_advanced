package ru.mentee.power.mvc.refactoring.finaltask.view;

import ru.mentee.power.mvc.refactoring.finaltask.model.AnimalDto;

import java.util.List;

public class ConsoleAnimalView implements AnimalView {

    @Override
    public void showList(List<AnimalDto> animals) {
        System.out.println("Animals list:");
        for (AnimalDto dto : animals) show(dto);
    }

    @Override
    public void show(AnimalDto animal) {
        System.out.printf("%s: name=%s, age=%d", animal.getType(), animal.getName(), animal.getAge());
        if (!animal.getExtraProperties().isEmpty()) {
            System.out.print(", props=" + animal.getExtraProperties());
        }
        System.out.println();
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }
}
