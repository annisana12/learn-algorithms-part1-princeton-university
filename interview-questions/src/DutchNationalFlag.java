import java.util.Arrays;

/**
 * Question :
 * Given an array of n buckets, each containing a red, white, and blue
 * pebble, sort them by color. The allowed operations are:
 * (1) swap(i, j): swap the pebble in bucket i with the pebble in bucket j
 * (j) color(i): determine the color of the pebble in bucket i
 * <p>
 * The performance requirements are as follows:
 * (1) At most n calls to color()
 * (2) At most n calls to swap()
 * (3) Constant extra space
 * <p>
 * -------------------------------------------------------------------
 * Solution :
 * In this case we will use 0, 1, 2 in place of red, white, and blue.
 * Divide the array into 3 parts using 3 pointers (lo, mid, hi):
 * (1) zeros : arr[0] to arr[lo - 1]
 * (2) ones : arr[lo] to arr[mid - 1]
 * (3) twos : arr[hi + 1] to arr[N - 1]
 * <p>
 * Then traverse the array until mid <= hi.
 */

public class DutchNationalFlag {
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void sort(int[] arr) {
        int n = arr.length;
        int lo = 0;
        int mid = 0;
        int hi = n - 1;

        while (mid <= hi) {
            switch (arr[mid]) {
                case 0:
                    swap(arr, lo, mid);
                    lo++;
                    mid++;
                    break;
                case 1:
                    mid++;
                    break;
                case 2:
                    swap(arr, mid, hi);
                    hi--; // mid is not incremented because arr[mid] after swap can be zero
                    break;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1, 2, 0};
        sort(arr);

        System.out.println(Arrays.toString(arr));
    }
}
