package ru.mentee.power.patterns.factory.apps.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.mentee.power.patterns.factory.documents.Document;
import ru.mentee.power.patterns.factory.documents.impl.TextDocument;
import static org.assertj.core.api.Assertions.assertThat;

public class TextEditorAppTest {
    private final TextEditorApp textEditorApp = new TextEditorApp();

    @Test
    @DisplayName("TextEditorApp createDocument() должен возвращать TextDocument")
    void createDocumentShouldReturnTextDocument() {
        Document doc = textEditorApp.createDocument();
        assertThat(doc).isInstanceOf(TextDocument.class);
    }
}
