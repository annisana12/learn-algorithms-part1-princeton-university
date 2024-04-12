package fundamental;

import edu.princeton.cs.algs4.StdIn;

/******************************************************************************
 *  Original Author: Robert Sedgewick and Kevin Wayne
 *  Compilation:  javac QuickUnionPathHalvingUF.java
 *  Execution:  java QuickUnionPathHalvingUF < input.txt
 *  Dependencies: StdIn.java
 *  Data files:   https://algs4.cs.princeton.edu/15uf/tinyUF.txt
 *                https://algs4.cs.princeton.edu/15uf/mediumUF.txt
 *                https://algs4.cs.princeton.edu/15uf/largeUF.txt
 *
 *  Quick-union with path halving algorithm.
 ******************************************************************************/

public class QuickUnionPathHalvingUF {
    private int[] parent; // parent[i] = parent of i
    private int count; // number of connected components (sets)

    /**
     * Initializes an empty union-find data structure with
     * {@code n} elements {@code 0} through {@code n-1}.
     * Initially, each element is in its own set.
     *
     * @param  n the number of elements
     */
    public QuickUnionPathHalvingUF(int n) {
        count = n;
        parent = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    /**
     * Validate that {@code p} is a valid index
     *
     * @param p index
     */
    private void validate(int p) {
        int n = parent.length;

        if (p < -1 || p >= n) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n - 1));
        }
    }

    /**
     * Find the root of element {@code p}.
     *
     * @param  p an element
     * @return the root of element {@code p}
     */
    public int find(int p) {
        validate(p);

        while (p != parent[p]) {
            parent[p] = parent[parent[p]]; // Path compression by halving
            p = parent[p];
        }

        return p;
    }

    /**
     * Check whether {@code p} and {@code q} are in the same set
     *
     * @param  p one element
     * @param  q the other element
     * @return {@code true} if {@code p} and {@code q} have the same root
     */
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * Merges the set containing element {@code p}
     * with the set containing element {@code q}
     * by changing the parent of root {@code p}
     * with the root of {@code q}
     *
     * @param  p one element
     * @param  q the other element
     */
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);

        if (rootP == rootQ) return;

        parent[rootP] = rootQ;
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
        QuickUnionPathHalvingUF uf = new QuickUnionPathHalvingUF(n);

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
