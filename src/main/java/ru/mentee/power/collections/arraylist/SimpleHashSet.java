package ru.mentee.power.collections.arraylist;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * Простая реализация HashSet поверх SimpleHashMap.
 */
public class SimpleHashSet<E> implements Set<E> {

    private transient SimpleHashMap<E, Object> map;

    // Заглушка для значений в HashMap
    private static final Object PRESENT = new Object();

    // --- Конструкторы ---

    public SimpleHashSet() {
        this.map = new SimpleHashMap<>();
    }

    public SimpleHashSet(int initialCapacity, float loadFactor) {
        this.map = new SimpleHashMap<>(initialCapacity, loadFactor);
    }

    public SimpleHashSet(int initialCapacity) {
        this.map = new SimpleHashMap<>(initialCapacity);
    }

    // --- Основные методы Set ---

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    @Override
    public boolean add(E e) {
        return map.put(e, PRESENT) == null;
    }

    @Override
    public boolean remove(Object o) {
        return map.remove(o) != null;
    }

    @Override
    public void clear() {
        map.clear();
    }

    // --- Дополнительные методы интерфейса Set (простые реализации) ---

    @Override
    public Iterator<E> iterator() {
        return (Iterator<E>) map.keySet().iterator();
    }

    @Override
    public Object[] toArray() {
        return map.keySet().toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return map.keySet().toArray(a);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return map.keySet().containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean modified = false;
        for (E e : c) {
            if (add(e)) modified = true;
        }
        return modified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return map.keySet().retainAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return map.keySet().removeAll(c);
    }

    @Override
    public String toString() {
        return map.keySet().toString();
    }

    @Override
    public boolean equals(Object o) {
        return map.keySet().equals(o);
    }

    @Override
    public int hashCode() {
        return map.keySet().hashCode();
    }
}
