/**
 *  ArrayDeque is a deque based on array.
 * @param <T> --- generic type, can be replaced by any type.
 */
public class ArrayDeque<T> implements Deque<T>{
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private double FACTOR;
    private double USAGE;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
        FACTOR = 1.25;
        USAGE = 0.25;
    }

    public ArrayDeque(ArrayDeque others) {
        items = (T[]) new Object[others.items.length];
        System.arraycopy(others.items, 0, items, 0, items.length);
        size = others.size();
        nextFirst = others.getNextFirst();
        nextLast = others.getNextLast();
    }

    /* Return the value of nextFirst. */
    private int getNextFirst() {
        return nextFirst;
    }

    /* Return the value of nextLast. */
    private int getNextLast() {
        return nextLast;
    }

    @Override
    /* Add one item to the front of the deque. */
    public void addFirst(T item) {
        if (size == items.length) {
            resizing((int)(Math.ceil(items.length * FACTOR)));
        }
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size++;
    }

    @Override
    /* Add one item to the last of the deque. */
    public void addLast(T item) {
        if (size == items.length) {
            resizing((int)(Math.ceil(items.length * FACTOR)));
        }
        items[nextLast] = item;
        nextLast = plusOne(nextLast);
        size++;
    }

    @Override
    /* Remove the first item of the deque and return the item.
    *  If no such exists, return null. */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        int first = plusOne(nextFirst);
        T temp = items[first];
        items[first] = null;
        size--;
        nextFirst = first;

        if (items.length >= 16 && ((float)size / items.length) < USAGE){
            resizing((int)(items.length / FACTOR));
        }
        return temp;
    }

    @Override
    /* Remove the last item of the deque and return the item.
    ** If no such item exists, return null.
     */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        int last = minusOne(nextLast);
        T temp = items[last];
        items[last] = null;
        size--;
        nextLast = last;

        if (items.length >= 16 && ((float)size / items.length) < USAGE){
            resizing((int)(items.length / FACTOR));
        }
        return temp;
    }

    @Override
    /* Get the item at the given index, where 0 is the front, 1 is
    ** next item, and so forth. If no such item exists, returns null.
    ** Must not altar the deque.*/
    public T get(int index) {
        if (index < 0 || index > size - 1) {
            return null;
        }
        return items[(nextFirst + 1 + index) % items.length];
    }

    @Override
    /* Return the size of the deque. */
    public int size() {
        return size;
    }

    /* Resizing the array if the deque is full. */
    private void resizing(int newSize) {
        T[] newItems = (T[]) new Object[newSize];
        nextLast = plusOne(nextFirst);
        for (int i = 0; i < size; i++) {
            newItems[nextLast] = get(i);
            nextLast = (nextLast + 1) % newSize;
        }
        items = newItems;
    }

    @Override
    /* Print the deque. */
    public void printDeque() {
        if (size == 0) {
            return;
        }
        int first = plusOne(nextFirst);
        while (first != nextLast) {
            System.out.print(items[first] + " ");
            first = (first + 1) %items.length;
        }
        System.out.println();
    }

    /* The index immediately "before" a given index. */
    private int minusOne(int index) {
        return (index - 1 + items.length) % items.length;
    }

    /* The index immediately "after" a given index. */
    private int plusOne(int index) {
        return (index + 1) % items.length;
    }

    @Override
    public T getFirst() {
        return items[plusOne(nextFirst)];
    }

    @Override
    public T getLast() {
        return items[minusOne(nextLast)];
    }
}
