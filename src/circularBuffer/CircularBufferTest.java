package circularBuffer;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class CircularBufferTest {

    @Test
    public void testEmptyBuffer() {
        CircularBuffer<Integer> buffer = new CircularBuffer<>(5);
        assertTrue(buffer.isEmpty());
        assertFalse(buffer.isFull());
    }

    @Test
    public void testAddAndRemoveElements() {
        CircularBuffer<String> buffer = new CircularBuffer<>(4);
        assertTrue(buffer.isEmpty());

        buffer.add("One");
        buffer.add("Two");
        buffer.add("Three");
        assertFalse(buffer.isEmpty());
        assertFalse(buffer.isFull());

        assertEquals("One", buffer.remove());
        assertFalse(buffer.isEmpty());
        assertFalse(buffer.isFull());

        buffer.add("Four");
        buffer.add("Five");
        assertTrue(buffer.isFull());
        assertFalse(buffer.isEmpty());

        assertEquals("Two", buffer.remove());
        assertEquals("Three", buffer.remove());
        assertEquals("Four", buffer.remove());
        assertEquals("Five", buffer.remove());
        assertTrue(buffer.isEmpty());
        assertFalse(buffer.isFull());
    }

    @Test
    public void testCircularBehavior() {
        CircularBuffer<Integer> buffer = new CircularBuffer<>(3);
        buffer.add(1);
        buffer.add(2);
        buffer.add(3);
        assertEquals(Optional.of(1), buffer.remove());

        buffer.add(4); // Overwrites oldest element (2)
        buffer.add(5);
        assertEquals(Optional.of(3), buffer.remove());
        assertEquals(Optional.of(4), buffer.remove());
        assertEquals(Optional.of(5), buffer.remove());
        assertTrue(buffer.isEmpty());
    }

    @Test
    public void testRemoveFromEmptyBuffer() {
        CircularBuffer<String> buffer = new CircularBuffer<>(3);
        assertTrue(buffer.isEmpty());
        assertThrows(IllegalStateException.class, buffer::remove);
    }
}
