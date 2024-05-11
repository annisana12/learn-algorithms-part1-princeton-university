import java.util.Arrays;

/**
 * An array is bitonic if it comprises an increasing
 * sequence of integers followed immediately by a decreasing
 * sequence of integers. Write program that, given a bitonic
 * array of n distinct integer values, determines whether
 * a given integer is in the array.
 * <p>
 * Standard version: Use ~ 3 lg n compares in the worst case
 * Signing bonus: Use ~ 2 lg n compares in the worst case
 * (and prove that no algorithm can guarantee
 * to perform fewer than ~ 2 lg n compares in
 * the worst case)
 */

public class BitonicArraySearch {
    private static int maxNumberIndex(int[] arr) {
        int lo = 0;
        int hi = arr.length - 1;

        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            int midValue = arr[mid];
            int leftValue = mid > 0 ? arr[mid - 1] : arr[0];
            int rightValue = mid < arr.length - 1 ? arr[mid + 1] : arr[arr.length - 1];

            if (leftValue > midValue) hi = mid - 1;
            else if (rightValue > midValue) lo = mid + 1;
            else return mid;
        }

        return -1;
    }

    private static int binarySearch(int[] arr, int key, boolean isIncreasing, int lo, int hi) {
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            int midValue = arr[mid];

            if (key < midValue) {
                if (isIncreasing) hi = mid - 1;
                else lo = mid + 1;
            } else if (key > midValue) {
                if (isIncreasing) lo = mid + 1;
                else hi = mid - 1;
            } else {
                return mid;
            }
        }

        return -1;
    }

    public static int standardVersion(int[] arr, int key) {
        int maxNumberIndex = maxNumberIndex(arr);
        if (maxNumberIndex == -1) return -1;

        int searchLeft = binarySearch(arr, key, true, 0, maxNumberIndex);

        if (searchLeft != -1) return searchLeft;
        else return binarySearch(arr, key, false, maxNumberIndex, arr.length - 1);
    }

    public static void runTest(int[] arr, int key) {
        System.out.println("Array           : " + Arrays.toString(arr));
        System.out.println("Search          : " + key);
        System.out.println("Result index    : " + standardVersion(arr, key));
        System.out.println("=================================================");
    }

    public static void main(String[] args) {
        runTest(new int[]{1, 3, 5, 7, 14, 12, 8, 6, 4, 2}, 3);
        runTest(new int[]{1, 3, 5, 7, 14, 12, 8, 6, 4, 2}, 1);
        runTest(new int[]{1, 3, 5, 7, 14, 12, 8, 6, 4, 2}, 2);
        runTest(new int[]{1, 3, 5, 7, 14, 12, 8, 6, 4, 2}, 14);
        runTest(new int[]{1, 3, 5, 7, 14, 12, 8, 6, 4, 2}, 0);
        runTest(new int[]{1, 3, 5, 7}, 3);
        runTest(new int[]{12, 8, 6, 4, 2}, 4);
    }
}
