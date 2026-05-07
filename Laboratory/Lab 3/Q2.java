import org.jetbrains.annotations.*;
import java.io.*;
import java.util.*;

public class Q2 {
    static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    static void main(String[] args) throws IOException {
        System.out.println("Student 1: Enter ID, name, major: ");
        StringTokenizer token = new StringTokenizer(input.readLine());
        Student student1 = new Student(token.nextToken(), token.nextToken(), token.nextToken());
        System.out.println("Student 2: Enter ID, name, major: ");
        token = new StringTokenizer(input.readLine());
        Student student2 = new Student(token.nextToken(), token.nextToken(), token.nextToken());

        System.out.printf("%s %s %s",
                student1.sameLevel(student2) ? "Same level" : "Different level",
                student1.sameYear(student2) ? "same year" : "different year",
                student1.sameFaculty(student2) ? "same faculty" : "different faculty"
        );
    }
}

class Student {
    private String id;
    private String name;
    private String major;

    public Student(String id, String name, String major) {
        this.id = id;
        this.name = name;
        this.major = major;
    }

    private int getDigit(int start, int end) {
        return Integer.parseInt(id.substring(start, end + 1));
    }

    public int getFaculty() {
        return getDigit(8, 9);
    }

    public int getLevelCode() {
        return getDigit(2, 2);
    }

    public int getYear() {
        return getDigit(0, 1);
    }

    public boolean sameYear(@NotNull Student student) {
        return this.getYear() == student.getYear();
    }

    public boolean sameLevel(@NotNull Student student) {
        return this.getLevelCode() == student.getLevelCode();
    }

    public boolean sameFaculty(@NotNull Student student) {
        return this.getFaculty() == student.getFaculty();
    }


}