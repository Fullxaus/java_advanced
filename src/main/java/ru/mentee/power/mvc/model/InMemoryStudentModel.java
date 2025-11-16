package ru.mentee.power.mvc.model;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Простая in-memory реализация StudentModel.
 */
public class InMemoryStudentModel implements StudentModel {
    private final Map<Long, Student> students = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong(0);

    @Override
    public void addStudent(Student student) {
        if (student == null || student.getName() == null || student.getName().isBlank()) {
            System.err.println("Модель: Нельзя добавить null или студента без имени.");
            return;
        }
        long newId = idCounter.incrementAndGet();
        Student studentToAdd = Student.builder()
                .id(newId)
                .name(student.getName())
                .build();
        students.put(newId, studentToAdd);
    }

    @Override
    public List<Student> getAllStudents() {
        return List.copyOf(students.values());
    }
}
