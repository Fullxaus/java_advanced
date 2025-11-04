package ru.mentee.power.patterns.observer.agency.impl;

import ru.mentee.power.patterns.observer.agency.Subject;
import ru.mentee.power.patterns.observer.subscriber.Observer;

import java.util.ArrayList;
import java.util.List;

public class NewsAgency implements Subject {
    private final List<Observer> observers = new ArrayList<>();

    @Override
    public void registerObserver(Observer o) {
        if (o != null && !observers.contains(o)) {
            observers.add(o);
        }
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers(String news) {
        for (Observer o : new ArrayList<>(observers)) {
            o.update(news);
        }
    }

    public void publishNews(String news) {
        System.out.println("NewsAgency: Публикую новость: " + news);
        notifyObservers(news);
    }
}
