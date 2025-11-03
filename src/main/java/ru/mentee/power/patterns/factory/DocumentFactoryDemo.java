package ru.mentee.power.patterns.factory;


import ru.mentee.power.patterns.factory.apps.impl.HtmlEditorApp;

import ru.mentee.power.patterns.factory.documents.Document;
import ru.mentee.power.patterns.factory.documents.impl.Application;

public class DocumentFactoryDemo {

    public static void main(String[] args) {
        Application textApp = new Application() {
            @Override
            protected Document createDocument() {
                return null;
            }
        };
        System.out.println("--- Работаем с текстовым редактором ---");
        textApp.newDocument();

        System.out.println("\n----------------------------------\n");

        Application htmlApp = new HtmlEditorApp();
        System.out.println("--- Работаем с HTML редактором ---");
        htmlApp.newDocument();
    }
}

