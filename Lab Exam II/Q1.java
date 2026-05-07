import java.util.Scanner;

public class Q1 {
    static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println(recurB(input.nextInt()));
    }

    static int recurB(int n) {
        if (n <= 1) return 1;
        System.out.println(n);

        return n % 2 == 0 ? recurB(n - 3) : recurB(n - 2);
    }
}