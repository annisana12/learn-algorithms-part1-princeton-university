package week1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        int i = 1;
        String champion = "";

        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            double p = 1.0 / i;

            if (StdRandom.bernoulli(p)) {
                champion = word;
            }

            i++;
        }

        System.out.println(champion);
    }
}
