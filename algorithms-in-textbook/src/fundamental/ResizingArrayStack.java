package fundamental;

import edu.princeton.cs.algs4.StdIn;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/******************************************************************************
 *  Original Author: Robert Sedgewick and Kevin Wayne
 *  Data: tobe_more.txt
 ******************************************************************************/

public class ResizingArrayStack<E> implements Iterable<E> {
    private E[] s;
    private int n;
    private static final int INIT_CAPACITY = 10;

    public ResizingArrayStack() {
        s = (E[]) new Object[INIT_CAPACITY];
        n = 0;
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public boolean isFull() {
        return n == s.length;
    }

    private void resize(int capacity) {
        assert capacity >= n;

        s = Arrays.copyOf(s, capacity);
    }

    public void push(E item) {
        if (isFull()) {
            resize(2 * s.length);
        }

        s[n++] = item;
    }

    public E pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }

        E oldItem = s[--n];
        s[n] = null;

        if (n > 0 && n == s.length / 4) {
            resize(s.length / 2);
        }

        return oldItem;
    }

    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }

        return s[n - 1];
    }


    @Override
    public Iterator<E> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<E> {
        private int i = n;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return s[--i];
        }
    }

    public static void main(String[] args) {
        ResizingArrayStack<String> stack = new ResizingArrayStack<>();

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
        System.out.println("Items left in stack :");

        for (String s : stack) {
            System.out.print(s + " ");
        }
    }
}
