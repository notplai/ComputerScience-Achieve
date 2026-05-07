import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Q3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a positive integer : ");
        int number = input.nextInt();

        System.out.println("Prime factors of " + number + " are " + primeFactor(number));

    }

    static ArrayList<Integer> primeFactor(int n) {
        return primeFactor(n, 2, new ArrayList<>());
    }

    // n = 3, d = 2
    // n = 3, d = 3
    // n = 3, d = 3 [3]
    // n = 3, d = 1 [3, 1]
    static ArrayList<Integer> primeFactor(int n, int d, ArrayList<Integer> factor) {
        if (n < 2) return factor;

        if (n % d == 0) {
            if (!factor.contains(d)) factor.add(d);
            return primeFactor(n / d , d, factor);
        }
        return primeFactor(n, (d * d > n ? n : d + (d == 2 ? 1 : 2)), factor);
    }
}

