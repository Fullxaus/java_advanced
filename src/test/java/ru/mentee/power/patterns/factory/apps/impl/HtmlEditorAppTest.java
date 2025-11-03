package ru.mentee.power.patterns.factory.apps.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.mentee.power.patterns.factory.documents.Document;
import ru.mentee.power.patterns.factory.documents.impl.HtmlDocument;
import static org.assertj.core.api.Assertions.assertThat;

public class HtmlEditorAppTest {
    private final HtmlEditorApp htmlEditorApp = new HtmlEditorApp();

    @Test
    @DisplayName("HtmlEditorApp createDocument() должен возвращать HtmlDocument")
    void createDocumentShouldReturnHtmlDocument() {
        Document doc = htmlEditorApp.createDocument();

        assertThat(doc).isInstanceOf(HtmlDocument.class);
    }
}
