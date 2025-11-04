package ru.mentee.power.patterns.observer.agency.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.mentee.power.patterns.observer.subscriber.Observer;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

public class NewsAgencyTest {

    @Test
    @DisplayName("Должен уведомлять всех зарегистрированных наблюдателей при публикации новости")
    void shouldNotifyAllRegisteredObserversWhenNewsPublished() {
        NewsAgency agency = new NewsAgency();
        Observer mockObserver1 = mock(Observer.class);
        Observer mockObserver2 = mock(Observer.class);
        String news = "Большая новость!";

        agency.registerObserver( mockObserver1);
        agency.registerObserver( mockObserver2);
        agency.publishNews(news);

        verify(mockObserver1, times(1)).update(news);
        verify(mockObserver2, times(1)).update(news);
    }

    @Test
    @DisplayName("Не должен уведомлять удаленного наблюдателя")
    void shouldNotNotifyRemovedObserver() {
        NewsAgency agency = new NewsAgency();
        Observer mockObserver1 = mock(Observer.class);
        Observer mockObserver2 = mock(Observer.class);
        String news1 = "Первая новость";
        String news2 = "Вторая новость";

        agency.registerObserver(mockObserver1);
        agency.registerObserver(mockObserver2);
        agency.publishNews(news1);

        agency.removeObserver((Observer) mockObserver1);
        agency.publishNews(news2);

        verify(mockObserver1, times(1)).update(news1);
        verify(mockObserver1, never()).update(news2);

        verify(mockObserver2, times(1)).update(news1);
        verify(mockObserver2, times(1)).update(news2);
    }

    @Test
    @DisplayName("Не должен падать при уведомлении, если нет наблюдателей")
    void shouldNotFailOnNotifyWithNoObservers() {
        NewsAgency agency = new NewsAgency();
        assertDoesNotThrow(() -> agency.publishNews("Никто не услышит"));
    }

    @Test
    @DisplayName("Не должен регистрировать одного и того же наблюдателя дважды")
    void shouldNotRegisterSameObserverTwice() {
        NewsAgency agency = new NewsAgency();
        Observer mockObserver = mock(Observer.class);
        String news = "Тестовая новость";

        agency.registerObserver(mockObserver);
        agency.registerObserver( mockObserver);

        agency.publishNews(news);

        verify(mockObserver, times(1)).update(news);
    }
}
