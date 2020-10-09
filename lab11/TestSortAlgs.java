import edu.princeton.cs.algs4.Queue;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestSortAlgs {

    @Test
    public void testQuickSort() {
        Queue<Double> queue = new Queue<>();
        queue.enqueue(1.3);
        queue.enqueue(1.9);
        queue.enqueue(9.3);
        queue.enqueue(-1.3);
        queue.enqueue(9.9);
        queue.enqueue(.0);
        queue = QuickSort.quickSort(queue);
        assertTrue(isSorted(queue));
    }

    @Test
    public void testMergeSort() {
        Queue<Double> queue = new Queue<>();
        queue.enqueue(1.3);
        queue.enqueue(1.9);
        queue.enqueue(9.3);
        queue.enqueue(-1.3);
        queue.enqueue(9.9);
        queue.enqueue(.0);
        queue = MergeSort.mergeSort(queue);
        assertTrue(isSorted(queue));
    }

    /**
     * Returns whether a Queue is sorted or not.
     *
     * @param items  A Queue of items
     * @return       true/false - whether "items" is sorted
     */
    private <Item extends Comparable> boolean isSorted(Queue<Item> items) {
        if (items.size() <= 1) {
            return true;
        }
        Item curr = items.dequeue();
        Item prev = curr;
        while (!items.isEmpty()) {
            prev = curr;
            curr = items.dequeue();
            if (curr.compareTo(prev) < 0) {
                return false;
            }
        }
        return true;
    }
}
