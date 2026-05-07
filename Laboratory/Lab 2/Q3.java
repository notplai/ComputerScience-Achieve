import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Q3 {
    private static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    private static double[] input(String prompt) throws IOException {
        System.out.print(prompt);
        StringTokenizer token = new StringTokenizer(input.readLine());
        return new double[]{
                Double.parseDouble(token.nextToken()),
                Double.parseDouble(token.nextToken())
        };
    }

    static void main(String[] args) throws IOException {
        double[] A = input("Point A: ");
        double[] B = input("Point B: ");

        Point p1 = new Point(A[0], A[1]);
        Point p2 = new Point(B[0], B[1]);
        System.out.printf("A = %s B = %s Distance = %.1f%n", p1, p2, p1.distance(p2));

        double[] translate = input("Move the points: ");
        p1.translate(translate[0], translate[1]);
        p2.translate(translate[0], translate[1]);
        System.out.printf("A = %s B = %s Distance = %.1f%n", p1, p2, p1.distance(p2));

        System.out.println(p1.equals(p2)
                ? "A and B are the same position."
                : "A and B are not at the same position."
        );
    }
}

class Point {
    double x, y;

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    double distance(Point other) {
        double dx = other.x - this.x;
        double dy = other.y - this.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    void translate(double dx, double dy) {
        x += dx;
        y += dy;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Point other)) return false;

        return Double.doubleToRawLongBits(this.x) == Double.doubleToRawLongBits(other.x) &&
                Double.doubleToRawLongBits(this.y) == Double.doubleToRawLongBits(other.y);
    }

    @Override
    public String toString() {
        return String.format("(%.1f, %.1f)", x, y);
    }
}