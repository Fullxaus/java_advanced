package ru.mentee.power.patterns.factory.documents.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

public class HtmlDocumentTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final HtmlDocument htmlDoc = new HtmlDocument();

    @BeforeEach
    void setUpStreams() { System.setOut(new PrintStream(outContent)); }
    @AfterEach
    void restoreStreams() { System.setOut(originalOut); }

    @Test
    @DisplayName("HtmlDocument open() должен выводить корректное сообщение")
    void htmlDocumentOpenShouldPrintCorrectMessage() {
        htmlDoc.open();
        assertThat(outContent.toString()).contains("Открыт HTML документ.");
    }

    @Test @DisplayName("HtmlDocument save() должен выводить корректное сообщение")
    void htmlDocumentSaveShouldPrintCorrectMessage() {
        htmlDoc.save();
        assertThat(outContent.toString()).contains("HTML документ сохранён.");
    }

    @Test @DisplayName("HtmlDocument close() должен выводить корректное сообщение")
    void htmlDocumentCloseShouldPrintCorrectMessage() {
        htmlDoc.close();
        assertThat(outContent.toString()).contains("HTML документ закрыт.");
    }
}
