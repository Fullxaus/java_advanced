package ru.mentee.power.patterns.factory.apps.impl;

import ru.mentee.power.patterns.factory.apps.Applications;

import ru.mentee.power.patterns.factory.documents.Document;
import ru.mentee.power.patterns.factory.documents.impl.TextDocument;

public class TextEditorApp extends Applications {
    @Override
    protected Document createDocument() {
        return new TextDocument();
    }
}

