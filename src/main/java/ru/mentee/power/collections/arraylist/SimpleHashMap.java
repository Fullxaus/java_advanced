package ru.mentee.power.collections.arraylist;

import java.util.Collection;
import java.util.Objects;

public class SimpleHashMap<K, V> {

    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private static final int MAXIMUM_CAPACITY = 1 << 30; // 1073741824

    private Node<K, V>[] table; // buckets
    private int size;           // number of entries
    private int threshold;      // when to resize
    private final float loadFactor;

    public SimpleHashMap() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public SimpleHashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    @SuppressWarnings("unchecked")
    public SimpleHashMap(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal initial capacity: " + initialCapacity);
        if (initialCapacity > MAXIMUM_CAPACITY)
            initialCapacity = MAXIMUM_CAPACITY;
        if (loadFactor <= 0 || Float.isNaN(loadFactor))
            throw new IllegalArgumentException("Illegal load factor: " + loadFactor);

        this.loadFactor = loadFactor;
        int capacity = tableSizeFor(Math.max(1, initialCapacity));
        this.threshold = calculateThreshold(capacity, loadFactor);
        this.table = (Node<K, V>[]) new Node[capacity];
        this.size = 0;
    }

    public Collection<Object> keySet() {

        return java.util.List.of();
    }

    static class Node<K, V> {
        final int hash;
        final K key;
        V value;
        Node<K, V> next;

        Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public final K getKey() {
            return key;
        }

        public final V getValue() {
            return value;
        }

        public final V setValue(V newValue) {
            V old = value;
            value = newValue;
            return old;
        }

        @Override
        public final boolean equals(Object o) {
            if (o == this) return true;
            if (!(o instanceof Node)) return false;
            Node<?, ?> e = (Node<?, ?>) o;
            return Objects.equals(key, e.key) && Objects.equals(value, e.value);
        }

        @Override
        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public V get(Object key) {
        int h = hash(key);
        int idx = indexFor(h, table.length);
        Node<K, V> e = table[idx];
        while (e != null) {
            if (e.hash == h && Objects.equals(e.key, key)) {
                return e.value;
            }
            e = e.next;
        }
        return null;
    }

    public boolean containsKey(Object key) {
        return get(key) != null;
    }

    public V put(K key, V value) {
        int h = hash(key);
        int idx = indexFor(h, table.length);
        Node<K, V> e = table[idx];
        for (Node<K, V> curr = e; curr != null; curr = curr.next) {
            if (curr.hash == h && Objects.equals(curr.key, key)) {
                V old = curr.value;
                curr.value = value;
                return old;
            }
        }
        // not found -> insert at head
        Node<K, V> newNode = new Node<>(h, key, value, e);
        table[idx] = newNode;
        if (++size > threshold) {
            resize();
        }
        return null;
    }

    public V remove(Object key) {
        int h = hash(key);
        int idx = indexFor(h, table.length);
        Node<K, V> prev = null;
        Node<K, V> curr = table[idx];
        while (curr != null) {
            Node<K, V> next = curr.next;
            if (curr.hash == h && Objects.equals(curr.key, key)) {
                if (prev == null) {
                    table[idx] = next;
                } else {
                    prev.next = next;
                }
                size--;
                return curr.value;
            }
            prev = curr;
            curr = next;
        }
        return null;
    }

    public void clear() {
        for (int i = 0; i < table.length; i++) table[i] = null;
        size = 0;
    }

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    static int indexFor(int h, int length) {
        return h & (length - 1);
    }

    static int calculateThreshold(int capacity, float loadFactor) {
        return (int) (capacity * loadFactor);
    }

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        n = (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
        return n;
    }

    @SuppressWarnings("unchecked")
    void resize() {
        Node<K, V>[] oldTable = table;
        int oldCap = oldTable.length;
        if (oldCap >= MAXIMUM_CAPACITY) {
            threshold = Integer.MAX_VALUE;
            return;
        }
        int newCap = oldCap << 1;
        if (newCap > MAXIMUM_CAPACITY) newCap = MAXIMUM_CAPACITY;
        Node<K, V>[] newTable = (Node<K, V>[]) new Node[newCap];
        // rehash
        for (int i = 0; i < oldCap; i++) {
            Node<K, V> e = oldTable[i];
            while (e != null) {
                Node<K, V> next = e.next;
                int idx = indexFor(e.hash, newCap);
                // insert at head of new bucket
                e.next = newTable[idx];
                newTable[idx] = e;
                e = next;
            }
            oldTable[i] = null; // help GC
        }
        table = newTable;
        threshold = calculateThreshold(newCap, loadFactor);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        boolean firstBucket = true;
        for (int i = 0; i < table.length; i++) {
            Node<K, V> e = table[i];
            if (e == null) continue;
            if (!firstBucket) sb.append(", ");
            firstBucket = false;
            sb.append("bucket").append(i).append(": [");
            boolean first = true;
            for (Node<K, V> curr = e; curr != null; curr = curr.next) {
                if (!first) sb.append(" -> ");
                first = false;
                sb.append(curr.toString());
            }
            sb.append("]");
        }
        sb.append("}");
        return sb.toString();
    }
}
