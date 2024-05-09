package week1;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF ufTopBottomVirtual;
    private WeightedQuickUnionUF ufTopVirtual; // union-find object to prevent backwash
    private boolean[] sitesOpenStatus;
    private int count; // number of open sites
    private final int SIZE, TOP_INDEX, BOTTOM_INDEX;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be greater than 0");
        }

        int numberOfSites = n * n;
        count = 0;
        SIZE = n;
        TOP_INDEX = numberOfSites;
        BOTTOM_INDEX = numberOfSites + 1;
        ufTopBottomVirtual = new WeightedQuickUnionUF(numberOfSites + 2);
        ufTopVirtual = new WeightedQuickUnionUF(numberOfSites + 1);
        sitesOpenStatus = new boolean[numberOfSites];
    }

    private int getSiteIndex(int row, int col) {
        if (row < 1 || row > SIZE || col < 1 || col > SIZE) {
            throw new IllegalArgumentException("Row and column indices must be between 1 and " + SIZE);
        }

        return (row - 1) * SIZE + col - 1;
    }

    public void open(int row, int col) {
        int siteIndex = getSiteIndex(row, col);

        if (sitesOpenStatus[siteIndex]) {
            return;
        }

        // Mark this site as open
        sitesOpenStatus[siteIndex] = true;
        count++;

        // Define neighbor index
        int leftNeighbor = col == 1 ? -1 : siteIndex - 1;
        int rightNeighbor = col == SIZE ? -1 : siteIndex + 1;
        int topNeighbor = row == 1 ? -1 : siteIndex - SIZE;
        int bottomNeighbor = row == SIZE ? -1 : siteIndex + SIZE;

        // Connect this site to its neighbor
        connectNeighbor(siteIndex, leftNeighbor);
        connectNeighbor(siteIndex, rightNeighbor);
        connectNeighbor(siteIndex, topNeighbor);
        connectNeighbor(siteIndex, bottomNeighbor);

        // Connect site to virtual top site
        if (row == 1) {
            ufTopBottomVirtual.union(siteIndex, TOP_INDEX);
            ufTopVirtual.union(siteIndex, TOP_INDEX);
        }

        // Connect site to virtual bottom site
        if (row == SIZE) {
            ufTopBottomVirtual.union(siteIndex, BOTTOM_INDEX);
        }
    }

    private void connectNeighbor(int site, int neighbor) {
        if (neighbor != -1 && sitesOpenStatus[neighbor]) {
            ufTopBottomVirtual.union(site, neighbor);
            ufTopVirtual.union(site, neighbor);
        }
    }

    public boolean isOpen(int row, int col) {
        int siteIndex = getSiteIndex(row, col);
        return sitesOpenStatus[siteIndex];
    }

    public boolean isFull(int row, int col) {
        if (isOpen(row, col)) {
            int siteIndex = getSiteIndex(row, col);
            return ufTopVirtual.find(TOP_INDEX) == ufTopVirtual.find(siteIndex);
        }

        return false;
    }

    public int numberOfOpenSites() {
        return count;
    }

    public boolean percolates() {
        return ufTopBottomVirtual.find(BOTTOM_INDEX) == ufTopBottomVirtual.find(TOP_INDEX);
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        Percolation percolation = new Percolation(n);

        while (!percolation.percolates()) {
            int row = StdRandom.uniformInt(n) + 1;
            int col = StdRandom.uniformInt(n) + 1;

            System.out.println("Site (" + row + ", " + col + ")");
            System.out.println("isOpen (before)         : " + percolation.isOpen(row, col));

            percolation.open(row, col);

            System.out.println("isOpen (after)          : " + percolation.isOpen(row, col));
            System.out.println("isFull                  : " + percolation.isFull(row, col));
            System.out.println("number of open sites    : " + percolation.numberOfOpenSites());
            System.out.println("isPercolates            : " + percolation.percolates());
            System.out.println("=========================================");
        }
    }
}
