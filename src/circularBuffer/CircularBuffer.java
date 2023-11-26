package circularBuffer;

public class CircularBuffer<T> {
    private int size;
    private int head; // Points to the head element
    private int tail; // Points to the next available position
    private T[] buffer;

    @SuppressWarnings("unchecked")
    public CircularBuffer(int size) {
        this.size = size;
        buffer = (T[]) new Object[size];
        head = 0;
        tail = 0;
    }

    public boolean isEmpty() {
        return head == tail;
    }

    public boolean isFull() {
        return (tail + 1) % size == head;
    }

    public void add(T element) {
        if (isFull()) {
            // Buffer is full, overwrite the oldest element
            head = (head + 1) % size;
        }
        buffer[tail] = element;
        tail = (tail + 1) % size;
    }

    public T remove() {
        if (isEmpty()) {
            throw new IllegalStateException("Buffer is empty");
        }
        T element = buffer[head];
        buffer[head] = null; // Clear the element
        head = (head + 1) % size;
        return element;
    }

    public void printBuffer() {
        System.out.print("Buffer: ");
        if (isEmpty()) {
            System.out.println("Empty");
            return;
        }
        int current = head;
        while (current != tail) {
            System.out.print(buffer[current] + " ");
            current = (current + 1) % size;
        }
        System.out.println();
    }

    // Other methods for your requirements (e.g., retrieving elements, etc.)
}
