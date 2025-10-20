package ru.mentee.power.collections.arraylist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.*;

@DisplayName("Тесты для SimpleHashMap")
public class SimpleHashMapTest {

    private SimpleHashMap<String, Integer> map;

    @BeforeEach
    void setUp() {
        map = new SimpleHashMap<>();
    }

    @Test
    @DisplayName("put() и get() должны корректно добавлять и получать значения")
    void shouldPutAndGetValues() {
        assertThat(map.isEmpty()).isTrue();
        assertThat(map.size()).isEqualTo(0);

        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        assertThat(map.size()).isEqualTo(3);
        assertThat(map.get("one")).isEqualTo(1);
        assertThat(map.get("two")).isEqualTo(2);
        assertThat(map.get("three")).isEqualTo(3);
    }

    @Test
    @DisplayName("put() должен заменять значение для существующего ключа и возвращать старое")
    void putShouldReplaceValueForExistingKey() {
        map.put("key", 10);
        Integer old = map.put("key", 20);

        assertThat(old).isEqualTo(10);
        assertThat(map.size()).isEqualTo(1);
        assertThat(map.get("key")).isEqualTo(20);
    }

    @Test
    @DisplayName("get() должен возвращать null для несуществующего ключа")
    void getShouldReturnNullForNonExistingKey() {
        map.put("exists", 5);
        assertThat(map.get("does_not_exist")).isNull();
        assertThat(map.get(null)).isNull(); // null-ключ пока не добавляли
    }

    @Test
    @DisplayName("containsKey() должен проверять наличие ключа")
    void containsKeyShouldCheckKeyExistence() {
        map.put("a", 1);
        assertThat(map.containsKey("a")).isTrue();
        assertThat(map.containsKey("b")).isFalse();
    }

    @Test
    @DisplayName("remove() должен удалять пару и возвращать значение")
    void removeShouldDeleteEntryAndReturnValue() {
        map.put("rem", 100);
        assertThat(map.containsKey("rem")).isTrue();
        assertThat(map.size()).isEqualTo(1);

        Integer removed = map.remove("rem");
        assertThat(removed).isEqualTo(100);
        assertThat(map.size()).isEqualTo(0);
        assertThat(map.containsKey("rem")).isFalse();
        assertThat(map.get("rem")).isNull();
    }

    @Test
    @DisplayName("remove() должен возвращать null для несуществующего ключа")
    void removeShouldReturnNullForNonExistingKey() {
        map.put("x", 1);
        Integer removed = map.remove("nonexistent");
        assertThat(removed).isNull();
        assertThat(map.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("Обработка коллизий: добавление элементов с одинаковым индексом корзины")
    void shouldHandleCollisions() {

        for (int i = 0; i < 50; i++) {
            map.put("key-coll-" + i, i);
        }
        assertThat(map.size()).isEqualTo(50);
        for (int i = 0; i < 50; i++) {
            assertThat(map.get("key-coll-" + i)).isEqualTo(i);
        }
    }

    @Test
    @DisplayName("resize() должен корректно перестраивать таблицу при росте")
    void resizeShouldRehashTableCorrectly() {

        SimpleHashMap<String, Integer> small = new SimpleHashMap<>(4, 0.75f);
        int count = 20;
        for (int i = 0; i < count; i++) {
            small.put("k" + i, i);
        }
        assertThat(small.size()).isEqualTo(count);
        for (int i = 0; i < count; i++) {
            assertThat(small.get("k" + i)).isEqualTo(i);
        }
    }

    @Test
    @DisplayName("clear() должен удалять все записи")
    void clearShouldRemoveAllEntries() {
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        assertThat(map.isEmpty()).isFalse();
        assertThat(map.size()).isEqualTo(3);

        map.clear();
        assertThat(map.size()).isEqualTo(0);
        assertThat(map.isEmpty()).isTrue();
        assertThat(map.get("a")).isNull();
        assertThat(map.get("b")).isNull();
        assertThat(map.get("c")).isNull();
    }
}
