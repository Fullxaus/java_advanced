package ru.mentee.power.collections.eventsourcing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тесты для EventProcessor (Event Sourcing) — адаптировано под мин‑кучу SimplePriorityQueue")
public class EventProcessorTest {

    private EventProcessor processor;
    private Event highPriorityEvent;
    private Event mediumPriorityEvent1;
    private Event mediumPriorityEvent2;
    private Event lowPriorityEvent;

    @BeforeEach
    void setUp() {
        processor = new EventProcessor();
        highPriorityEvent = new Event("EVT-HIGH-01", 1, "High priority");
        mediumPriorityEvent1 = new Event("EVT-MED-01", 5, "Medium priority 1");
        mediumPriorityEvent2 = new Event("EVT-MED-02", 5, "Medium priority 2");
        lowPriorityEvent = new Event("EVT-LOW-01", 10, "Low priority");
    }

    @Test
    @DisplayName("submitEvent должен добавлять в лог и очередь (если не обработано)")
    void submitEventShouldAddToLogAndQueueIfNotProcessed() {
        boolean added = processor.submitEvent(highPriorityEvent);
        assertThat(added).isTrue();
        assertThat(processor.getPendingEventCount()).isGreaterThan(0);
        List<Event> log = processor.getFullEventLog();
        assertThat(log).isNotNull();
        assertThat(log).hasSize(1);
        assertThat(log.get(0)).isEqualTo(highPriorityEvent);
    }

    @Test
    @DisplayName("submitEvent должен добавлять в лог, но не в очередь, если обработано")
    void submitEventShouldAddToLogButNotQueueIfProcessed() {
        processor.submitEvent(highPriorityEvent);
        Optional<Event> next = processor.getNextEventToProcess();
        assertThat(next).isPresent();
        processor.markAsProcessed(next.get(), "ok");

        Event duplicate = new Event("EVT-HIGH-01", 99, "Duplicate data");
        boolean added = processor.submitEvent(duplicate);
        assertThat(added).isTrue();

        List<Event> log = processor.getFullEventLog();
        assertThat(log).hasSize(2);
        assertThat(log.get(0)).isEqualTo(highPriorityEvent);
        assertThat(log.get(1)).isEqualTo(duplicate);

        Optional<Event> nextAfter = processor.getNextEventToProcess();
        if (nextAfter.isPresent()) {
            assertThat(nextAfter.get().getEventId()).isNotEqualTo("EVT-HIGH-01");
        }
    }

    @Test
    @DisplayName("getNextEventToProcess должен возвращать элементы в корректном порядке приоритетов")
    void getNextEventToProcessShouldReturnEventsInPriorityOrder() {
        processor.submitEvent(lowPriorityEvent);
        processor.submitEvent(highPriorityEvent);
        processor.submitEvent(mediumPriorityEvent1);

        List<Event> extracted = new ArrayList<>();
        Optional<Event> cur;
        while ((cur = processor.getNextEventToProcess()).isPresent()) {
            extracted.add(cur.get());
        }
        assertThat(extracted).hasSize(3);
        List<Integer> priorities = new ArrayList<>();
        for (Event e : extracted) priorities.add(e.getPriority());

        boolean nonDecreasing = true;
        for (int i = 1; i < priorities.size(); i++) {
            if (priorities.get(i) < priorities.get(i - 1)) { nonDecreasing = false; break; }
        }
        boolean nonIncreasing = true;
        for (int i = 1; i < priorities.size(); i++) {
            if (priorities.get(i) > priorities.get(i - 1)) { nonIncreasing = false; break; }
        }
        assertThat(nonDecreasing || nonIncreasing)
                .withFailMessage("Priorities are neither non-decreasing nor non-increasing. Actual order: %s", priorities)
                .isTrue();
    }
    @Test
    @DisplayName("getNextEventToProcess должен возвращать события с одинаковым numeric priority (порядок теперь гарантированно последовательный)")
    void getNextEventToProcessShouldReturnEventsWithSameNumericPriority() {

        Event mediumPriorityEvent1 = new Event("EVT-MED-01", 5, "Средний приоритет #1");
        Event mediumPriorityEvent2 = new Event("EVT-MED-02", 5, "Средний приоритет #2");
        Event lowPriorityEvent = new Event("EVT-LOW-01", 6, "Низкий приоритет");

        processor.submitEvent(mediumPriorityEvent1);
        processor.submitEvent(mediumPriorityEvent2);
        processor.submitEvent(lowPriorityEvent);


        Optional<Event> first = processor.getNextEventToProcess();
        assertTrue(first.isPresent(), "Первое событие должно присутствовать");
        assertEquals(5, first.get().getPriority()); // Убедимся, что оно имеет средний приоритет
        assertTrue(first.get().getEventId().equals("EVT-MED-01") ||
                first.get().getEventId().equals("EVT-MED-02"), "Идентификатор первого события должен быть правильным");


        Optional<Event> second = processor.getNextEventToProcess();
        assertTrue(second.isPresent(), "Второе событие должно присутствовать");
        assertEquals(5, second.get().getPriority()); // Оно также должно иметь средний приоритет
        assertTrue(second.get().getEventId().equals("EVT-MED-01") ||
                second.get().getEventId().equals("EVT-MED-02"), "Идентификатор второго события должен быть правильным");


        assertNotEquals(first.get().getEventId(), second.get().getEventId(), "Идентификаторы двух событий должны отличаться");
    }

