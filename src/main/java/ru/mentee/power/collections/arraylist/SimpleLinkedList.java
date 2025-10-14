package ru.mentee.power.collections.arraylist;

import org.jetbrains.annotations.NotNull;

import java.util.*;

    public class SimpleLinkedList<E> implements List {
    private int size = 0;
    private Node<E> first;
    private Node<E> last;

    // --- Конструктор ---
    public SimpleLinkedList() {

    }

    // --- Внутренний класс Node ---
    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

// --- Основные методы List ---

        public int size() {
        return size;
    }

        public boolean isEmpty() {
        return size == 0;
    }

        public boolean add(Object e) {
        linkLast((E) e);
        return true;
    }

        public E get(int index) {
        checkElementIndex(index);
        return node(index).item;
    }

    @Override
        public Object set(int index, Object element) {
        return null;
    }

    @Override
        public void add(int index, Object element) {

    }

    public E remove(int index) {
        checkElementIndex(index);
        return unlink(node(index));
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public @NotNull ListIterator listIterator() {
        return null;
    }

    @Override
    public @NotNull ListIterator listIterator(int index) {
        return null;
    }

    @Override
    public @NotNull List subList(int fromIndex, int toIndex) {
        return List.of();
    }

    public boolean remove(Object o) {
        for (Node<E> x = first; x != null; x = x.next) {
            if (Objects.equals(o, x.item)) {
                unlink(x);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addAll(@NotNull Collection c) {
        return false;
    }

    @Override
    public boolean addAll(int index, @NotNull Collection c) {
        return false;
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

    public boolean contains(Object o) {
        for (Node<E> x = first; x != null; x = x.next) {
            if (Objects.equals(o, x.item)) {
                return true;
            }
        }
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

    public void clear() {
        for (Node<E> x = first; x != null; ) {
            Node<E> next = x.next;
            x.item = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        first = last = null;
        size = 0;
    }

// --- Методы Deque ---

    public void addFirst(Object e) {
        linkFirst((E) e);
    }

    public void addLast(Object e) {
        linkLast((E) e);
    }

    public E getFirst() {
        if (first == null) throw new NoSuchElementException();
        return first.item;
    }

    public E getLast() {
        if (last == null) throw new NoSuchElementException();
        return last.item;
    }

    public E removeFirst() {
        if (first == null) throw new NoSuchElementException();
        return unlinkFirst(first);
    }

    public E removeLast() {
        if (last == null) throw new NoSuchElementException();
        return unlinkLast(last);
    }

// --- Вспомогательные методы ---

    private void linkFirst(E e) {
        final Node<E> f = first;
        final Node<E> newNode = new Node<>(null, e, f);
        first = newNode;
        if (f == null) {
            last = newNode;
        } else {
            f.prev = newNode;
        }
        size++;
    }

    private void linkLast(E e) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
    }

    E unlinkFirst(Node<E> f) {
        final E element = f.item;
        final Node<E> next = f.next;
        f.item = null;
        f.next = null; // help GC
        first = next;
        if (next == null) {
            last = null;
        } else {
            next.prev = null;
        }
        size--;
        return element;
    }

    E unlinkLast(Node<E> l) {
        final E element = l.item;
        final Node<E> prev = l.prev;
        l.item = null;
        l.prev = null; // help GC
        last = prev;
        if (prev == null) {
            first = null;
        } else {
            prev.next = null;
        }
        size--;
        return element;
    }

    E unlink(Node<E> x) {
        final E element = x.item;
        final Node<E> next = x.next;
        final Node<E> prev = x.prev;

        if (prev == null) {
            // x is first
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            // x is last
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.item = null;
        size--;
        return element;
    }

    Node<E> node(int index) {
        // assumes index is valid (0 <= index < size)
        if (index < (size >> 1)) {
            Node<E> x = first;
            for (int i = 0; i < index; i++) x = x.next;
            return x;
        } else {
            Node<E> x = last;
            for (int i = size - 1; i > index; i--) x = x.prev;
            return x;
        }
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index)) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    @Override
    public String toString() {
        if (first == null) return "[]";
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        Node<E> x = first;
        while (x != null) {
            sb.append(x.item);
            x = x.next;
            if (x != null) sb.append(", ");
        }
        sb.append(']');
        return sb.toString();
    }

}
