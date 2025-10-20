package ru.mentee.power.collections.arraylist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Тесты для SimpleHashSet")
public class SimpleHashSetTest {

    private SimpleHashSet<String> set;

    @BeforeEach
    void setUp() {
        set = new SimpleHashSet<>();
    }

    @Test
    @DisplayName("add() должен добавлять уникальные элементы и возвращать true")
    void addShouldAddUniqueElementsAndReturnTrue() {
        boolean added = set.add("one");
        assertThat(added).isTrue();
        assertThat(set.size()).isEqualTo(1);
        assertThat(set.contains("one")).isTrue();
    }

    @Test
    @DisplayName("add() не должен добавлять дубликаты и возвращать false")
    void addShouldNotAddDuplicatesAndReturnFalse() {
        assertThat(set.add("dup")).isTrue();
        boolean addedAgain = set.add("dup");
        assertThat(addedAgain).isFalse();
        assertThat(set.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("remove() должен удалять существующий элемент и возвращать true")
    void removeShouldDeleteExistingElementAndReturnTrue() {
        set.add("toRemove");
        assertThat(set.contains("toRemove")).isTrue();
        boolean removed = set.remove("toRemove");
        assertThat(removed).isTrue();
        assertThat(set.contains("toRemove")).isFalse();
        assertThat(set.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("remove() должен возвращать false для несуществующего элемента")
    void removeShouldReturnFalseForNonExistingElement() {
        assertThat(set.size()).isEqualTo(0);
        boolean removed = set.remove("missing");
        assertThat(removed).isFalse();
        assertThat(set.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("contains() должен проверять наличие элемента")
    void containsShouldCheckExistence() {
        set.add("exists");
        assertThat(set.contains("exists")).isTrue();
        assertThat(set.contains("notExists")).isFalse();
    }
    @Test
    @DisplayName("clear() должен удалять все элементы")
    void clearShouldRemoveAllElements() {
        set.add("a");
        set.add("b");
        set.add("c");
        assertThat(set.isEmpty()).isFalse();
        assertThat(set.size()).isEqualTo(3);
        set.clear();
        assertThat(set.isEmpty()).isTrue();
        assertThat(set.size()).isEqualTo(0);
    }
}
