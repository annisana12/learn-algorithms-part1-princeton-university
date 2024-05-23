package fundamental;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizingArrayBag<E> implements Iterable<E> {
    private static final int INIT_CAPACITY = 10;
    private E[] arr;
    private int n;

    public ResizingArrayBag() {
        n = 0;
        arr = (E[]) new Object[INIT_CAPACITY];
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    private void resize(int capacity) {
        assert capacity >= n;
        arr = Arrays.copyOf(arr, capacity);
    }

    public void add(E item) {
        if (n == arr.length) {
            resize(arr.length * 2);
        }

        arr[n++] = item;
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<E> {
        private int i = 0;

        @Override
        public boolean hasNext() {
            return i < n;
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
        ResizingArrayBag<String> bag = new ResizingArrayBag<>();

        bag.add("Hello");
        bag.add("World");
        bag.add("How");
        bag.add("Are");
        bag.add("You");

        System.out.println("Bag size : " + bag.size());

        for (String item : bag) {
            System.out.println(item);
        }
    }
}
