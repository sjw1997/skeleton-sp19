import java.util.*;

public class MyHashMap<K extends Comparable<K> , V> implements Map61B<K, V> {
    private LinkedList<Node>[] items;
    private double loadFactor;
    private int size;
    private class Node {
        public K key;
        public V val;
        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    public MyHashMap() {
        this(16, 0.75);
    }
    public MyHashMap(int initialSize) {
        this(initialSize, 0.75);
    }
    public MyHashMap(int initialSize, double loadFactor) {
        this.items = new LinkedList[initialSize];
        this.loadFactor = loadFactor;
        this.size = 0;
        for (int i = 0; i < initialSize; i++) {
            this.items[i] = new LinkedList<>();
        }
    }


    /**
     * Removes all of the mappings from this map.
     */
    @Override
    public void clear() {
        for (int i = 0; i < this.items.length; i++) {
            this.items[i].clear();
        }
        this.size = 0;
    }

    /**
     * Returns true if this map contains a mapping for the specified key.
     *
     * @param key
     */
    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    /**
     * Return the key's index of the hash table.
     * @param key
     */
    private int index(K key) {
        return (key.hashCode() & 0x7fffffff) % this.items.length;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     *
     * @param key
     */
    @Override
    public V get(K key) {
        int indice = index(key);
        for (Node node: this.items[indice]) {
            if (key.equals(node.key)) {
                return node.val;
            }
        }
        return null;
    }

    /**
     * Returns the number of key-value mappings in this map.
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     *
     * @param key
     * @param value
     */
    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Key can't be null");
        }
        int indice = index(key);
        LinkedList<Node> list = this.items[indice];
        for (Node node: list) {
            if (key.equals(node.key)) {
                node.val = value;
                return;
            }
        }
        list.add(new Node(key, value));
        this.size++;
        if ((double)this.size / this.items.length > this.loadFactor) {
            resizing();
        }
    }

    /**
     * Resizing the hash table when loadfactor is too large.
     */
    private void resizing() {
        int newSize = 2 * this.items.length;
        LinkedList<Node>[] newItems = new LinkedList[newSize];
        for (int i = 0; i < newSize; i++) {
            newItems[i] = new LinkedList<>();
        }
        for (LinkedList<Node> list: this.items) {
            for (Node node: list) {
                int indice = (node.key.hashCode() & 0x7fffffff)
                                % newSize;
                newItems[indice].add(node);
            }
        }
        this.items = newItems;
    }

    /**
     * Returns a Set view of the keys contained in this map.
     */
    @Override
    public Set<K> keySet() {
        if (this.size == 0) {
            return null;
        }
        Set<K> set = new HashSet<>();
        for (LinkedList<Node> list: this.items) {
            for (Node node: list) {
                set.add(node.key);
            }
        }
        return set;
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     *
     * @param key
     */
    @Override
    public V remove(K key) {
        int indice = index(key);
        LinkedList<Node> list = this.items[indice];
        for (Node node: list) {
            if (key.equals(node.key)) {
                V res = node.val;
                list.remove(node);
                return res;
            }
        }
        return null;
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     *
     * @param key
     * @param value
     */
    @Override
    public V remove(K key, V value) {
        int indice = index(key);
        LinkedList<Node> list = this.items[indice];
        for (Node node: list) {
            if (key.equals(node.key) && value.equals(node.val)) {
                list.remove(node);
                return value;
            }
        }
        return null;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<K> iterator() {
        return new MyHashMapIterator<>();
    }

    private class MyHashMapIterator<K> implements Iterator<K> {
        private K[] keys;
        private int indice;

        public MyHashMapIterator() {
            this.keys = (K[])keySet().toArray();
            this.indice = 0;
        }
        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return this.indice < this.keys.length;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public K next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return this.keys[this.indice++];
        }
    }

    public static void main(String[] args) {
        MyHashMap<String, Integer> temp = new MyHashMap<>();
        int N = 10;
        for (int i = 0; i < N; i++) {
            temp.put("hi" + i, i);
        }
        for (String st: temp) {
            System.out.println(st);
        }
    }
}
