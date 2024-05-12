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
    private static int maxNumberIndex(int[] arr, int maxIndex) {
        int lo = 0;
        int hi = maxIndex;

        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            int midValue = arr[mid];
            int leftValue = mid > 0 ? arr[mid - 1] : arr[0];
            int rightValue = mid < maxIndex ? arr[mid + 1] : arr[maxIndex];

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

            if (midValue == key) return mid;
            else if (isIncreasing == (key > midValue)) lo = mid + 1;
            else hi = mid - 1;
        }

        return -1;
    }

    public static int standardVersion(int[] arr, int key) {
        int maxNumberIndex = maxNumberIndex(arr, arr.length - 1);

        if (maxNumberIndex == -1) return -1;
        if (arr[maxNumberIndex] == key) return maxNumberIndex;

        int searchLeft = binarySearch(arr, key, true, 0, maxNumberIndex);

        if (searchLeft != -1) return searchLeft;
        else return binarySearch(arr, key, false, maxNumberIndex, arr.length - 1);
    }

    public static int signingBonus(int[] arr, int key) {
        // Check increasing part
        int lo = 0;
        int hi = arr.length - 1;
        int initialMid = hi / 2;
        int searchLeft = -1;

        int startLeft = 0;
        int endLeft = initialMid;
        int maxLeftIndex = initialMid;

        while (lo <= hi) {
            // Determine max number index in left initialMid in maximum lg n steps
            int midLeft = (startLeft + endLeft) / 2;
            int leftMid = midLeft > 0 ? midLeft - 1 : 0;
            int rightMid = midLeft < initialMid ? midLeft + 1 : initialMid;

            if (arr[leftMid] > arr[midLeft]) {
                if (arr[leftMid] > arr[maxLeftIndex]) maxLeftIndex = leftMid;
                endLeft = midLeft - 1;
            } else if (arr[rightMid] > arr[midLeft]) {
                if (arr[rightMid] > arr[maxLeftIndex]) maxLeftIndex = rightMid;
                startLeft = midLeft + 1;
            } else {
                if (arr[midLeft] > arr[maxLeftIndex]) maxLeftIndex = midLeft;
            }

            // Perform binary search
            int mid = (lo + hi) / 2;
            int midValue = arr[mid];

            if (key > midValue) {
                lo = mid + 1;
            } else if (key < midValue) {
                hi = mid - 1;
            } else {
                searchLeft = mid;
                break;
            }
        }

        if (searchLeft != -1) return searchLeft;

        // Check decreasing part
        return binarySearch(arr, key, false, maxLeftIndex, arr.length - 1);
    }

    public static void runTest(int[] arr, int key) {
        System.out.println("Array                           : " + Arrays.toString(arr));
        System.out.println("Search                          : " + key);
        System.out.println("Result index (standard version) : " + standardVersion(arr, key));
        System.out.println("Result index (signing bonus)    : " + signingBonus(arr, key));
        System.out.println("================================================================");
    }

    public static void main(String[] args) {
        runTest(new int[]{1, 3, 5, 7, 14, 12, 8, 6, 4, 2}, 3);
        runTest(new int[]{1, 3, 5, 7, 14, 12, 8, 6, 4, 2}, 1);
        runTest(new int[]{1, 3, 5, 7, 14, 12, 8, 6, 4, 2}, 2);
        runTest(new int[]{1, 3, 5, 7, 14, 12, 8, 6, 4, 2}, 14);
        runTest(new int[]{1, 3, 5, 7, 14, 12, 8, 6, 4, 2}, 0);
        runTest(new int[]{1, 3, 5, 7, 9, 15, 18, 16, 12, 8, 2}, 16);
        runTest(new int[]{1, 3, 5, 15, 14, 12, 8, 6, 4}, 12);
        runTest(new int[]{3, 5, 7, 15, 12, 8, 6, 4, 2}, 15);
        runTest(new int[]{3, 25, 23, 21, 19, 17, 15, 13, 12, 11, 10, 9, 8, 7, 6, 4, 2}, 15);
        runTest(new int[]{1, 3, 5, 7}, 3);
        runTest(new int[]{12, 8, 6, 4, 2}, 4);
    }
}
