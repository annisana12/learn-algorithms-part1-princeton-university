import java.util.Arrays;

/**
 * Question :
 * Design an algorithm for the 3-SUM problem that takes
 * time proportional to n^2 in the worst case. You may
 * assume that you can sort n integers in time
 * proportional to n^2 or better.
 */

public class ThreeSum {
    public static void printThreeSum(int[] arr) {
        if (arr.length < 3) {
            return;
        }

        int count = 0;
        Arrays.sort(arr);

        for (int i = 0; i < arr.length - 2; i++) {
            int a = arr[i];
            int start = i + 1;
            int end = arr.length - 1;

            while (start < end) {
                int b = arr[start];
                int c = arr[end];

                if (a + b + c == 0) {
                    System.out.println(a + " " + b + " " + c);
                    count++;
                    start++;
                    end--;
                } else if (a + b + c > 0) {
                    end--;
                } else {
                    start++;
                }
            }
        }

        System.out.println("Number of triple sums that result in zero = " + count);
    }

    public static void runTest(int[] arr) {
        System.out.println("Array : " + Arrays.toString(arr));
        printThreeSum(arr);
        System.out.println("================================");
    }

    public static void main(String[] args) {
        runTest(new int[]{-2, 5, -3, 7, -5, 2, 3, 0});
        runTest(new int[]{0});
        runTest(new int[]{-2, -3, -1});
        runTest(new int[]{5, 2, 3});
        runTest(new int[]{5, -1, -1, 2, 3});
    }
}
