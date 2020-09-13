public interface Deque<Item> {
    public void addFirst(Item item);
    public void addLast(Item item);
    public void printDeque();
    public int size();
    public Item removeFirst();
    public Item removeLast();
    public Item get(int index);
    public Item getFirst();
    public Item getLast();
    default boolean isEmpty() {
        return size() == 0;
    }
}
