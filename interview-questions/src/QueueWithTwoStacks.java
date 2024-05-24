import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Question :
 * Implement a queue with two stacks so that each
 * queue operations takes a constant amortized
 * number of stack operations.
 * <p>
 * Data : tobe.txt
 */

public class QueueWithTwoStacks<E> implements Iterable<E> {
    private Stack<E> firstStack; // Least recently added item at bottom
    private Stack<E> secondStack; // Least recently added item at top

    public QueueWithTwoStacks() {
        firstStack = new Stack<>();
        secondStack = new Stack<>();
    }

    public int size() {
        return firstStack.size() + secondStack.size();
    }

    public boolean isEmpty() {
        return firstStack.isEmpty() && secondStack.isEmpty();
    }

    public void enqueue(E item) {
        firstStack.push(item);
    }

    private void fillSecondStack() {
        if (secondStack.isEmpty()) {
            while (!firstStack.isEmpty()) {
                secondStack.push(firstStack.pop());
            }
        }
    }

    public E dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }

        fillSecondStack();
        return secondStack.pop();
    }

    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }

        fillSecondStack();
        return secondStack.peek();
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<E> {
        private final E[] arr;
        private int i;

        public ArrayIterator() {
            int n = size();
            arr = (E[]) new Object[n];
            i = 0;

            int firstIdx = 0;

            for (E secondItem : secondStack) {
                arr[firstIdx++] = secondItem;
            }

            int secondIdx = n;

            for (E firstItem : firstStack) {
                arr[--secondIdx] = firstItem;
            }
        }

        @Override
        public boolean hasNext() {
            return i < arr.length;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return arr[i++];
        }
    }

    public static void main(String[] args) {
        QueueWithTwoStacks<String> queue = new QueueWithTwoStacks<>();

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
        System.out.print(queue.size() + " items left in queue :");

        for (String item : queue) {
            System.out.print(item);
            System.out.print(' ');
        }

        System.out.println();
        System.out.println("Item left at front : " + queue.peek());
    }
}
