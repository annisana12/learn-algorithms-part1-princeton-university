package week1;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] openSitePercentages;
    private final int trials;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("n and trials must be greater than 0");
        }

        this.trials = trials;
        openSitePercentages = new double[trials];

        // Perform percolation by opening random sites until system percolates
        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);

            while (!percolation.percolates()) {
                int site = StdRandom.uniformInt(n * n);
                int row = site / n + 1;
                int col = site % n + 1;

                percolation.open(row, col);
            }

            openSitePercentages[i] = (double) percolation.numberOfOpenSites() / (n * n);
        }
    }

    public double mean() {
        return StdStats.mean(openSitePercentages);
    }

    public double stddev() {
        return StdStats.stddev(openSitePercentages);
    }

    public double confidenceLo() {
        double mean = mean();
        double stddev = stddev();

        return mean - 1.96 * stddev / Math.sqrt(trials);
    }

    public double confidenceHi() {
        double mean = mean();
        double stddev = stddev();

        return mean + 1.96 * stddev / Math.sqrt(trials);
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);

        PercolationStats stats = new PercolationStats(n, t);

        System.out.println("mean                    = " + stats.mean());
        System.out.println("stddev                  = " + stats.stddev());
        System.out.println("95% confidence interval = [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
    }
}
