package ru.mentee.power.mvc.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class InMemoryStudentModelTest {

    private InMemoryStudentModel model;

    @BeforeEach
    void setUp() {
        model = new InMemoryStudentModel();
    }

    @Test
    @DisplayName("Должен добавлять студента и корректно присваивать ID")
    void shouldAddStudentAndIncrementId() {
        Student student1 = Student.builder().name("Иван").build();
        model.addStudent(student1);

        List<Student> students = model.getAllStudents();

        assertThat(students).hasSize(1);
        Student addedStudent = students.get(0);
        assertThat(addedStudent.getId()).isEqualTo(1L);
        assertThat(addedStudent.getName()).isEqualTo("Иван");

        Student student2 = Student.builder().name("Мария").build();
        model.addStudent(student2);
        students = model.getAllStudents();

        assertThat(students).hasSize(2);

        Student addedStudent2 = students.stream().filter(s -> s.getName().equals("Мария")).findFirst().orElse(null);
        assertThat(addedStudent2).isNotNull();
        assertThat(addedStudent2.getId()).isEqualTo(2L);
    }

    @Test
    @DisplayName("getAllStudents должен возвращать всех добавленных студентов в корректном порядке или без него")
    void getAllStudentsShouldReturnAllAddedStudents() {
        Student s1 = Student.builder().name("Петр").build();
        Student s2 = Student.builder().name("Анна").build();
        model.addStudent(s1);
        model.addStudent(s2);

        List<Student> students = model.getAllStudents();

        assertThat(students)
                .hasSize(2)
                .extracting(Student::getName)
                .containsExactlyInAnyOrder("Петр", "Анна");
    }

    @Test
    @DisplayName("getAllStudents должен возвращать пустой список, если студенты не добавлены")
    void getAllStudentsShouldReturnEmptyListWhenNoStudentsAdded() {
        List<Student> students = model.getAllStudents();

        assertThat(students).isEmpty();
    }

    @Test
    @DisplayName("Не должен добавлять студента, если имя null, пустое или состоит из пробелов")
    void shouldNotAddStudentWithNullOrEmptyName() {
        Student nullNameStudent = Student.builder().name(null).build();
        Student emptyNameStudent = Student.builder().name("").build();
        Student blankNameStudent = Student.builder().name("   ").build();

        model.addStudent(nullNameStudent);
        model.addStudent(emptyNameStudent);
        model.addStudent(blankNameStudent);

        assertThat(model.getAllStudents()).isEmpty();
    }
}
