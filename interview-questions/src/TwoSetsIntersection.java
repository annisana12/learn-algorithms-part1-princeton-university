import edu.princeton.cs.algs4.Shell;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

/**
 * Question :
 * Given two arrays a[] and b[], each containing n distinct 2D points in the plane.
 * Design a sub quadratic algorithm to count the number of points that are contained
 * both in array a[] and array b[].
 */

public class TwoSetsIntersection {
    private static class Point implements Comparable<Point> {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point that) {
            if (this.x < that.x) return -1;
            if (this.x > that.x) return 1;
            if (this.y < that.y) return -1;
            if (this.y > that.y) return 1;
            return 0;
        }

        @Override
        public String toString() {
            return "(" + this.x + "," + this.y + ")";
        }
    }

    public static int countIntersection(Point[] a, Point[] b) {
        Shell.sort(a);
        Shell.sort(b);

        int count = 0;
        int i = 0;
        int j = 0;

        while (i < a.length && j < b.length) {
            if (a[i].compareTo(b[j]) == 0) {
                count++;
                i++;
                j++;
            } else if (a[i].compareTo(b[j]) < 0) {
                i++;
            } else {
                j++;
            }
        }

        return count;
    }
    public static void main(String[] args) {
        Point[] a = {
                new Point(-1, 2),
                new Point(-3, 1),
                new Point(6, 5),
                new Point(3, -4),
                new Point(2, 0),
                new Point(5, -2),
                new Point(-5, 10),
                new Point(8, 1),
                new Point(6, 3),
                new Point(9, 4)
        };

        Point[] b = {
                new Point(1, 2),
                new Point(-3, 9),
                new Point(6, 5),
                new Point(2, -4),
                new Point(2, 0),
                new Point(-5, -2),
                new Point(8, 10),
                new Point(9, 1),
                new Point(6, -4),
                new Point(9, 4)
        };

        int numberOfIntersection = countIntersection(a, b);

        System.out.println("Sorted a[] : " + Arrays.toString(a));
        System.out.println("Sorted b[] : " + Arrays.toString(b));
        System.out.println("Number of points that are contained in both array : " + numberOfIntersection);
    }
}
