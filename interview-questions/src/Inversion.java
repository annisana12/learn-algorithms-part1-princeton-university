import java.util.Arrays;

/**
 * Question :
 * An inversion in an array a[] is a pair of entries a [i] and a[j]
 * such that i < j but a[i] > a[j]. Given an array, design a
 * linearithmic algorithm to count the number of inversions.
 */

public class Inversion {
    private Inversion() {
    }

    private static <T extends Comparable<T>> boolean less(T a, T b) {
        return a.compareTo(b) < 0;
    }

    private static <T extends Comparable<T>> long mergeAndCount(T[] a, T[] aux, int lo, int mid, int hi) {
        long count = 0;

        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        int i = lo;
        int j = mid + 1;

        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (less(aux[j], aux[i])) {
                a[k] = aux[j++];

                // aux[j] < aux[i] and aux[i] < aux[mid] then aux[j] < aux[mid]
                count += (mid + 1 - i);
            } else {
                a[k] = aux[i++];
            }
        }

        return count;
    }

    private static <T extends Comparable<T>> long countInversionRecursive(T[] a, T[] aux, int lo, int hi) {
        if (hi <= lo) return 0;

        long count = 0;
        int mid = lo + (hi - lo) / 2;

        count += countInversionRecursive(a, aux, lo, mid);
        count += countInversionRecursive(a, aux, mid + 1, hi);
        count += mergeAndCount(a, aux, lo, mid, hi);

        return count;
    }

    public static <T extends Comparable<T>> long countInversion(T[] a) {
        if (a == null || a.length <= 1) return 0;

        T[] aux = (T[]) new Comparable[a.length];

        return countInversionRecursive(a, aux, 0, a.length - 1);
    }

    public static void main(String[] args) {
        String[] array1 = {"S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};

        System.out.println("Original array : " + Arrays.toString(array1));
        System.out.println("Number of inversions : " + countInversion(array1));

        String[] array2 = {"E", "E", "G", "M", "O", "R", "R", "S", "A", "E", "E", "L", "M", "P", "T", "X"};

        System.out.println("Original array : " + Arrays.toString(array2));
        System.out.println("Number of inversions : " + countInversion(array2));

        String[] array3 = {"A"};

        System.out.println("Original array : " + Arrays.toString(array3));
        System.out.println("Number of inversions : " + countInversion(array3));

        String[] array4 = {"G", "A"};

        System.out.println("Original array : " + Arrays.toString(array4));
        System.out.println("Number of inversions : " + countInversion(array4));

        String[] array5 = {"A", "B"};

        System.out.println("Original array : " + Arrays.toString(array5));
        System.out.println("Number of inversions : " + countInversion(array5));

        String[] array6 = {"D", "C", "B", "A"};

        System.out.println("Original array : " + Arrays.toString(array6));
        System.out.println("Number of inversions : " + countInversion(array6));
    }
}
