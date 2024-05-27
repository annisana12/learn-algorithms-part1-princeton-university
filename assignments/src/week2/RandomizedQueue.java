package week2;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private static final int INIT_CAPACITY = 10;
    private Item[] queue;
    private int n; // Number of items

    public RandomizedQueue() {
        queue = (Item[]) new Object[INIT_CAPACITY];
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    private void resize(int capacity) {
        Item[] newQueue = (Item[]) new Object[capacity];

        for (int i = 0; i < n; i++) {
            newQueue[i] = queue[i];
        }

        queue = newQueue;
    }

    /**
     * Add item to the end of the array
     *
     * @param item is the element to be inserted to the queue
     */
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item can't be null");
        }

        if (n == queue.length) {
            resize(2 * queue.length);
        }

        queue[n++] = item;
    }

    private void assertEmptyQueue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
    }

    /**
     * Remove item at random index and moved the
     * last item in the array to that position
     *
     * @return random item removed
     */
    public Item dequeue() {
        assertEmptyQueue();

        int randomIndex = StdRandom.uniformInt(n);
        Item removedItem = queue[randomIndex];
        queue[randomIndex] = queue[--n];
        queue[n] = null;

        if (n > 0 && n == queue.length / 4) {
            resize(queue.length / 2);
        }

        return removedItem;
    }

    /**
     * Pick random item without removing it
     *
     * @return random item
     */
    public Item sample() {
        assertEmptyQueue();

        int randomIndex = StdRandom.uniformInt(n);
        return queue[randomIndex];
    }

    /**
     * Iterate over items in randomized queue in random order
     */
    @Override
    public Iterator<Item> iterator() {
        return new RandomArrayIterator();
    }

    private class RandomArrayIterator implements Iterator<Item> {
        private Item[] arr;
        private int n;

        public RandomArrayIterator() {
            n = size();
            arr = (Item[]) new Object[n];

            for (int i = 0; i < n; i++) {
                arr[i] = queue[i];
            }
        }

        @Override
        public boolean hasNext() {
            return n > 0;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            int randomIndex = StdRandom.uniformInt(n);
            Item item = arr[randomIndex];
            arr[randomIndex] = arr[--n];
            arr[n] = null;

            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private static void runTest(RandomizedQueue<String> queue, String operation, String item) {
        if (operation.equals("enqueue")) {
            queue.enqueue(item);
            System.out.println("Enqueue : " + item);
        } else if (operation.equals("dequeue")) {
            System.out.println("Dequeue : " + queue.dequeue());
        } else {
            System.out.println("Sample  : " + queue.sample());
        }

        System.out.println("Size    : " + queue.size());
        System.out.println("========================");
    }

    private static void runTest(RandomizedQueue<String> queue, String operation) {
        runTest(queue, operation, null);
    }

    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<>();

        runTest(queue, "enqueue", "to");
        runTest(queue, "enqueue", "be");
        runTest(queue, "enqueue", "or");
        runTest(queue, "sample");
        runTest(queue, "dequeue");
        runTest(queue, "enqueue", "not");
        runTest(queue, "enqueue", "when");
        runTest(queue, "sample");
        runTest(queue, "dequeue");
        runTest(queue, "enqueue", "then");
        runTest(queue, "dequeue");

        System.out.println();
        System.out.println(queue.size() + " remaining items : ");

        for (String item : queue) {
            System.out.println(item);
        }
    }
}
