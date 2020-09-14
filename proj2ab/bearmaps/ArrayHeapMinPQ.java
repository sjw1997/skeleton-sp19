package bearmaps;

import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {

    private static final int INIT_SIZE = 10;

    private T[] items;
    private double[] priorities;
    private int n;

    public ArrayHeapMinPQ(int cap) {
        items = (T[]) new Object[cap + 1];
        priorities = new double[cap + 1];
        n = 0;
    }

    public ArrayHeapMinPQ() {
        this(INIT_SIZE);
    }

    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public void add(T item, double priority) {
        if (item == null) {
            throw new NullPointerException("calling add() with null");
        }
        if (contains(item)) {
            throw new IllegalArgumentException("calling add() with already existed item");
        }
        n++;
        items[n] = item;
        priorities[n] = priority;
        swim(n);
        if (n == items.length - 1) {
            resize(2 * items.length);
        }
    }

    private void resize(int cap) {
        T[] newI = (T[]) new Object[cap + 1];
        double[] newP = new double[cap +  1];
        for (int i = 1; i <= n; i++) {
            newI[i] = items[i];
            newP[i] = priorities[i];
        }
        items = newI;
        priorities = newP;
    }

    private void swap(int i, int j) {
        T x = items[i];
        items[i] = items[j];
        items[j] = x;

        double y = priorities[i];
        priorities[i] = priorities[j];
        priorities[j] = y;
    }

    private void sink(int i) {
        while (2 * i <= n) {
            int j = 2 * i;
            if (j < n && priorities[j + 1] < priorities[j]) {
                j++;
            }
            if (priorities[j] < priorities[i]) {
                swap(i,j);
                i = j;
            } else {
                break;
            }
        }
    }

    private void swim(int i) {
        while (i > 1) {
            int j = i / 2;
            if (priorities[i] < priorities[j]) {
                swap(i, j);
                i = j;
            } else {
                break;
            }
        }
    }

    @Override
    public boolean contains(T item) {
        for (int i = 1; i <= n; i++) {
            if (items[i].equals(item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public T getSmallest() {
        if (isEmpty()) {
            throw new NoSuchElementException("calling getSmallest() with empty PQ");
        }
        return items[1];
    }

    @Override
    public T removeSmallest() {
        if (isEmpty()) {
            throw new NoSuchElementException("calling removeSmallest() with empty PQ");
        }
        swap(1, n--);
        T res = items[n + 1];
        sink(1);
        if (n > 0 && n == items.length / 4) {
            resize(items.length / 2);
        }
        return res;
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public void changePriority(T item, double priority) {
        if (!contains(item)) {
            throw new NoSuchElementException("the item does not exist");
        }
        int i = 0;
        while (!item.equals(items[++i]));
        priorities[i] = priority;
        sink(i);
        swim(i);
    }
}
