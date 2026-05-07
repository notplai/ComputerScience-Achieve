import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1 {
    static void main(String[] args) throws IOException {

        BufferedReader input =
                new BufferedReader(new InputStreamReader(System.in));

        double totalGradePoints = 0;
        int totalCredits = 0;

        System.out.println("Enter course ID, credit and grade; or X to terminate");

        while (true) {
            String line = input.readLine();

            if (line == null || line.trim().equalsIgnoreCase("X")) {
                break;
            }

            StringTokenizer st = new StringTokenizer(line);

            if (st.countTokens() < 3) {
                System.out.println("Invalid input line");
                continue;
            }

            String courseId = st.nextToken();
            int credit = Integer.parseInt(st.nextToken());
            String grade = st.nextToken().toUpperCase();

            double gradeValue;
            switch (grade) {
                case "A":  gradeValue = 4.0; break;
                case "B+": gradeValue = 3.5; break;
                case "B":  gradeValue = 3.0; break;
                case "C+": gradeValue = 2.5; break;
                case "C":  gradeValue = 2.0; break;
                case "D+": gradeValue = 1.5; break;
                case "D":  gradeValue = 1.0; break;
                case "F":  gradeValue = 0.0; break;
                default:
                    System.out.println("Invalid grade: " + grade);
                    continue;
            }

            totalGradePoints += gradeValue * credit;
            totalCredits += credit;
        }

        if (totalCredits > 0) {
            double gpa = totalGradePoints / totalCredits;
            System.out.printf("GPA = %.2f%n", gpa);
        } else {
            System.out.println("No courses entered.");
        }
    }
}