import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Q2 {
    private static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    private static double[] input(String prompt) throws IOException {
        System.out.print(prompt);
        StringTokenizer token = new StringTokenizer(input.readLine());
        return new double[] {
                Double.parseDouble(token.nextToken()),
                Double.parseDouble(token.nextToken())
        };
    }

    private static double dist(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    static void main(String[] args) throws IOException {
        double[] p1 = input("First point : ");
        double[] p2 = input("Second point: ");
        double[] p3 = input("Third point : ");
        double[] p4 = input("Fourth point: ");

        double cx = (p1[0] + p2[0] + p3[0] + p4[0]) / 4;
        double cy = (p1[1] + p2[1] + p3[1] + p4[1]) / 4;

        System.out.printf("The centroid is (%f, %f).%n", cx, cy);
        double d1 = dist(cx, cy, p1[0], p1[1]);
        double d2 = dist(cx, cy, p2[0], p2[1]);
        double d3 = dist(cx, cy, p3[0], p3[1]);
        double d4 = dist(cx, cy, p4[0], p4[1]);

        System.out.printf("Sum of distance is %f.%n", d1 + d2 + d3 + d4);
        System.out.printf("Shortest distance is %f.%n", Math.min(Math.min(d1, d2), Math.min(d3, d4)));
        System.out.printf("Longest distance is %f.%n", Math.max(Math.max(d1, d2), Math.max(d3, d4)));
    }
}