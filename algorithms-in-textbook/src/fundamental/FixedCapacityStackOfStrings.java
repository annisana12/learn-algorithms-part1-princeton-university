package fundamental;

import edu.princeton.cs.algs4.StdIn;

import java.util.Iterator;
import java.util.NoSuchElementException;

/******************************************************************************
 *  Original Author: Robert Sedgewick and Kevin Wayne
 *  Data: tobe.txt
 *  Compilation:  javac FixedCapacityStackOfStrings.java
 *  Execution:  java FixedCapacityStackOfStrings 5 < tobe.txt
 ******************************************************************************/

public class FixedCapacityStackOfStrings implements Iterable<String> {
    private String[] s;
    private int n; // number of items in stack

    public FixedCapacityStackOfStrings(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity must be greater than 0");
        }

        s = new String[capacity];
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

    public void push(String item) {
        if (isFull()) {
            throw new RuntimeException("Stack is full");
        }

        s[n++] = item;
    }

    public String pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }

        String oldItem = s[--n];
        s[n] = null;

        return oldItem;
    }

    /**
     * Looks at the object at the top of this stack
     * without removing it from the stack.
     *
     * @return the last item in stack
     */
    public String peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }

        return s[n - 1];
    }

    @Override
    public Iterator<String> iterator() {
        return new ReverseArrayIterator();
    }

    public class ReverseArrayIterator implements Iterator<String> {
        private int i = n;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public String next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return s[--i];
        }
    }

    public static void main(String[] args) {
        int capacity = Integer.parseInt(args[0]);
        FixedCapacityStackOfStrings stack = new FixedCapacityStackOfStrings(capacity);

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
