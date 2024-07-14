import edu.princeton.cs.algs4.Shell;

import java.util.Objects;

/**
 * Question :
 * Given two integer arrays of size n, design a sub quadratic algorithm
 * to determine whether one is a permutation of the other. That is, do
 * they contain exactly the same entries but, possibly, in a different order.
 */

public class PermutationShellSort {
    public static boolean isPermutation(Integer[] a, Integer[] b) {
        if (a.length != b.length) return false;

        Shell.sort(a);
        Shell.sort(b);

        for (int i = 0; i < a.length; i++) {
            if (!Objects.equals(a[i], b[i])) return false;
        }

        return true;
    }

    public static void main(String[] args) {
        Integer[] a = {3, 1, 2, 5, 4};
        Integer[] b = {2, 5, 3, 1, 4};

        boolean isABPermutation = isPermutation(a, b);
        System.out.println("a and b is permutation ? " + isABPermutation);

        Integer[] c = {1, 2, 6, 4, 5};
        Integer[] d = {7, 5, 3, 1, 4};

        boolean isCDPermutation = isPermutation(c, d);
        System.out.println("c and d is permutation ? " + isCDPermutation);
    }
}
