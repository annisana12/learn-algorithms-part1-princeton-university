import java.util.List;
import java.util.Random;

/**
 * Question:
 * Suppose that you have an n-story building (with floors 1 through n)
 * and plenty of eggs. An egg breaks if it is dropped from floor T or
 * higher and doesn't break otherwise. Your goal is to devise a
 * strategy to determine the value of T given the following limitations
 * on the number of eggs and tosses:
 * Version 0 : 1 egg, <= T tosses
 * Version 1 : ~ 1 lg n eggs and ~ 1 lg n tosses
 * Version 2 : ~ lg T eggs and ~ 2 lg T tosses
 * Version 3 : 2 eggs and ~ 2 sqrt(n) tosses
 * Version 4 : 2 eggs and <= c sqrt(T) tosses for some fixed constant c
 */

public class EggDrop {
    private static int floorThreshold;
    private static int tosses;

    /**
     * Initialize floor threshold and tosses.
     * This value is unknown beforehand in the real-world
     * scenario. The value of floor threshold is set here
     * for the purpose of demonstrating how isEggBreaks
     * function could be implemented in a coding problem.
     * In a real-world scenario, the outcome of isEggBreaks
     * function is determined by the actual experiment of
     * dropping egg from the given floor.
     *
     * @param n is the total number of floors
     */
    private static void init(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("n must be >= 1");
        }

        Random random = new Random();
        floorThreshold = random.nextInt(n) + 1;
        tosses = 0;
    }

    private static boolean isEggBreaks(int floor) {
        return floor >= floorThreshold;
    }

    private static void validateRemainingEggs(int eggs) {
        if (eggs <= 0) {
            throw new RuntimeException("There are no more eggs left to throw. The experiment has failed.");
        }
    }

    public static int findTVersionO(int n) {
        init(n);

        int floor = 1;

        while (floor <= n) {
            tosses++;

            if (isEggBreaks(floor)) {
                break;
            }

            floor++;
        }

        return floor;
    }

    public static int findTVersion1(int n) {
        init(n);

        int eggs = (int) Math.floor(Math.log(n) / Math.log(2));
        int floorThreshold = 1;
        int lo = 1;
        int hi = n;

        while (lo <= hi) {
            validateRemainingEggs(eggs);

            int mid = (lo + hi) / 2;
            tosses++;

            if (isEggBreaks(mid)) {
                floorThreshold = mid;
                hi = mid - 1;
                eggs--;
            } else {
                lo = mid + 1;
            }
        }

        return floorThreshold;
    }

    public static int getNumberOfTosses() {
        return tosses;
    }

    public static void runTest(int version, int n) {
        List<Integer> versions = List.of(0, 1, 2, 3, 4);

        if (!versions.contains(version)) {
            throw new IllegalArgumentException("Please input valid version (0 - 4)");
        }

        int floorThreshold = 0;
        int maxTosses = 0;

        if (version == 0) {
            floorThreshold = findTVersionO(n);
            maxTosses = floorThreshold;
        } else if (version == 1) {
            floorThreshold = findTVersion1(n);
            maxTosses = (int) Math.ceil(Math.log(n) / Math.log(2));
        }

        int tosses = getNumberOfTosses();

        System.out.println("Version " + version + " with " + n + " floors");
        System.out.println("Floor threshold         : " + floorThreshold);
        System.out.println("Number of tosses        : " + tosses);
        System.out.println("Max number of tosses    : " + maxTosses);
        System.out.println("=====================================");
    }

    public static void main(String[] args) {
        runTest(0, 10);
        runTest(1, 10);
        runTest(1, 100);
    }
}
