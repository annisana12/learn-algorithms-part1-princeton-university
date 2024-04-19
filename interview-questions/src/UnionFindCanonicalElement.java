import edu.princeton.cs.algs4.StdIn;

/**********************************************************
 * Question:
 * Add a method find() to the union-find data type so that
 * find(i) returns the largest element in the connected
 * component containing i.
 * The operation union(), connected(), and find() should
 * all take logarithmic time or better.
 *
 * For example:
 * If one of the connected components is {1, 2, 6, 9},
 * then the find() method should return 9 for each of the
 * four elements in the connected components
***********************************************************/

public class UnionFindCanonicalElement {
    private int[] parent;
    private int[] maxElements; // maxElements[i] = maximum elements rooted at i
    private int[] height; // height[i] = height of subtree rooted at i
    private int count;

    public UnionFindCanonicalElement(int n) {
        count = n;
        parent = new int[n];
        maxElements = new int[n];
        height = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            maxElements[i] = i;
            height[i] = 0;
        }
    }

    private void validate(int p) {
        int n = parent.length;
        if (p < -1 || p >= n) throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n - 1));
    }

    public int root(int p) {
        validate(p);
        while (p != parent[p]) p = parent[p];
        return p;
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public void union(int p, int q) {
        int rootP = root(p);
        int rootQ = root(q);
        int maxRootP = maxElements[rootP];
        int maxRootQ = maxElements[rootQ];
        int newMax = Math.max(maxRootP, maxRootQ);

        if (height[rootP] < height[rootQ]) {
            parent[rootP] = rootQ;
            maxElements[rootQ] = newMax;
        } else if (height[rootP] > height[rootQ]) {
            parent[rootQ] = rootP;
            maxElements[rootP] = newMax;
        } else {
            parent[rootQ] = rootP;
            height[rootP]++;
            maxElements[rootP] = newMax;
        }

        count--;
    }

    public int find(int p) {
        validate(p);
        int rootP = root(p);
        return maxElements[rootP];
    }

    public int count() {
        return count;
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        int componentTobeFind = StdIn.readInt();
        UnionFindCanonicalElement uf = new UnionFindCanonicalElement(n);

        // Create connection
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();

            if (uf.connected(p, q)) continue;

            uf.union(p, q);
            System.out.println(p + " " + q);
        }

        System.out.println("Max component in set containing " + componentTobeFind + " : " + uf.find(componentTobeFind));
    }
}
