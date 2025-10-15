package ru.mentee.power.collections;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class SimplePriorityQueue<E>implements Queue {
    private static final int DEFAULT_INITIAL_CAPACITY = 11;
    private Object[] queue;
    private int size = 0;
    private final Comparator<? super E> comparator;

    public SimplePriorityQueue() {
        this(DEFAULT_INITIAL_CAPACITY, null);
    }

    public SimplePriorityQueue(Comparator<? super E> comparator) {
        this(DEFAULT_INITIAL_CAPACITY, comparator);
    }

    public SimplePriorityQueue(int initialCapacity) {
        this(initialCapacity, null);
    }

    public SimplePriorityQueue(int initialCapacity, Comparator<? super E> comparator) {
        if (initialCapacity < 1)
            throw new IllegalArgumentException("initialCapacity must be > 0");
        this.queue = new Object[initialCapacity];
        this.comparator = comparator;
    }

    public boolean offer(Object e) {
        if (e == null) throw new NullPointerException("Null elements not permitted");
        int i = size;
        if (i >= queue.length)
            grow(i + 1);
        size = i + 1;
        if (i == 0) {
            queue[0] = e;
        } else {
            siftUp(i, (E) e);
        }
        return true;
    }

    @Override
    public boolean add(Object o) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean addAll(@NotNull Collection c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean retainAll(@NotNull Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(@NotNull Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(@NotNull Collection c) {
        return false;
    }



    @Override
    public Object remove() {
        return null;
    }

    @SuppressWarnings("unchecked")
    public E poll() {
        if (size == 0) return null;
        int s = --size;
        E result = (E) queue[0];
        E x = (E) queue[s];
        queue[s] = null;
        if (s != 0) {
            siftDown(0, x);
        }
        return result;
    }

    @Override
    public Object element() {
        return null;
    }

    @SuppressWarnings("unchecked")
    public E peek() {
        return (size == 0) ? null : (E) queue[0];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public @NotNull Iterator iterator() {
        return null;
    }

    @Override
    public @NotNull Object[] toArray() {
        return new Object[0];
    }

    @Override
    public @NotNull Object[] toArray(@NotNull Object[] a) {
        return new Object[0];
    }

    private void grow(int minCapacity) {
        int oldCapacity = queue.length;
        int newCapacity;
        if (oldCapacity < 64) {
            newCapacity = (oldCapacity << 1) + 2;
        } else {
            newCapacity = oldCapacity + (oldCapacity >> 1);
        }
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        if (newCapacity - Integer.MAX_VALUE > 0)
            newCapacity = hugeCapacity(minCapacity);
        queue = Arrays.copyOf(queue, newCapacity);
    }

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > Integer.MAX_VALUE - 8) ?
                Integer.MAX_VALUE :
                Integer.MAX_VALUE - 8;
    }

    private void siftUp(int k, E x) {
        if (comparator != null)
            siftUpUsingComparator(k, x);
        else
            siftUpComparable(k, x);
    }

    @SuppressWarnings("unchecked")
    private void siftUpComparable(int k, E x) {
        Comparable<? super E> key = (Comparable<? super E>) x;
        while (k > 0) {
            int parent = (k - 1) >>> 1;
            Object e = queue[parent];
            if (key.compareTo((E) e) >= 0)
                break;
            queue[k] = e;
            k = parent;
        }
        queue[k] = key;
    }

    @SuppressWarnings("unchecked")
    private void siftUpUsingComparator(int k, E x) {
        while (k > 0) {
            int parent = (k - 1) >>> 1;
            Object e = queue[parent];
            if (comparator.compare(x, (E) e) >= 0)
                break;
            queue[k] = e;
            k = parent;
        }
        queue[k] = x;
    }

    private void siftDown(int k, E x) {
        if (comparator != null)
            siftDownUsingComparator(k, x);
        else
            siftDownComparable(k, x);
    }

    @SuppressWarnings("unchecked")
    private void siftDownComparable(int k, E x) {
        Comparable<? super E> key = (Comparable<? super E>) x;
        int half = size >>> 1;
        while (k < half) { // node has at least one child
            int child = (k << 1) + 1;
            Object c = queue[child];
            int right = child + 1;
            if (right < size) {
                Object rightObj = queue[right];
                if (((Comparable<? super E>) rightObj).compareTo((E) c) < 0) {
                    child = right;
                    c = rightObj;
                }
            }
            if (key.compareTo((E) c) <= 0)
                break;
            queue[k] = c;
            k = child;
        }
        queue[k] = key;
    }

    @SuppressWarnings("unchecked")
    private void siftDownUsingComparator(int k, E x) {
        int half = size >>> 1;
        while (k < half) {
            int child = (k << 1) + 1;
            Object c = queue[child];
            int right = child + 1;
            if (right < size) {
                Object rightObj = queue[right];
                if (comparator.compare((E) rightObj, (E) c) < 0) {
                    child = right;
                    c = rightObj;
                }
            }
            if (comparator.compare(x, (E) c) <= 0)
                break;
            queue[k] = c;
            k = child;
        }
        queue[k] = x;
    }

}
