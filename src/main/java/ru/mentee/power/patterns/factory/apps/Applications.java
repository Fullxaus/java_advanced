package ru.mentee.power.patterns.factory.apps;

import ru.mentee.power.patterns.factory.documents.Document;

public abstract class Applications {
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
        System.out.println("Открытие текстового документа.");
    }
}

