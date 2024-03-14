package fundamental;

import edu.princeton.cs.algs4.StdIn;

/******************************************************************************
 *  Original Author: Robert Sedgewick and Kevin Wayne
 *  Compilation:  javac QuickFindUF.java
 *  Execution:  java QuickFindUF < input.txt
 *  Dependencies: StdIn.java StdOut.java
 *  Data files:   https://algs4.cs.princeton.edu/15uf/tinyUF.txt
 *                https://algs4.cs.princeton.edu/15uf/mediumUF.txt
 *                https://algs4.cs.princeton.edu/15uf/largeUF.txt
 *
 *  Quick-find algorithm.
 ******************************************************************************/

public class QuickFindUF {
    private int[] id; // id[i] = component identifier of i
    private int count; // number of connected components (sets)

    /**
     * Initializes an empty union-find data structure with
     * {@code n} elements {@code 0} through {@code n-1}.
     * Initially, each element is in its own set.
     *
     * @param n the number of elements
     */
    public QuickFindUF(int n) {
        count = n;
        id = new int[n];

        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    /**
     * Validate that {@code p} is a valid index
     *
     * @param p index
     */
    private void validate(int p) {
        int n = id.length;

        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n - 1));
        }
    }

    /**
     * Get component identifier for {@code p}
     *
     * @param p an element
     * @return component identifier (0 to n-1)
     */
    public int find(int p) {
        validate(p);
        return id[p];
    }

    /**
     * Check whether {@code p} and {@code q} are in the same set
     *
     * @param p an element
     * @param q the other element
     * @return true if {@code p} and {@code q} are in the same set
     */
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * Add connection between {@code p} and {@code q}
     * by merging the set containing element {@code p}
     * into the set containing element {@code q}
     * @param p an element
     * @param q the other element
     */
    public void union(int p, int q) {
        int pID = find(p);
        int qID = find(q);

        if (pID == qID) return;

        for (int i = 0; i < id.length; i++) {
            if (id[i] == pID) {
                id[i] = qID;
            }
        }

        count--;
    }

    /**
     * Get the number of connected components (sets)
     *
     * @return the number of sets (between {@code 1} and {@code n})
     */
    public int count() {
        return count;
    }

    /**
     * Reads an integer {@code n} and a sequence of pairs of integers
     * (between {@code 0} and {@code n-1}) from standard input, where each integer
     * in the pair represents some element;
     * if the elements are in different sets, merge the two sets
     * and print the pair to standard output.
     */
    public static void main(String[] args) {
        int n = StdIn.readInt();
        QuickFindUF uf = new QuickFindUF(n);

        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();

            if (uf.connected(p, q)) continue;

            uf.union(p, q);
            System.out.println(p + " " + q);
        }

        System.out.println("Number of connected components : " + uf.count());
    }
}
