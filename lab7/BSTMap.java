import edu.princeton.cs.algs4.BST;

import java.util.Iterator;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Consumer;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private Node root;
    private int size;

    private class Node {
        private K key;
        private V val;
        private Node left;
        private Node right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    public BSTMap() {
        size = 0;
    }

    /**
     * Removes all of the mappings from this map.
     */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        if (null == key) {
            throw new IllegalArgumentException("argumnet to " +
                    "containsKey() is null");
        }
        return get(key) != null;
    }

    @Override
    public V get(K key) {
        return get(root, key);
    }

    private V get(Node r, K key) {
        if (key == null) {
            throw new IllegalArgumentException("calls " +
                    "get() with a null key");
        }
        if (r == null) {
            return null;
        }
        int cmp = key.compareTo(r.key);
        if (cmp < 0) {
            return get(r.left, key);
        } else if (cmp > 0) {
            return get(r.right, key);
        } else {
            return r.val;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("calls with " +
                    "put() is null");
        }
        root = put(root, key, value);
        size++;
    }

    private Node put(Node r, K key, V val) {
        if (r == null) {
            return new Node(key, val);
        }
        int cmp = key.compareTo(r.key);
        if (cmp > 0) {
            r.right = put(r.right, key, val);
        } else if (cmp < 0) {
            r.left = put(r.left, key, val);
        } else {
            throw new IllegalArgumentException("calls put() " +
                    "with a key that already exists");
        }
        return r;
    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    public V remove(K key, V value) {
        return null;
    }

    public void printInOrder() {
        printInOrder(this.root);
    }

    private void printInOrder(Node r) {
        if (r == null) {
            return;
        }
        printInOrder(r.left);
        System.out.println(r.val);
        printInOrder(r.right);
    }


    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<K> iterator() {
        return null;
    }
}
