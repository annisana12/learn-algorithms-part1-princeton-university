package fundamental;

import edu.princeton.cs.algs4.StdIn;

import java.util.Iterator;
import java.util.NoSuchElementException;

/******************************************************************************
 *  Original Author: Robert Sedgewick and Kevin Wayne
 *  Data: tobe.txt
 ******************************************************************************/

public class Queue<E> implements Iterable<E> {
    private Node<E> first; // Least recently added item
    private Node<E> last; // recently added item
    private int n; // queue size

    private static class Node<E> {
        E item;
        Node<E> next;
    }

    public Queue() {
        first = null;
        last = null;
        n = 0;
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void enqueue(E item) {
        Node<E> oldLast = last;
        last = new Node<>();
        last.item = item;
        last.next = null;
        n++;

        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
    }

    public E dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }

        E item = first.item;
        first = first.next;
        n--;

        if (isEmpty()) {
            last = null;
        }

        return item;
    }

    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }

        return first.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedIterator(first);
    }

    private class LinkedIterator implements Iterator<E> {
        private Node<E> current;

        public LinkedIterator(Node<E> first) {
            current = first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            E item = current.item;
            current = current.next;

            return item;
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        for (E item : this) {
            s.append(item);
            s.append(' ');
        }

        return s.toString();
    }

    public static void main(String[] args) {
        Queue<String> queue = new Queue<>();

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
        System.out.println(queue.size() + " items left in queue :");
        System.out.println(queue);
    }
}
