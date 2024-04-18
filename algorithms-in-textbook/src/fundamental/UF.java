package fundamental;

import edu.princeton.cs.algs4.StdIn;

/******************************************************************************
 *  Original Author: Robert Sedgewick and Kevin Wayne
 *  Compilation:  javac UF.java
 *  Execution:    java  UF < input.txt
 *  Dependencies: StdIn.java
 *  Data files:   https://algs4.cs.princeton.edu/15uf/tinyUF.txt
 *                https://algs4.cs.princeton.edu/15uf/mediumUF.txt
 *                https://algs4.cs.princeton.edu/15uf/largeUF.txt
 *
 *  Weighted quick-union by rank with path compression by halving.
 *
 ******************************************************************************/

public class UF {
    private int[] parent; // parent[i] = parent of i
    private byte[] rank; // rank[i] = rank of subtree rooted at i (never more than 31)
    private int count; // number of connected components (sets)

    public UF(int n) {
        count = n;
        parent = new int[n];
        rank = new byte[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public int count() {
        return count;
    }

    private void validate(int p) {
        int n = parent.length;

        if (p < -1 || p >= n) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n - 1));
        }
    }

    public int find(int p) {
        validate(p);

        while (p != parent[p]) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }

        return p;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);

        if (rootP == rootQ) return;

        if (rank[rootP] < rank[rootQ]) {
            parent[rootP] = rootQ;
        } else if (rank[rootP] > rank[rootQ]) {
            parent[rootQ] = rootP;
        } else {
            parent[rootQ] = rootP;
            rank[rootP]++;
        }

        count--;
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
        UF uf = new UF(n);

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
