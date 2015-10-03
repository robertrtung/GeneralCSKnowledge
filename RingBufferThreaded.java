package from223;

public class RingBufferTest {
	 /* The actual ring buffer. */
    private final int[] elements;

    /* The write pointer, represented as an offset into the array. */
    private int offset = 0;

    private int unconsumedElements = 0;

    public RingBufferTest(int size) {
        /* Validate the size. */
        if (size <= 0)
            throw new IllegalArgumentException("RingBuffer capacity must be positive.");
        
        elements = new int[size];
    }

    public synchronized void add(int elem) throws InterruptedException {
        /* Block until the capacity is nonzero.  Otherwise we don't have any
         * space to write.
         */
        while (unconsumedElements == elements.length)
            wait();

        /* Write the element into the next open spot, then advance the write
         * pointer forward a step.
         */
        elements[offset] = elem;
        offset = (offset + 1) % elements.length;

        /* Increase the number of unconsumed elements by one, then notify any
         * threads that are waiting that more data is now available.
         */
        ++unconsumedElements;
        notifyAll();
    }

    /**
     * Returns the maximum capacity of the ring buffer.
     *
     * @return The maximum capacity of the ring buffer.
     */
    public int capacity() {
        return elements.length;
    }

    public synchronized int peek() throws InterruptedException {
        /* Wait for data to become available. */
        while (unconsumedElements == 0)
            wait();

        /* Hand back the next value.  The index of this next value is a bit
         * tricky to compute.  We know that there are unconsumedElements
         * elements waiting to be read, and they're contiguously before the
         * write position.  However, the buffer wraps around itself, and so we
         * can't just do a naive subtraction; that might end up giving us a
         * negative index.  To avoid this, we'll use a clever trick in which
         * we'll add to the index the capacity minus the distance.  This value
         * must be positive, since the distance is never greater than the
         * capacity, and if we then wrap this value around using the modulus
         * operator we'll end up with a valid index.  All of this machinery
         * works because
         *
         *                 (x + (n - k)) mod n == (x - k) mod n
         * 
         * And Java's modulus operator works best on positive values.
         */
        return elements[(offset + (capacity() - unconsumedElements)) % capacity()];
    }

    /**
     * Removes and returns the next available element, blocking until data
     * becomes available.
     *
     * @return The next available element
     * @throws InterruptedException If the caller is interrupted before data
     *                              becomes available.
     */
    public synchronized int remove() throws InterruptedException {
        /* Use peek() to get the element to return. */
        int result = peek();

        /* Mark that one fewer elements are now available to read. */
        --unconsumedElements;

        /* Because there is more space left, wake up any waiting threads. */
        notifyAll();

        return result;
    }

    public synchronized int size() {
        return unconsumedElements;
    }
    
    public synchronized boolean isEmpty() {
        return size() == 0;
    }
}
