package ru.mentee.power.patterns.factory.documents.impl;

import ru.mentee.power.patterns.factory.documents.Document;

public abstract class Application {
    /**
     * Фабричный метод — должен быть реализован в подклассах.
     */
    protected abstract Document createDocument();

    /**
     * Метод использует фабричный метод для создания документа и открывает его.
     */
    public void newDocument() {
        Document doc = createDocument();
        doc.open();
        System.out.println("Открыт новый документ в приложении.");
    }
}

