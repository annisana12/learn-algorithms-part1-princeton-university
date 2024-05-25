import edu.princeton.cs.algs4.Stack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Question :
 * Create a data structure that efficiently supports
 * the stack operations (push and pop) and also return
 * the maximum operation. Assume the elements are real
 * numbers so that you can compare them.
 */

public class StackWithMax implements Iterable<Double> {
    private Stack<Double> stack;
    private Stack<Double> maxStack;

    public StackWithMax() {
        stack = new Stack<>();
        maxStack = new Stack<>();
    }

    public int size() {
        return stack.size();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public void push(Double item) {
        stack.push(item);

        if (maxStack.isEmpty() || (!maxStack.isEmpty() && item >= maxStack.peek())) {
            maxStack.push(item);
        }
    }

    public Double pop() {
        Double item = stack.pop();

        if (!isEmpty() && item.equals(maxStack.peek())) {
            maxStack.pop();
        }

        return item;
    }

    public Double peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }

        return stack.peek();
    }

    public Double max() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }

        return maxStack.peek();
    }

    @Override
    public Iterator<Double> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Double> {
        private List<Double> arr;
        private int i;

        public ArrayIterator() {
            i = 0;
            arr = new ArrayList<>();

            for (Double item : stack) {
                arr.add(item);
            }
        }

        @Override
        public boolean hasNext() {
            return i < arr.size();
        }

        @Override
        public Double next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return arr.get(i++);
        }
    }

    private static void runTest(StackWithMax stack, String operation, Double item) {
        if (operation.equals("push")) {
            stack.push(item);
            System.out.println("Push item   : " + item);
        } else {
            Double removedItem = stack.pop();
            System.out.println("Pop item    : " + removedItem);
        }

        System.out.println("Peek        : " + stack.peek());
        System.out.println("Max item    : " + stack.max());
    }

    private static void runTest(StackWithMax stack) {
        runTest(stack, "pop", null);
    }

    public static void main(String[] args) {
        StackWithMax stack = new StackWithMax();

        runTest(stack, "push", 10.5);
        runTest(stack, "push", 5.8);
        runTest(stack, "push", 12.7);
        runTest(stack, "push", 12.7);
        runTest(stack, "push", 10.4);
        runTest(stack);
        runTest(stack);
        runTest(stack);

        System.out.println();
        System.out.println(stack.size() + " remaining items :");

        for (Double item : stack) {
            System.out.println(item);
        }
    }
}