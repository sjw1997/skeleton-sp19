/**
 *  LinkedListDeque is based on doubly linked list.
 * @param <T> ---- generic type, can be replaced by any type.
 */
public class LinkedListDeque<T> implements Deque<T>{
    private class StuffNode {
        public T item;
        public StuffNode prev;
        public StuffNode next;

        public StuffNode() {
            item = null;
            prev = null;
            next = null;
        }

        public StuffNode(T i, StuffNode p, StuffNode n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    private StuffNode sentinel;
    private int size;

    public LinkedListDeque() {
        size = 0;
        sentinel = new StuffNode();
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public LinkedListDeque(LinkedListDeque other) {
        size = other.size();
        sentinel = new StuffNode();
        for (int i = 0; i < size; i++) {
            addLast((T)other.get(i));
        }
    }

    @Override
    /* Add one item to the front of the deque. */
    public void addFirst(T item) {
        StuffNode p = new StuffNode(item, sentinel, sentinel.next);
        StuffNode first = sentinel.next;
        sentinel.next = p;
        first.prev = p;
        size++;
    }

    @Override
    /* Add one item to the last of the deque. */
    public void addLast(T item) {
        StuffNode p = new StuffNode(item, sentinel.prev, sentinel);
        StuffNode last = sentinel.prev;
        sentinel.prev = p;
        last.next = p;
        size++;
    }

    @Override
    /* Return the size of the deque. */
    public int size() {
        return size;
    }

    @Override
    /* Print the deque. */
    public void printDeque() {
        StuffNode p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    @Override
    /* Removes and returns the first item from the deque.
    ** If no such item exists, returns null.
    */
    public T removeFirst() {
        if (0 == size) {
            return null;
        }
        StuffNode p = sentinel.next;
        sentinel.next = p.next;
        p.next.prev = sentinel;
        size--;
        return p.item;
    }

    @Override
    /* Removes and returns the last item from the deque.
    ** If no such item exists, returns null.
    */
    public T removeLast() {
        if (0 == size) {
            return null;
        }
        StuffNode p = sentinel.prev;
        sentinel.prev = p.prev;
        p.prev.next = sentinel;
        size--;
        return p.item;
    }

    @Override
    /* Gets the item at the given index, where 0 is the front, 1 is the
    ** next item, and so forth. If no such item exists, returns null.
    ** Must not altar the deque.
     */
    public T get(int index) {
        if (index < 0 || index > size - 1) {
            return null;
        }
        StuffNode p = sentinel.next;
        for (int i = 0; i < index; p = p.next);
        return p.item;
    }

    /* Same as get, but uses recursion. */
    public T getRecursive(int index) {
        if (index < 0 || index > size - 1) {
            return null;
        }
        return getRecursive2(index, sentinel.next);
    }

    private T getRecursive2(int index, StuffNode p) {
        if (index == 0) {
            return p.item;
        }
        return getRecursive2(index - 1, p.next);
    }

    @Override
    public T getFirst() {
        return sentinel.next.item;
    }

    @Override
    public T getLast() {
        return sentinel.prev.item;
    }
}
