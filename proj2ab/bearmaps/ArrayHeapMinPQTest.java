package bearmaps;

import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayHeapMinPQTest {

    @Test
    public void testAdd() {
        ArrayHeapMinPQ<String> p = new ArrayHeapMinPQ<>();
        p.add("this", 0);
        p.add("is", 0);
        p.add("a", 0);
        p.add("test", 0);
        assertEquals(p.size(), 4);
    }

    @Test
    public void testContains() {
        ArrayHeapMinPQ<String> p = new ArrayHeapMinPQ<>();
        p.add("this", 0);
        p.add("is", 0);
        p.add("a", 0);
        p.add("test", 0);
        assertTrue(p.contains("this"));
        assertTrue(p.contains("is"));
        assertTrue(p.contains("test"));
        assertTrue(p.contains("a"));
        assertFalse(p.contains("hello"));
    }

    @Test
    public void testRemoveSmallest() {
        ArrayHeapMinPQ<String> p = new ArrayHeapMinPQ<>();
        p.add("this", 0);
        p.add("is", 1);
        p.add("a", 2);
        p.add("test", 3);
        assertEquals("this", p.removeSmallest());
        assertEquals("is", p.removeSmallest());
        assertEquals("a", p.removeSmallest());
        assertEquals("test", p.removeSmallest());
    }

    @Test
    public void testChangePriority() {
        ArrayHeapMinPQ<String> p = new ArrayHeapMinPQ<>();
        p.add("this", 0);
        p.add("is", 1);
        p.add("a", 2);
        p.add("test", 3);
        p.changePriority("this", -1);
        p.changePriority("test", 0.5);

        assertEquals("this", p.removeSmallest());
        assertEquals("test", p.removeSmallest());
        assertEquals("is", p.removeSmallest());
        assertEquals("a", p.removeSmallest());
    }
}
