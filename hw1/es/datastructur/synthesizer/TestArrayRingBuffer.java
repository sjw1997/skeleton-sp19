package es.datastructur.synthesizer;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer actual = new ArrayRingBuffer(10);

    }

    @Test
    public void testEquals() {
        ArrayRingBuffer<String> actual = new ArrayRingBuffer<>(10);
        ArrayRingBuffer<String> expect = new ArrayRingBuffer<>(10);
        actual.enqueue("hello");
        actual.enqueue("world");
        actual.enqueue("bupt");
        actual.enqueue("bye");
        actual.dequeue();
        actual.dequeue();
        assertNotEquals(expect, actual);

        expect.enqueue("bupt");
        expect.enqueue("bye");
        assertEquals(expect, actual);

        ArrayRingBuffer<Character> temp = new ArrayRingBuffer<>(9);
        assertNotEquals(temp, actual);
    }

    @Test
    public void testIterator() {
        ArrayRingBuffer<String> actual = new ArrayRingBuffer<>(10);
        actual.enqueue("hello");
        actual.enqueue("world");
        actual.enqueue("bupt");
        actual.enqueue("bye");
        actual.dequeue();
        actual.dequeue();
        List<String> output = new LinkedList<>();
        List<String> expect = new LinkedList<>();
        expect.add("bupt");
        expect.add("bye");
        for (String temp : actual) {
            output.add(temp);
        }
        assertEquals(expect, output);
    }
}
