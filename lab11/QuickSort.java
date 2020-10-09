import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.QuickUnionUF;

import javax.management.MBeanRegistration;

public class QuickSort {
    /**
     * Returns a new queue that contains the given queues catenated together.
     *
     * The items in q2 will be catenated after all of the items in q1.
     *
     * @param q1  A Queue of items
     * @param q2  A Queue of items
     * @return    A Queue containing the items of 
     *            q1 followed by the items of q2.
     */
    private static <Item extends Comparable> Queue<Item> catenate(Queue<Item> q1, Queue<Item> q2) {
        Queue<Item> catenated = new Queue<Item>();
        for (Item item : q1) {
            catenated.enqueue(item);
        }
        for (Item item: q2) {
            catenated.enqueue(item);
        }
        return catenated;
    }

    /**
     * Returns a random item from the given queue.
     *
     * @param items  A Queue of items
     * @return       A random item from items
     */
    private static <Item extends Comparable> Item getRandomItem(Queue<Item> items) {
        int pivotIndex = (int) (Math.random() * items.size());
        Item pivot = null;
        // Walk through the queue to find the item at the given index.
        for (Item item : items) {
            if (pivotIndex == 0) {
                pivot = item;
                break;
            }
            pivotIndex--;
        }
        return pivot;
    }

    /**
     * Partitions the given unsorted queue by pivoting on the given item.
     *
     * @param unsorted  A Queue of unsorted items
     * @param pivot     The item to pivot on
     * @param less      An empty Queue. When the function completes, this queue will contain
     *                  all of the items in unsorted that are less than the given pivot.
     * @param equal     An empty Queue. When the function completes, this queue will contain
     *                  all of the items in unsorted that are equal to the given pivot.
     * @param greater   An empty Queue. When the function completes, this queue will contain
     *                  all of the items in unsorted that are greater than the given pivot.
     */
    private static <Item extends Comparable> void partition(
            Queue<Item> unsorted, Item pivot,
            Queue<Item> less, Queue<Item> equal, Queue<Item> greater) {
        if (unsorted.isEmpty()) {
            return;
        }
        for (Item item : unsorted) {
            int cmp = item.compareTo(pivot);
            if (cmp < 0) {
                less.enqueue(item);
            } else if (cmp == 0) {
                equal.enqueue(item);
            } else {
                greater.enqueue(item);
            }
        }
    }

    /**
     * Returns a Queue that contains the given items sorted from least to greatest.
     *
     * @param items  A Queue of possibly unsorted items
     * @return       A Queue of sorted items
     */
    public static <Item extends Comparable> Queue<Item> quickSort(
            Queue<Item> items) {
        if (items == null || items.isEmpty()) {
            return items;
        }
        Item pivot = getRandomItem(items);
        Queue<Item> less = new Queue<>();
        Queue<Item> equal = new Queue<>();
        Queue<Item> greater = new Queue<>();
        partition(items, pivot, less, equal, greater);
        less = quickSort(less);
        greater = quickSort(greater);
        return catenate(catenate(less, equal), greater);
    }

    public static void quickSortArray(int[] items) {
        quickSortArrayHelper(items, 0, items.length - 1);
    }

    private static void quickSortArrayHelper(int[] items, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int j = partitionArray(items, lo, hi);
        quickSortArrayHelper(items, lo, j - 1);
        quickSortArrayHelper(items, j + 1, hi);
    }

    private static int partitionArray(int[] items, int lo, int hi) {
        int pivot = items[lo];
        int i = lo, j = hi + 1;
        while (true) {
            while (items[++i] < pivot) {
                if (i == hi) {
                    break;
                }
            }
            while (items[--j] > pivot) {

            }
            if (i >= j) {
                break;
            }
            int temp = items[i];
            items[i] = items[j];
            items[j] = temp;
        }
        items[lo] = items[j];
        items[j] = pivot;
        return j;
    }

    public static void main(String[] args) {
        int[] a = new int[]{10, 9, 8, 6};
        quickSortArray(a);
        for (int num : a) {
            System.out.println(num);
        }
    }
}