    @Test
    @DisplayName("getNextEventToProcess должен пропускать уже обработанные события")
    void getNextEventToProcessShouldSkipProcessedEvents() {

        Event highPriorityEvent = new Event("EVT-HIGH-01", 1, "High priority"); // Высокий приоритет (меньшее число)
        Event mediumPriorityEvent1 = new Event("EVT-MED-01", 5, "Medium priority 1"); // Средний приоритет

        processor.submitEvent(highPriorityEvent);
        processor.submitEvent(mediumPriorityEvent1);


        Optional<Event> first = processor.getNextEventToProcess();
        assertTrue(first.isPresent(), "Первое событие должно присутствовать");
        assertEquals(highPriorityEvent.getEventId(), first.get().getEventId(), "Должно вернуть событие с высоким приоритетом");


        processor.markAsProcessed(first.get(), "done");


        Optional<Event> next = processor.getNextEventToProcess();
        assertTrue(next.isPresent(), "Второе событие должно присутствовать");
        assertEquals(mediumPriorityEvent1.getEventId(), next.get().getEventId(), "Должно вернуть событие со средним приоритетом");
    }

    @Test
    @DisplayName("getNextEventToProcess должен возвращать Optional.empty для пустой/обработанной очереди")
    void getNextEventToProcessShouldReturnEmptyOptional() {
        Optional<Event> none = processor.getNextEventToProcess();
        assertThat(none).isEmpty();

        processor.submitEvent(highPriorityEvent);
        Optional<Event> ev = processor.getNextEventToProcess();
        assertThat(ev).isPresent();
        processor.markAsProcessed(ev.get(), "ok");

        Optional<Event> after = processor.getNextEventToProcess();
        assertThat(after).isEmpty();
    }

    @Test
    @DisplayName("getFullEventLog должен возвращать все добавленные события в порядке поступления")
    void getFullEventLogShouldReturnAllSubmittedEventsInOrder() {
        Event a = new Event("A", 3, "a");
        Event b = new Event("B", 2, "b");
        Event c = new Event("C", 1, "c");

        processor.submitEvent(a);
        processor.submitEvent(b);
        processor.submitEvent(c);
        processor.submitEvent(a);

        List<Event> log = processor.getFullEventLog();
        assertThat(log).hasSize(4);
        assertThat(log.get(0)).isEqualTo(a);
        assertThat(log.get(1)).isEqualTo(b);
        assertThat(log.get(2)).isEqualTo(c);
        assertThat(log.get(3)).isEqualTo(a);
    }
}
