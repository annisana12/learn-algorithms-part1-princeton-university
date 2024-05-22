package fundamental;

import edu.princeton.cs.algs4.StdIn;

import java.util.Iterator;
import java.util.NoSuchElementException;

/******************************************************************************
 *  Original Author: Robert Sedgewick and Kevin Wayne
 *  Data: tobe_more.txt
 ******************************************************************************/

public class ResizingArrayQueue<E> implements Iterable<E> {
    private E[] q; // queue elements
    private int n; // number of elements
    private int head; // first element index
    private int tail; // last element index + 1
    private static final int INIT_CAPACITY = 10;

    public ResizingArrayQueue() {
        q = (E[]) new Object[INIT_CAPACITY];
        n = 0;
        head = 0;
        tail = 0;
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public boolean isFull() {
        return n == q.length;
    }

    private void resize(int capacity) {
        assert capacity >= n;

        E[] newArr = (E[]) new Object[capacity];

        for (int i = 0; i < n; i++) {
            newArr[i] = q[(head + i) % q.length];
        }

        q = newArr;
        head = 0;
        tail = n;
    }

    public void enqueue(E item) {
        if (isFull()) {
            resize(q.length * 2);
        }

        q[tail++] = item;

        if (tail == q.length) {
            tail = 0;
        }

        n++;
    }

    public E dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }

        E removedItem = q[head];
        q[head] = null;
        head++;
        n--;

        if (head == q.length) {
            head = 0;
        }

        if (n > 0 && n == q.length / 4) {
            resize(q.length / 2);
        }

        return removedItem;
    }

    /**
     * Look the item least recently added to
     * this queue without removing it
     *
     * @return item least recently added
     */
    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }

        return q[head];
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<E> {
        private int i = 0;

        @Override
        public boolean hasNext() {
            return i < n;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            E item = q[(head + i) % q.length];
            i++;

            return item;
        }
    }

    public static void main(String[] args) {
        ResizingArrayQueue<String> queue = new ResizingArrayQueue<>();

        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();

            if (item.equals("-")) {
                String removedItem = queue.dequeue();
                System.out.print(removedItem + " ");
            } else {
                queue.enqueue(item);
            }
        }

        System.out.println();
        System.out.println("Items left in queue :");

        for (String s : queue) {
            System.out.print(s + " ");
        }
    }
}
