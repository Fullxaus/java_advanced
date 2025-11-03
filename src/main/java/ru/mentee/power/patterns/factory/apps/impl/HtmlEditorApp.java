package ru.mentee.power.patterns.factory.apps.impl;


import ru.mentee.power.patterns.factory.documents.Document;
import ru.mentee.power.patterns.factory.documents.impl.Application;
import ru.mentee.power.patterns.factory.documents.impl.HtmlDocument;

public class HtmlEditorApp extends Application {
    @Override
    protected Document createDocument() {
        return new HtmlDocument();
    }
}

