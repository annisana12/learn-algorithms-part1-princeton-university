package fundamental;

import edu.princeton.cs.algs4.StdIn;
import java.util.Iterator;
import java.util.NoSuchElementException;

/******************************************************************************
 *  Original Author: Robert Sedgewick and Kevin Wayne
 *  Data: tobe.txt
 *  Compilation:  javac FixedCapacityStack.java
 *  Execution:  java FixedCapacityStack 5 < tobe.txt
 ******************************************************************************/

public class FixedCapacityStack<E> implements Iterable<E> {
    private E[] s;
    private int n; // number of items in stack

    public FixedCapacityStack(int capacity) {
        s = (E[]) new Object[capacity];
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

    public void push(E item) {
        if (isFull()) {
            throw new RuntimeException("Stack is full");
        }

        s[n++] = item;
    }

    public E pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }

        E oldItem = s[--n];
        s[n] = null;

        return oldItem;
    }

    public E peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }

        return s[n - 1];
    }

    @Override
    public Iterator<E> iterator() {
        return new ReverseArrayIterator();
    }

    public class ReverseArrayIterator implements Iterator<E> {
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
        int capacity = Integer.parseInt(args[0]);
        FixedCapacityStack<String> stack = new FixedCapacityStack<>(capacity);

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
