package ru.mentee.power.patterns.factory.apps;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.mentee.power.patterns.factory.documents.Document;
import static org.mockito.Mockito.*;

public class ApplicationTest {

    static class TestableApplication extends Applications {
        Document mockDocument = Mockito.mock(Document.class);
        @Override
        protected Document createDocument() {

            return mockDocument;
        }
    }

    @Test
    @DisplayName("newDocument() должен вызывать createDocument() и затем open() у созданного документа")
    void newDocumentShouldCreateAndOpenDocument() {
        TestableApplication app = new TestableApplication();
        app.newDocument();

        verify(app.mockDocument, times(1)).open();
    }
}
