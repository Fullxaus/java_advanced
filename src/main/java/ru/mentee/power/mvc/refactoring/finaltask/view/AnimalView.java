package ru.mentee.power.mvc.refactoring.finaltask.view;

import ru.mentee.power.mvc.refactoring.finaltask.model.AnimalDto;

import java.util.List;

public interface AnimalView {
    void showList(List<AnimalDto> animals);
    void show(AnimalDto animal);
    void showMessage(String message);
}
