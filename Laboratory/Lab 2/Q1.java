import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Q1 {
    static void main(String[] args) throws IOException {
        System.out.print("Enter firstname lastname age: ");
        StringTokenizer token = new StringTokenizer(new BufferedReader(new InputStreamReader(System.in)).readLine(), " ");
        System.out.printf("%s, %s is %d years old.",
                token.nextToken(), token.nextToken(), Integer.parseInt(token.nextToken())
        );
    }
}