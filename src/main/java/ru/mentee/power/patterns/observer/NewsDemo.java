package ru.mentee.power.patterns.observer;

import ru.mentee.power.patterns.observer.agency.impl.NewsAgency;
import ru.mentee.power.patterns.observer.subscriber.impl.NewsSubscriber;

public class NewsDemo {
    public static void main(String[] args) {
        // Создаем агентство
        NewsAgency agency = new NewsAgency();

        // Создаем подписчиков
        NewsSubscriber subscriber1 = new NewsSubscriber("Иван");
        NewsSubscriber subscriber2 = new NewsSubscriber("Мария");
        NewsSubscriber subscriber3 = new NewsSubscriber("Петр");

        // Подписываем их на агентство (вариант: вручную через registerObserver или через subscribe)
        agency.registerObserver(subscriber1);
        agency.registerObserver(subscriber2);
        agency.registerObserver(subscriber3);

        // Публикуем первую новость
        System.out.println("--- Публикуем первую новость ---");
        agency.publishNews("В Java 21 вышли виртуальные потоки!");

        // Отписываем одного подписчика (Мария)
        System.out.println("\n--- Мария отписывается ---");
        agency.removeObserver(subscriber2);
        // либо subscriber2.unsubscribe();

        // Публикуем вторую новость
        System.out.println("\n--- Публикуем вторую новость ---");
        agency.publishNews("Паттерн Наблюдатель очень полезен!");
    }
}
