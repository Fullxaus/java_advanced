package ru.mentee.power.collections.eventsourcing;

import ru.mentee.power.collections.arraylist.SimpleHashMap;
import ru.mentee.power.collections.arraylist.SimpleHashSet;
import ru.mentee.power.collections.arraylist.SimpleLinkedList;
import ru.mentee.power.collections.SimplePriorityQueue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class EventProcessor {

    private final SimplePriorityQueue<Event> eventQueue;
    private final SimpleHashSet<String> processedEventIds;
    private final SimpleHashMap<String, String> processingResults;
    private final SimpleLinkedList<Event> arrivalLog;

    // Приоритет: меньше значение = выше приоритет (значение 1 — это самый высокий приоритет)
    private static final Comparator<Event> EVENT_COMPARATOR =
            Comparator.comparingInt(Event::getPriority);

    public EventProcessor() {
        this.eventQueue = new SimplePriorityQueue<>(EVENT_COMPARATOR);
        this.processedEventIds = new SimpleHashSet<>();
        this.processingResults = new SimpleHashMap<>();
        this.arrivalLog = new SimpleLinkedList<>();
    }

    public boolean submitEvent(Event event) {
        if (event == null) return false;
        arrivalLog.add(event); // журнал хранит все события
        String id = event.getEventId();
        if (!processedEventIds.contains(id)) {
            eventQueue.offer(event);
        }
        return true;
    }

    public Optional<Event> getNextEventToProcess() {
        Event nextEvent;
        while ((nextEvent = eventQueue.poll()) != null) {
            if (!isProcessed(nextEvent.getEventId())) {
                return Optional.of(nextEvent);
            }
            // Если событие уже обработано - пропускаем
        }
        return Optional.empty();
    }

    public void markAsProcessed(Event event, String result) {
        if (event == null) return;
        if (result == null) result = "";
        String id = event.getEventId();
        processedEventIds.add(id);
        processingResults.put(id, result);
    }

    public boolean isProcessed(String eventId) {
        if (eventId == null) return false;
        return processedEventIds.contains(eventId);
    }

    public Optional<String> getResult(String eventId) {
        if (eventId == null) return Optional.empty();
        return Optional.ofNullable(processingResults.get(eventId));
    }

    public List<Event> getFullEventLog() {
        List<Event> list = new ArrayList<>();
        for (int i = 0; i < arrivalLog.size(); i++) {
            list.add((Event) arrivalLog.get(i));
        }
        return list;
    }

    public int getPendingEventCount() {
        return eventQueue.size();
    }
}
