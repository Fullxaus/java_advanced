package ru.mentee.power.collections.stacksqueues;

import java.util.Queue;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

public class TaskScheduler {

    private static final Random random = new Random();

    /**
     * Симулирует обработку очереди задач с ограниченным числом параллельных обработчиков.
     *
     * @param tasks Список задач для обработки.
     * @param maxConcurrent Максимальное количество одновременно обрабатываемых задач.
     */
    public static void simulateTaskProcessing(List<String> tasks, int maxConcurrent) {
        if (tasks == null || maxConcurrent <= 0) {
            System.out.println("Некорректные входные данные для симуляции.");
            return;
        }

        Queue<String> waitingQueue = new LinkedList<>(tasks);
        Set<String> activeTasks = new HashSet<>();

        System.out.println("--- Начало симуляции обработки задач (Макс. параллельно: " + maxConcurrent + ") ---");

        while (!waitingQueue.isEmpty() || !activeTasks.isEmpty()) {

            // Запускаем новые задачи
            while (activeTasks.size() < maxConcurrent && !waitingQueue.isEmpty()) {
                String task = waitingQueue.poll();
                if (task != null) {
                    activeTasks.add(task);
                    System.out.println("Начата обработка: " + task);
                }
            }

            // Завершаем одну из активных задач (если есть)
            if (!activeTasks.isEmpty()) {
                // Выбираем случайную задачу для завершения
                int idx = random.nextInt(activeTasks.size());
                Iterator<String> it = activeTasks.iterator();
                String toFinish = null;
                for (int i = 0; it.hasNext(); i++) {
                    String t = it.next();
                    if (i == idx) {
                        toFinish = t;
                        break;
                    }
                }
                if (toFinish != null) {
                    activeTasks.remove(toFinish);
                    System.out.println("Завершена обработка: " + toFinish);
                }

                // Небольшая пауза для наглядности
                try {
                    Thread.sleep(100 + random.nextInt(200));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        System.out.println("--- Симуляция обработки задач завершена ---");
    }
}
