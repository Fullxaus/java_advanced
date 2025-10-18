package ru.mentee.power.collections.eventsourcing;

import java.util.List;
import java.util.Optional;

public class EventSourcingDemo {
    public static void main(String[] args) {
        System.out.println("--- Запуск системы Event Sourcing (имитация) ---");

        EventProcessor processor = new EventProcessor();

        Event event1 = new Event("EVT001", 5, "Пользователь зарегистрировался");
        Event event2 = new Event("EVT002", 1, "Платеж получен");
        Event event3 = new Event("EVT003", 10, "Запрос на смену пароля");
        Event event4 = new Event("EVT004", 5, "Профиль обновлен");
        Event event5 = new Event("EVT001", 99, "Повторная регистрация?"); // повт. ID

        System.out.println("Добавление событий:");
        processor.submitEvent(event1);
        processor.submitEvent(event2);
        processor.submitEvent(event3);
        processor.submitEvent(event4);
        processor.submitEvent(event5); // добавится в лог, но не будет обработан повторно

        System.out.println("\nОжидает обработки: " + processor.getPendingEventCount());

        System.out.println("\n--- Обработка событий по приоритету ---");
        Optional<Event> nextEventOpt;
        while ((nextEventOpt = processor.getNextEventToProcess()).isPresent()) {
            Event currentEvent = nextEventOpt.get();
            System.out.println("Обработка: " + currentEvent);
            String result = "Обработано успешно: " + currentEvent.getEventId();
            processor.markAsProcessed(currentEvent, result);
            System.out.println("  -> Завершено.");
        }

        System.out.println("\nОжидает обработки после цикла: " + processor.getPendingEventCount());

        System.out.println("\n--- Проверка результатов ---");
        String[] idsToCheck = {"EVT002", "EVT001", "EVT005"};
        for (String id : idsToCheck) {
            System.out.println("Событие " + id + " обработано? " + processor.isProcessed(id));
            processor.getResult(id).ifPresentOrElse(
                    res -> System.out.println("  Результат: " + res),
                    () -> System.out.println("  Результат не найден.")
            );
        }

        System.out.println("\n--- Полный лог событий (порядок поступления) ---");
        List<Event> log = processor.getFullEventLog();
        if (log != null) {
            log.forEach(System.out::println);
        } else {
            System.out.println("Лог пуст или не реализован.");
        }

        System.out.println("\n--- Система завершила работу ---");
    }
}
