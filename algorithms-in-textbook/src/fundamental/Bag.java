package fundamental;

import edu.princeton.cs.algs4.StdIn;

import java.util.Iterator;
import java.util.NoSuchElementException;

/******************************************************************************
 *  Original Author: Robert Sedgewick and Kevin Wayne
 *  Data: tobe.txt
 ******************************************************************************/

public class Bag<E> implements Iterable<E> {
    private Node<E> first; // Recently added item
    private int n; // Bag size

    private static class Node<E> {
        private E item;
        private Node<E> next;
    }

    public Bag() {
        first = null;
        n = 0;
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void add(E item) {
        Node<E> oldFirst = first;
        first = new Node<>();
        first.item = item;
        first.next = oldFirst;
        n++;
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

    public static void main(String[] args) {
        Bag<String> bag = new Bag<>();

        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            bag.add(item);
        }

        System.out.println("Bag size : " + bag.size());

        for (String item : bag) {
            System.out.println(item);
        }
    }
}
