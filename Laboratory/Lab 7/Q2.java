import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q2 {
    static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    static void main() throws IOException {
//        System.out.print("Enter x and y : ");
        StringTokenizer tokens = new StringTokenizer(input.readLine());
        int x = Integer.parseInt(tokens.nextToken());
        int y = Integer.parseInt(tokens.nextToken());

        System.out.println(power(x, y));
    }

    static int power(int b, int p) {
        if (p == 0) return 1;
        int dH = power(b, p >> 1);
        return (p & 1) == 0 ? dH * dH : dH * dH * b;
    }
}