package ru.mentee.power.collections.eventsourcing;

import java.util.Objects;

public class Event {
    private final String eventId;
    private final int priority;
    private final String data;

    public Event(String eventId, int priority, String data) {
        if (eventId == null || eventId.trim().isEmpty()) {
            throw new IllegalArgumentException("eventId must not be null or empty");
        }
        if (data == null) {
            throw new IllegalArgumentException("data must not be null");
        }
        this.eventId = eventId;
        this.priority = priority;
        this.data = data;
    }

    public String getEventId() { return eventId; }
    public int getPriority() { return priority; }
    public String getData() { return data; }

    @Override
    public String toString() {
        return "Event{" +
                "eventId='" + eventId + '\'' +
                ", priority=" + priority +
                ", data='" + data + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(eventId, event.eventId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId);
    }
}
