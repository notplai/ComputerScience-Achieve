import org.jetbrains.annotations.*;

import java.io.*;
import java.util.*;

public class Q4 {
    static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    static void main(String[] args) throws IOException {
        CourseRegister ComputerScience = new CourseRegister("2301260");

        while (true) {
            System.out.println("Enter ID, name, major");
            String data = input.readLine();

            if (data.equals("X")) break;
            StringTokenizer token = new StringTokenizer(data);
            String  id = token.nextToken(),
                    name = token.nextToken() + " " + token.nextToken(),
                    major = token.nextToken();

            System.out.printf("%s %s%n", id,
                    ComputerScience.register(new Student(id, name, major))
                            ? "REGISTERED SUCCESSFULLY"
                            : "REGISTERED UNSUCCESSFULLY"
            );
        }
        ComputerScience.printReg();

        System.out.println("Drop student -> Enter ID of student:");
        String id = input.readLine();
        System.out.printf("%s %s%n", id, ComputerScience.drop(id)
                ? "DROPPED SUCCESSFULLY"
                : "NOT REGISTERED"
        );
        ComputerScience.printReg();
        input.close();
    }
}

class Student {
    final String id;
    private final String name;
    private final String major;

    Student(String id, String name, String major) {
        this.id = id;
        this.name = name;
        this.major = major;
    }

    private int getDigit(int start, int end) { return Integer.parseInt(this.id.substring(start, end + 1)); }

    int getFaculty() { return this.getDigit(8, 9); }

    int getLevelCode() { return this.getDigit(2, 2); }

    int getYear() { return this.getDigit(0, 1); }

    boolean sameYear(@NotNull Student student) { return this.getYear() == student.getYear(); }

    boolean sameLevel(@NotNull Student student) { return this.getLevelCode() == student.getLevelCode(); }

    boolean sameFaculty(@NotNull Student student) { return this.getFaculty() == student.getFaculty(); }

    @Override
    public String toString() { return String.format("%s %s", this.id, this.name); }
}

class CourseRegister {
    final String subjectCode;
    private final ArrayList<Student> students = new ArrayList<>();

    CourseRegister(String subjectCode) { this.subjectCode = subjectCode;}

    boolean register(@NotNull Student student) { return this.students.add(student); }

    boolean drop(@NotNull String student) { return this.students.removeIf(s -> s.id.equals(student)); }

    void printReg() {
        for (Student student : students) System.out.println(student);
        System.out.printf("Total Student in this course: %d%n", this.students.size());
    }
}
