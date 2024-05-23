package fundamental;

import edu.princeton.cs.algs4.StdIn;

import java.util.Iterator;
import java.util.NoSuchElementException;

/******************************************************************************
 *  Original Author: Robert Sedgewick and Kevin Wayne
 *  Data: tobe.txt
 ******************************************************************************/

public class Stack<E> implements Iterable<E> {
    private Node<E> first; // top item in stack
    private int n; // stack size

    private static class Node<E> {
        E item;
        Node<E> next;
    }

    public Stack() {
        first = null;
        n = 0;
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void push(E item) {
        Node<E> oldFirst = first;
        first = new Node<>();
        first.item = item;
        first.next = oldFirst;
        n++;
    }

    public E pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }

        E item = first.item;
        first = first.next;
        n--;

        return item;
    }

    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
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
        Stack<String> stack = new Stack<>();

        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();

            if (item.equals("-")) {
                String removedItem = stack.pop();
                System.out.print(removedItem + " ");
            } else {
                stack.push(item);
            }
        }

        System.out.println();
        System.out.println(stack.size() + " items left in stack :");
        System.out.println(stack);
    }
}
