package ru.mentee.power.patterns.factory.documents.impl;

import ru.mentee.power.patterns.factory.documents.Document;

public class TextDocument implements Document {
    @Override
    public void open() {
        System.out.println("Открытие текстового документа.");
    }

    @Override
    public void save() {
        System.out.println("Текстовый документ сохранён.");
    }

    @Override
    public void close() {
        System.out.println("Текстовый документ закрыт.");
    }
}

