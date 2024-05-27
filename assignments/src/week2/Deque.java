package week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item>{
    private Node<Item> first; // Item at front
    private Node<Item> last; // Item at back
    private int n; // Deque size;

    private static class Node<Item> {
        private Item item;

        private Node<Item> prev;
        private Node<Item> next;
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

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("item must be not null");
        }

        Node<Item> oldFirst = first;
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

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("item must be not null");
        }

        Node<Item> oldLast = last;
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

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }

        Item item = first.item;
        first = first.next;
        n--;

        if (isEmpty()) {
            last = null;
        } else {
            first.prev = null;
        }

        return item;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }

        Item item = last.item;
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
    public Iterator<Item> iterator() {
        return new LinkedListIterator(first);
    }

    private class LinkedListIterator implements Iterator<Item> {
        private Node<Item> current;

        public LinkedListIterator(Node<Item> first) {
            current = first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Item item = current.item;
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
