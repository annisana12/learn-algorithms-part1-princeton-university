package week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<E> implements Iterable<E>{
    private Node<E> first; // Item at front
    private Node<E> last; // Item at back
    private int n; // Deque size;

    private static class Node<E> {
        private E item;

        private Node<E> prev;
        private Node<E> next;
    }

    public Deque() {
        first = null;
        last = null;
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void addFirst(E item) {
        if (item == null) {
            throw new IllegalArgumentException("item must be not null");
        }

        Node<E> oldFirst = first;
        first = new Node<>();
        first.item = item;
        first.prev = null;
        first.next = oldFirst;

        if (isEmpty()) {
            last = first;
        } else {
            oldFirst.prev = first;
        }

        n++;
    }

    public void addLast(E item) {
        if (item == null) {
            throw new IllegalArgumentException("item must be not null");
        }

        Node<E> oldLast = last;
        last = new Node<>();
        last.item = item;
        last.prev = oldLast;
        last.next = null;

        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }

        n++;
    }

    public E removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }

        E item = first.item;
        first = first.next;
        n--;

        if (isEmpty()) {
            last = null;
        } else {
            first.prev = null;
        }

        return item;
    }

    public E removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }

        E item = last.item;
        last = last.prev;
        n--;

        if (isEmpty()) {
            first = null;
        } else {
            last.next = null;
        }

        return item;
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator(first);
    }

    private class LinkedListIterator implements Iterator<E> {
        private Node<E> current;

        public LinkedListIterator(Node<E> first) {
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

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        Deque<String> deque = new Deque<>();

        deque.addFirst("to");
        System.out.println("Add from front      : to");
        System.out.println("Deque size          : " + deque.size());
        System.out.println("==================================");

        System.out.println("Remove from back    : " + deque.removeLast());
        System.out.println("Deque size          : " + deque.size());
        System.out.println("==================================");

        deque.addLast("be");
        System.out.println("Add from back       : be" );
        System.out.println("Deque size          : " + deque.size());
        System.out.println("==================================");

        System.out.println("Remove from front   : " + deque.removeFirst());
        System.out.println("Deque size          : " + deque.size());
        System.out.println("==================================");

        deque.addFirst("is");
        deque.addFirst("be");
        deque.removeFirst();
        deque.addFirst("or");
        deque.addLast("not");
        deque.removeLast();
        deque.addLast("to");
        deque.addLast("be");
        deque.removeFirst();

        System.out.println("Operation           : +is +be -f +or not+ -l to+ be+ -f");
        System.out.println("Deque size          : " + deque.size());
        System.out.println("Remaining elements  : ");

        for (String item : deque) {
            System.out.println(item);
        }
    }
}
