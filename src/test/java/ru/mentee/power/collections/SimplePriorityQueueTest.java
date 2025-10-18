package ru.mentee.power.collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Comparator;


import static org.assertj.core.api.Assertions.*;

@DisplayName("Тесты для SimplePriorityQueue")
public class SimplePriorityQueueTest {
    private SimplePriorityQueue<Integer> minHeap;
    private SimplePriorityQueue<Integer> maxHeap;

    @BeforeEach
    void setUp() {
        minHeap = new SimplePriorityQueue<>();
        maxHeap = new SimplePriorityQueue<>(Comparator.reverseOrder());
    }

    @Test
    @DisplayName("offer() и poll() должны сохранять порядок min-heap")
    void offerAndPollShouldMaintainMinHeapOrder() {
        int[] values = {5, 1, 9, 3, 7, 2, 8, 4, 6, 0};
        for (int v : values) minHeap.offer(v);

        for (int expected = 0; expected <= 9; expected++) {
            Integer polled = minHeap.poll();
            assertThat(polled).isEqualTo(expected);
        }
        assertThat(minHeap.poll()).isNull();
        assertThat(minHeap.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("offer() и poll() должны сохранять порядок max-heap")
    void offerAndPollShouldMaintainMaxHeapOrder() {
        int[] values = {5, 1, 9, 3, 7, 2, 8, 4, 6, 0};
        for (int v : values) maxHeap.offer(v);

        for (int expected = 9; expected >= 0; expected--) {
            Integer polled = maxHeap.poll();
            assertThat(polled).isEqualTo(expected);
        }
        assertThat(maxHeap.poll()).isNull();
        assertThat(maxHeap.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("peek() должен возвращать минимальный/максимальный элемент без удаления")
    void peekShouldReturnTopElementWithoutRemoval() {
        minHeap.offer(10);
        minHeap.offer(3);
        minHeap.offer(7);

        assertThat(minHeap.peek()).isEqualTo(3);
        assertThat(minHeap.size()).isEqualTo(3);
        assertThat(minHeap.peek()).isEqualTo(3);

        maxHeap.offer(10);
        maxHeap.offer(3);
        maxHeap.offer(7);

        assertThat(maxHeap.peek()).isEqualTo(10);
        assertThat(maxHeap.size()).isEqualTo(3);
        assertThat(maxHeap.peek()).isEqualTo(10);
    }

    @Test
    @DisplayName("poll() должен возвращать null для пустой очереди")
    void pollShouldReturnNullForEmptyQueue() {
        assertThat(minHeap.poll()).isNull();
        assertThat(maxHeap.poll()).isNull();
    }

    @Test
    @DisplayName("peek() должен возвращать null для пустой очереди")
    void peekShouldReturnNullForEmptyQueue() {
        assertThat(minHeap.peek()).isNull();
        assertThat(maxHeap.peek()).isNull();
    }

    @Test
    @DisplayName("size() и isEmpty() должны работать корректно")
    void sizeAndIsEmptyShouldWorkCorrectly() {
        assertThat(minHeap.isEmpty()).isTrue();
        assertThat(minHeap.size()).isZero();

        minHeap.offer(1);
        assertThat(minHeap.isEmpty()).isFalse();
        assertThat(minHeap.size()).isEqualTo(1);

        minHeap.offer(2);
        assertThat(minHeap.size()).isEqualTo(2);

        minHeap.poll();
        assertThat(minHeap.size()).isEqualTo(1);

        minHeap.poll();
        assertThat(minHeap.isEmpty()).isTrue();
        assertThat(minHeap.size()).isZero();
    }

    @Test
    @DisplayName("Добавление большого количества элементов должно вызывать рост массива")
    void addingManyElementsShouldTriggerGrow() {
        SimplePriorityQueue<Integer> small = new SimplePriorityQueue<>(3);
        int n = 2000;
        for (int i = 0; i < n; i++) {
            int finalI = i;
            assertThatCode(() -> small.offer(n - finalI)).doesNotThrowAnyException();
        }
        assertThat(small.size()).isEqualTo(n);

        // Проверим корректность порядка (min-heap)
        int prev = -1;
        for (int i = 0; i < n; i++) {
            Integer polled = small.poll();
            if (i == 0) prev = polled;
            else {
                assertThat(polled).isGreaterThanOrEqualTo(prev);
                prev = polled;
            }
        }
        assertThat(small.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("offer() должен бросать NullPointerException при добавлении null")
    void offerShouldThrowNPEForNull() {
        assertThatThrownBy(() -> minHeap.offer(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("Конструктор должен бросать исключение при некорректной емкости")
    void constructorShouldThrowExceptionForInvalidCapacity() {
        assertThatThrownBy(() -> new SimplePriorityQueue<Integer>(0))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new SimplePriorityQueue<Integer>(-5))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
