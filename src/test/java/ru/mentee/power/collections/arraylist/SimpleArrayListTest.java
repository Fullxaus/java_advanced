package ru.mentee.power.collections.arraylist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

@DisplayName("Тесты для SimpleArrayList")
public class SimpleArrayListTest {

    private SimpleArrayList<String> list;

    @BeforeEach
    void setUp() {
        list = new SimpleArrayList<>();
    }

    @Test
    @DisplayName("Добавление и получение элементов")
    void shouldAddAndGetElements() {
        list.add("A");
        list.add("B");
        list.add("C");

        assertThat(list.size()).isEqualTo(3);
        assertThat(list.get(0)).isEqualTo("A");
        assertThat(list.get(1)).isEqualTo("B");
        assertThat(list.get(2)).isEqualTo("C");
    }

    @Test
    @DisplayName("Проверка роста внутреннего массива")
    void shouldGrowInternalArray() {
        SimpleArrayList<Integer> intList = new SimpleArrayList<>(2);
        intList.add(1);
        intList.add(2);
        // Добавляем больше элементов, чем начальная емкость (2)
        intList.add(3);
        intList.add(4);
        intList.add(5);

        assertThat(intList.size()).isEqualTo(5);
        assertThat(intList.get(0)).isEqualTo(1);
        assertThat(intList.get(1)).isEqualTo(2);
        assertThat(intList.get(2)).isEqualTo(3);
        assertThat(intList.get(3)).isEqualTo(4);
        assertThat(intList.get(4)).isEqualTo(5);
    }

    @Test
    @DisplayName("Вставка элемента в середину")
    void shouldAddElementInTheMiddle() {
        list.add("A");
        list.add("C");

        list.add(1, "B");

        assertThat(list.size()).isEqualTo(3);
        assertThat(list.get(0)).isEqualTo("A");
        assertThat(list.get(1)).isEqualTo("B");
        assertThat(list.get(2)).isEqualTo("C");
    }

    @Test
    @DisplayName("Удаление элемента по индексу")
    void shouldRemoveElementByIndex() {
        list.add("A");
        list.add("B");
        list.add("C");

        String removedElement = list.remove(1);

        assertThat(removedElement).isEqualTo("B");
        assertThat(list.size()).isEqualTo(2);
        assertThat(list.get(0)).isEqualTo("A");
        assertThat(list.get(1)).isEqualTo("C");
    }

    @Test
    @DisplayName("Удаление элемента по значению")
    void shouldRemoveElementByValue() {
        list.add("A");
        list.add("B");
        list.add("C");

        boolean removed = list.remove("B");

        assertThat(removed).isTrue();
        assertThat(list.size()).isEqualTo(2);
        assertThat(list.get(0)).isEqualTo("A");
        assertThat(list.get(1)).isEqualTo("C");

        // Попытка удалить несуществующее значение
        boolean removedMissing = list.remove("X");
        assertThat(removedMissing).isFalse();
    }

    @Test
    @DisplayName("Поиск индекса элемента")
    void shouldFindIndexOfElement() {
        list.add("A");
        list.add("B");
        list.add("A");
        list.add(null);

        assertThat(list.indexOf("A")).isEqualTo(0);
        assertThat(list.lastIndexOf("A")).isEqualTo(2);

        // Поиск null
        assertThat(list.indexOf(null)).isEqualTo(3);
        assertThat(list.lastIndexOf(null)).isEqualTo(3);

        // Отсутствующий элемент
        assertThat(list.indexOf("X")).isEqualTo(-1);
        assertThat(list.lastIndexOf("X")).isEqualTo(-1);
    }

    @Test
    @DisplayName("Проверка наличия элемента")
    void shouldCheckIfElementExists() {
        list.add("A");
        list.add("B");

        assertThat(list.contains("A")).isTrue();
        assertThat(list.contains("B")).isTrue();
        assertThat(list.contains("X")).isFalse();

        list.add(null);
        assertThat(list.contains(null)).isTrue();
    }

    @Test
    @DisplayName("Очистка списка")
    void shouldClearList() {
        list.add("A");
        list.add("B");
        list.add("C");

        list.clear();

        assertThat(list.size()).isEqualTo(0);
        assertThat(list.isEmpty()).isTrue();
        // Добавление после очистки должно работать
        list.add("Z");
        assertThat(list.size()).isEqualTo(1);
        assertThat(list.get(0)).isEqualTo("Z");
    }

    @Test
    @DisplayName("Замена элемента по индексу")
    void shouldSetElementAtIndex() {
        list.add("A");
        list.add("B");
        list.add("C");

        String old = list.set(1, "BB");

        assertThat(old).isEqualTo("B");
        assertThat(list.get(0)).isEqualTo("A");
        assertThat(list.get(1)).isEqualTo("BB");
        assertThat(list.get(2)).isEqualTo("C");
    }

    @Test
    @DisplayName("toString должен возвращать корректное представление")
    void toStringShouldReturnCorrectRepresentation() {
        assertThat(list.toString()).isEqualTo("[]");

        list.add("A");
        assertThat(list.toString()).isEqualTo("[A]");

        list.add("B");
        list.add("C");
        assertThat(list.toString()).isEqualTo("[A, B, C]");
    }

    @Test
    @DisplayName("toArray должен возвращать массив с элементами")
    void toArrayShouldReturnArrayWithElements() {
        list.add("A");
        list.add("B");
        list.add("C");

        Object[] arr = list.toArray();
        assertThat(arr).containsExactly("A", "B", "C");
    }

    @Test
    @DisplayName("Обработка некорректных индексов")
    void shouldThrowIndexOutOfBoundsException() {
        // get on empty
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> list.get(0));

        // remove on empty
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> list.remove(0));

        // set on empty
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> list.set(0, "X"));

        // add at invalid negative index
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> list.add(-1, "X"));

        // add at index > size
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> list.add(1, "X"));

        // valid add at size should not throw
        list.add("A");
        list.add(1, "B"); // should succeed
        assertThat(list.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Конструктор должен выбрасывать исключение при отрицательной емкости")
    void constructorShouldThrowExceptionForNegativeCapacity() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new SimpleArrayList<>(-1));
    }
}
