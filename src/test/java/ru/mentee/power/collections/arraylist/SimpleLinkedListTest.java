package ru.mentee.power.collections.arraylist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import java.util.NoSuchElementException;

@DisplayName("Тесты для SimpleLinkedList")
public class SimpleLinkedListTest {
    private SimpleLinkedList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new SimpleLinkedList<>();
        // Для большинства тестов начальное состояние — пустой список.
    }

    @Test
    @DisplayName("add() и size() должны работать корректно")
    void shouldAddElementsAndGetCorrectSize() {
        assertThat(list.isEmpty()).isTrue();
        list.add(1);
        list.add(2);
        list.add(3);
        assertThat(list.size()).isEqualTo(3);
        assertThat(list.isEmpty()).isFalse();
    }

    @Test
    @DisplayName("addFirst() и getFirst() должны работать корректно")
    void shouldAddAndGetFirst() {
        list.add(10);
        list.addFirst(5);
        list.addFirst(3);
        assertThat(list.getFirst()).isEqualTo(3);
        // Проверим порядок через get()
        assertThat(list.get(0)).isEqualTo(3);
        assertThat(list.get(1)).isEqualTo(5);
        assertThat(list.get(2)).isEqualTo(10);
        assertThat(list.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("addLast() и getLast() должны работать корректно")
    void shouldAddAndGetLast() {
        list.addLast(7);
        list.addLast(8);
        list.addLast(9);
        assertThat(list.getLast()).isEqualTo(9);
        assertThat(list.get(0)).isEqualTo(7);
        assertThat(list.get(1)).isEqualTo(8);
        assertThat(list.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("get() должен возвращать правильный элемент по индексу")
    void shouldGetElementByIndex() {
        list.add(1);
        list.add(2);
        list.add(3);
        assertThat(list.get(0)).isEqualTo(1);
        assertThat(list.get(1)).isEqualTo(2);
        assertThat(list.get(2)).isEqualTo(3);
    }

    @Test
    @DisplayName("get() должен выбрасывать исключение для некорректного индекса")
    void getShouldThrowExceptionForInvalidIndex() {
        list.add(1);
        list.add(2);
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> list.get(-1));
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> list.get(2)); // valid indices: 0,1
    }

    @Test
    @DisplayName("getFirst()/getLast() должны выбрасывать исключение для пустого списка")
    void getFirstLastShouldThrowExceptionOnEmptyList() {
        assertThatExceptionOfType(NoSuchElementException.class)
                .isThrownBy(() -> list.getFirst());
        assertThatExceptionOfType(NoSuchElementException.class)
                .isThrownBy(() -> list.getLast());
    }

    @Test
    @DisplayName("remove(index) должен удалять элемент и возвращать его")
    void removeByIndexShouldRemoveAndReturnElement() {
        list.add(100);
        list.add(200);
        list.add(300);
        assertThat(list.size()).isEqualTo(3);
        Integer removed = list.remove(1);
        assertThat(removed).isEqualTo(200);
        assertThat(list.size()).isEqualTo(2);
        assertThat(list.get(0)).isEqualTo(100);
        assertThat(list.get(1)).isEqualTo(300);
    }

    @Test
    @DisplayName("remove(Object) должен удалять элемент по значению")
    void removeByObjectShouldRemoveElement() {
        list.add(5);
        list.add(6);
        list.add(7);
        boolean removed = list.remove((Integer)6);
        assertThat(removed).isTrue();
        assertThat(list.size()).isEqualTo(2);
        assertThat(list.contains(6)).isFalse();
        // удаление отсутствующего элемента
        assertThat(list.remove((Integer)42)).isFalse();
    }

    @Test
    @DisplayName("removeFirst()/removeLast() должны удалять элементы с концов")
    void removeFirstLastShouldWork() {
        list.add(1);
        list.add(2);
        list.add(3);
        Integer first = list.removeFirst();
        Integer last = list.removeLast();
        assertThat(first).isEqualTo(1);
        assertThat(last).isEqualTo(3);
        assertThat(list.size()).isEqualTo(1);
        assertThat(list.getFirst()).isEqualTo(2);
        assertThat(list.getLast()).isEqualTo(2);
    }

    @Test
    @DisplayName("removeFirst()/removeLast() должны выбрасывать исключение для пустого списка")
    void removeFirstLastShouldThrowExceptionOnEmptyList() {
        assertThatExceptionOfType(NoSuchElementException.class)
                .isThrownBy(() -> list.removeFirst());
        assertThatExceptionOfType(NoSuchElementException.class)
                .isThrownBy(() -> list.removeLast());
    }

    @Test
    @DisplayName("clear() должен очищать список")
    void clearShouldEmptyTheList() {
        list.add(11);
        list.add(12);
        list.clear();
        assertThat(list.isEmpty()).isTrue();
        assertThat(list.size()).isEqualTo(0);
        assertThatExceptionOfType(NoSuchElementException.class)
                .isThrownBy(() -> list.getFirst());
    }

    @Test
    @DisplayName("contains() должен проверять наличие элемента")
    void containsShouldCheckExistence() {
        list.add(9);
        list.add(10);
        assertThat(list.contains(9)).isTrue();
        assertThat(list.contains(10)).isTrue();
        assertThat(list.contains(999)).isFalse();
    }

    @Test
    @DisplayName("toString() должен возвращать корректное представление")
    void toStringShouldReturnCorrectString() {
        // пустой список
        assertThat(list.toString()).isEqualTo("[]");
        list.add(1);
        list.add(2);
        list.add(3);
        assertThat(list.toString()).isEqualTo("[1, 2, 3]");
    }

}
