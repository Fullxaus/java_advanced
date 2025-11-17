package ru.mentee.power.mvc.model;

import java.util.List;

public interface StudentModel {
    void addStudent(Student student);
    List<Student> getAllStudents();
}
