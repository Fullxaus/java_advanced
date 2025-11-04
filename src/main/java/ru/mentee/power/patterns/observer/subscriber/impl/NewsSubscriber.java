package ru.mentee.power.patterns.observer.subscriber.impl;

import ru.mentee.power.patterns.observer.agency.Subject;
import ru.mentee.power.patterns.observer.subscriber.Observer;

public class NewsSubscriber implements Observer {
    private final String name;
    // опционально: ссылка на Subject для удобной отписки
    private Subject subject;

    public NewsSubscriber(String name) {
        this.name = name;
    }

    // опционально: подписаться сразу при создании
    public void subscribe(Subject subject) {
        if (subject != null) {
            this.subject = subject;
            subject.registerObserver(this);
        }
    }

    public void unsubscribe() {
        if (subject != null) {
            subject.removeObserver(this);
            subject = null;
        }
    }

    @Override
    public void update(String news) {
        System.out.println(name + " получил новость: " + news);
    }

    public String getName() {
        return name;
    }
}
