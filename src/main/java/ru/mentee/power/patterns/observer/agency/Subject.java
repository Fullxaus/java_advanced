package ru.mentee.power.patterns.observer.agency;

import ru.mentee.power.patterns.observer.subscriber.Observer;

public interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers(String news);
}
