import java.io.*;
import java.util.*;

public class Q2 {
    private static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    private static final ArrayList<Integer> li = new ArrayList<>();

    static void main(String[] args) throws IOException {
        System.out.print("Enter n: ");
        int n = Integer.parseInt(input.readLine());

        if (n > 2) {
            boolean[] composite = new boolean[n];
            for (int i = 2; i * i < n; i++) if (!composite[i]) for (int j = i * i; j < n; j += i) composite[j] = true;
            for (int i = 2; i < n; i++) if (!composite[i]) li.add(i);
            for (int prime : li) System.out.println(prime);
        }
    }
}
