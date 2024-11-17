import java.util.Arrays;

/**
 * Question :
 * Suppose that the subarray a[0] and a[n-1] is sorted and
 * the subarray a[n] to a[2*n - 1] is sorted. How can you merge
 * the two subarrays so that a[0] to a[2*n - 1] is sorted using
 * an auxiliary array of length n (instead of 2n)
 * <p>
 * Note :
 * Solution for ascending order
 */

public class MergeSmallerAuxiliaryArray {
    private static <T extends Comparable<T>> boolean less(T a, T b) {
        return a.compareTo(b) < 0;
    }

    private static <T extends Comparable<T>> boolean isSorted(T[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            if (less(a[i], a[i - 1])) return false;
        }

        return true;
    }

    public static <T extends Comparable<T>> void merge(T[] a, T[] aux, int lo, int mid, int hi) {
        // Copy only the left half to the auxiliary array
        for (int k = lo; k <= mid; k++) {
            aux[k] = a[k];
        }

        int i = lo;
        int j = mid + 1;

        for (int k = lo; k <= hi; k++) {
            if (i > mid) break; // No need to copy, elements are already in place
            else if (j > hi) a[k] = aux[i++];
            else if (less(a[j], aux[i])) a[k] = a[j++];
            else a[k] = aux[i++];
        }

        assert isSorted(a, lo, hi);
    }

    public static void main(String[] args) {
        String[] a = {"E", "E", "G", "M", "O", "R", "R", "S", "A", "E", "E", "L", "M", "P", "T", "X"};

        int n = a.length;
        int lo = 0;
        int hi = n - 1;
        int mid = lo + (hi - lo) / 2;

        String[] aux = new String[n];

        System.out.println("Original array : " + Arrays.toString(a));
        merge(a, aux, lo, mid, hi);
        System.out.println("Merged array : " + Arrays.toString(a));
    }
}
