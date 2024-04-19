import edu.princeton.cs.algs4.StdIn;

/**********************************************************
 * Question:
 * Given a set of n integers S = { 0, 1, ..., n-1 } and
 * a sequence of requests of the following form:
 * (1) Remove x from S
 * (2) Find the successor of x, the smallest y in S
 *     such that y >= x.
 *
 * Design a data type so that all operations
 * (except construction) take logarithmic time or better
 * in the worst case.
 *
 *
 * Data files for testing:   src/data/
 ***********************************************************/

public class SuccessorWithDelete {
    private int[] successors; // successors[i] = undeleted j where j >= i
    private int count; // Number of components that have not yet been deleted

    public SuccessorWithDelete(int n) {
        count = n;
        successors = new int[n];

        for (int i = 0; i < n; i++) {
            successors[i] = i;
        }
    }

    public int count() {
        return count;
    }

    private void validate(int p) {
        int n = successors.length;
        if (p < -1 || p >= n) throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n - 1));
    }

    public boolean isDeleted(int p) {
        validate(p);
        return p != successors[p];
    }

    public int find(int p) {
        validate(p);

        while (p != successors[p]) {
            if (successors[p] == -1) {
                p = successors[p];
                break;
            } else {
                successors[p] = successors[successors[p]]; // Path halving
                p = successors[p];

                if (p == -1) {
                    break;
                }
            }
        }

        return p;
    }

    public void delete(int p) {
        validate(p);

        if (p == successors[p]) {
            if (p == successors.length - 1) {
                successors[p] = -1;
            } else {
                int nextComponentSuccessor = find(p + 1);
                successors[p] = nextComponentSuccessor;
            }

            count--;
        }
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        SuccessorWithDelete dataType = new SuccessorWithDelete(n);

        System.out.println("Given " + n + " components (0 - " + (n-1) + ")");

        while (!StdIn.isEmpty()) {
            int dataTobeDeleted = StdIn.readInt();

            if (!dataType.isDeleted(dataTobeDeleted)) {
                dataType.delete(dataTobeDeleted);
                System.out.println(dataTobeDeleted + " is deleted");
            }

            int successor = dataType.find(dataTobeDeleted);
            System.out.println("Successor of " + dataTobeDeleted + " is " + successor);
        }

        System.out.println("Number of components have not been deleted : " + dataType.count());
    }
}
