import java.io.*;
import java.util.*;

public class Q1 {
    static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static final ArrayList<Integer> li = new ArrayList<>();

    static void main() throws IOException {
        int n = Integer.parseInt(input.readLine());
        if (n <= 0) return;

        for (int candidate = 2; li.size() < n; candidate++) {
            if (candidate == 2) { li.add(candidate);continue; }
            if (candidate % 2 == 0) continue;

            boolean isPrime = true;
            for (int i = 3; i * i <= candidate; i += 2) {
                if (candidate % i == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) li.add(candidate);
        }

        for (int p : li) System.out.println(p);
    }
}
