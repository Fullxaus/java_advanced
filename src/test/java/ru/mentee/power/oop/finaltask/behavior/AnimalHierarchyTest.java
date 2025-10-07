package ru.mentee.power.oop.finaltask.behavior;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.mentee.power.oop.finaltask.animal.Eagle;
import ru.mentee.power.oop.finaltask.animal.Lion;
import ru.mentee.power.oop.finaltask.animal.Snake;


import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тесты иерархии животных и конструкторов")
public class AnimalHierarchyTest {

    @Test
    @DisplayName("Lion конструктор и начальное поведение")
    void lionConstructorAndDefaults() {
        Lion lion = new Lion("Alex", 5, "golden");
        assertThat(lion.getName()).isEqualTo("Alex");
        assertThat(lion.getAge()).isEqualTo(5);
        assertThat(lion.getManeColor()).isEqualTo("golden");

        // Проверяем, что поведение установлено (через performMove/performSound вывод)
        // Здесь достаточно вызвать performMove/performSound и убедиться, что они не выбрасывают исключений.
        lion.performMove();   // ожидает "Walking on the ground."
        lion.performSound();  // ожидает "Roar!"
    }

    @Test
    @DisplayName("Eagle конструктор и начальное поведение")
    void eagleConstructorAndDefaults() {
        Eagle eagle = new Eagle("Aquila", 3);
        assertThat(eagle.getName()).isEqualTo("Aquila");
        assertThat(eagle.getAge()).isEqualTo(3);
        // Проверяем поведение через performMove/performSound (не падает)
        eagle.performMove();
        eagle.performSound();
    }

    @Test
    @DisplayName("Snake конструктор и начальное поведение")
    void snakeConstructorAndDefaults() {
        Snake snake = new Snake("Kaa", 2);
        assertThat(snake.getName()).isEqualTo("Kaa");
        assertThat(snake.getAge()).isEqualTo(2);
        snake.performMove();   // Slithering along the ground.
        snake.performSound();  // Hiss...
    }
}
