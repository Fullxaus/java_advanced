package ru.mentee.power.patterns.observer.subscriber.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.assertj.core.api.Assertions.assertThat;

public class NewsSubscriberTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("NewsSubscriber должен корректно выводить полученную новость")
    void updateShouldPrintNews() {
        String subscriberName = "Тестовый Подписчик";
        NewsSubscriber subscriber = new NewsSubscriber(subscriberName);
        String news = "Срочная новость!";

        subscriber.update(news);

        String expectedOutput = subscriberName + " получил новость: " + news;
        assertThat(outContent.toString()).contains(expectedOutput);
    }
}
