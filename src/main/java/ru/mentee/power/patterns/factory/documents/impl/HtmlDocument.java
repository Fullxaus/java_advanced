package ru.mentee.power.patterns.factory.documents.impl;

import ru.mentee.power.patterns.factory.documents.Document;

public class HtmlDocument implements Document {
    @Override
    public void open() {
        System.out.println("Открыт HTML документ.");
    }

    @Override
    public void save() {
        System.out.println("HTML документ сохранён.");
    }

    @Override
    public void close() {
        System.out.println("HTML документ закрыт.");
    }
}

