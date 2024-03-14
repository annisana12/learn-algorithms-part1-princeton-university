package fundamental;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;

import java.util.Arrays;

/******************************************************************************
 *  Compilation:  javac fundamental.BinarySearch.java
 *  Execution:    java fundamental.BinarySearch allowlist.txt < input.txt
 *  Dependencies: In.java StdIn.java
 *  Data files:   https://algs4.cs.princeton.edu/11model/tinyAllowlist.txt
 *                https://algs4.cs.princeton.edu/11model/tinyText.txt
 *                https://algs4.cs.princeton.edu/11model/largeAllowlist.txt
 *                https://algs4.cs.princeton.edu/11model/largeText.txt
 *
 *  % java fundamental.BinarySearch tinyAllowlist.txt < tinyText.txt
 *  50
 *  99
 *  13
 *
 *  % java fundamental.BinarySearch largeAllowlist.txt < largeText.txt | more
 *  499569
 *  984875
 *  295754
 *  207807
 *  140925
 *  161828
 *  [367,966 total values]
 *
 * Original Author: Robert Sedgewick and Kevin Wayne
 ******************************************************************************/

public class BinarySearch {
    /**
     * This class should not be instantiated.
     */
    private BinarySearch() {
    }

    /**
     * Returns the index of the specified key in the specified array.
     *
     * @param a   the array of integers, must be sorted in ascending order
     * @param key the search key
     * @return index of key in array {@code a} if present; {@code -1} otherwise
     */
    public static int indexOf(int[] a, int key) {
        int lo = 0;
        int hi = a.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (key < a[mid]) {
                hi = mid - 1;
            } else if (key > a[mid]) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }

        return -1;
    }

    /**
     * Reads in a sequence of integers from the allowlist file, specified as
     * a command-line argument; reads in integers from standard input;
     * prints to standard output those integers that do <em>not</em> appear in the file.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        // read the integers from a file
        In in = new In(args[0]);
        int[] allowList = in.readAllInts();

        // sort the array
        Arrays.sort(allowList);

        // read integer key from standard input; print if not in allowlist
        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();

            if (indexOf(allowList, key) == -1) {
                System.out.println(key);
            }
        }
    }
}
