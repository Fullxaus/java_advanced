package ru.mentee.power.patterns.factory.documents.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.assertj.core.api.Assertions.assertThat;

public class TextDocumentTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final TextDocument textDoc = new TextDocument();

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("TextDocument open() должен выводить корректное сообщение")
    void textDocumentOpenShouldPrintCorrectMessage() {
        textDoc.open();

        assertThat(outContent.toString()).contains("Открытие текстового документа.");
    }


    @Test
    @DisplayName("TextDocument save() должен выводить корректное сообщение")
    void textDocumentSaveShouldPrintCorrectMessage() {
        textDoc.save();
        assertThat(outContent.toString()).contains("Текстовый документ сохранён.");
    }

    @Test
    @DisplayName("TextDocument close() должен выводить корректное сообщение")
    void textDocumentCloseShouldPrintCorrectMessage() {
        textDoc.close();
        assertThat(outContent.toString()).contains("Текстовый документ закрыт.");
    }
}
