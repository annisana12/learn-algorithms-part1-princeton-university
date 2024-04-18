package application;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import fundamental.UF;

import java.util.Arrays;
import java.util.Comparator;
import java.util.OptionalInt;

/******************************************************************************
 *  Original Author: Robert Sedgewick and Kevin Wayne
 *
 *  Repeatedly add random edges (with replacement) to a graph on n
 *  vertices until the graph is connected. Report the mean and
 *  standard deviation of the number of edges added.
 *
 *  When n is large, Erdos and Renyi proved that after about 1/2 n ln n
 *  additions, the graph will have a 50/50 chance of being connected.
 *
 *
 *         Computational Experiments
 *         --------------------------
 *
 *       n    mean # edges    1/2 n ln n
 *  ------------------------------------
 *     100            260            230
 *     200            600            530
 *     400           1300           1200
 *     800           2900           2700
 *    1600           6400           5900
 *    3200          14000          13000
 *    6400          30000          28000
 *   12800          64000          61000
 *   25600         140000         130000
 *   51200         290000         280000
 *  102400         620000         590000
 *  204800        1300000        1300000
 *  409600        2700000        2700000
 *
 ******************************************************************************/

public class ErdosRenyi {
    public static int count(int n) {
        int edges = 0; // number of random edges (with replacement) needed for an n-vertex graph to become connected
        UF uf = new UF(n);

        while (uf.count() > 1) {
            int p = StdRandom.uniformInt(n);
            int q = StdRandom.uniformInt(n);

            if (uf.find(p) != uf.find(q)) {
                uf.union(p, q);
            }

            edges++;
        }

        return edges;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);          // Number of vertices
        int trials = Integer.parseInt(args[1]);     // Number of trials
        int[] edges = new int[trials];              // Keep track the number of edges for every trial

        for (int i = 0; i < trials; i++) {
            edges[i] = count(n);
        }

        // Report statistics
        System.out.println("1/2 n ln n          = " + 0.5 * n * Math.log(n));
        System.out.println("mean (StdStats)     = " + StdStats.mean(edges));
        System.out.println("stddev (StdStats)   = " + StdStats.stddev(edges));

        /**
         * Note
         * For large n, in example 2222749, the mean is negative.
         * This is because the sum is exceeded max int value allowed
         * and in the StdStats package, the sum data type is int.
         */

        System.out.println("mean (manual)       = " + calculateMean(edges));
        System.out.println("sttdev (manual)     = " + calculateStddev(edges));
    }

    private static double calculateMean(int[] array) {
        long sum = Arrays.stream(array).asLongStream().sum();
        return (double) sum / array.length;
    }

    private static double calculateStddev(int[] array) {
        double mean = calculateMean(array);

        double sumOfSquaredDiff = Arrays.stream(array).asDoubleStream()
                .map(val -> Math.pow(val - mean, 2))
                .sum();

        return Math.sqrt(sumOfSquaredDiff / array.length);
    }
}
