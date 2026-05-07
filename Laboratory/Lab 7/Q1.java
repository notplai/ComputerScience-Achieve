import java.util.Arrays;

interface Evaluation {
    double evaluate();
    char grade(double score);
}

public class Q1 {
    static void main(String[] args) {
        int[] sc = {45, 48};
        Secretary s = new Secretary("Sasipa", 20000, sc, 60);
        if (s.grade(s.evaluate()) == 'P') {
            System.out.println("Congratulations");
            System.out.println(s);
        } else
            System.out.println("Sorry");
        int[] subjScore = {45, 90, 89, 88, 75, 56, 46, 70, 90, 65};
        Subject sj = new Subject("Java", subjScore);
        System.out.println(sj + " Grade class is " +
                sj.grade(sj.evaluate()));
    }
}

class Employee {
    private final String name;
    private int salary;

    Employee (String name) { this.name = name; }
    void setSalary(int salary) { this.salary = salary; }

    @Override
    public String toString() { return String.format("%s%nsalary = %d", this.name, this.salary); }
}

class Secretary extends Employee implements Evaluation {
    private final int[] score;

    Secretary (String name, int salary, int[] score, int typingSpeed) {
        super(name);
        super.setSalary(salary);
        this.score = score;
    }

    @Override
    public double evaluate() { return Arrays.stream(this.score).sum(); }

    @Override
    public char grade(double score) {
        if (score < 90) return 'F';

        super.setSalary(18_000);
        return 'P';
    }
}

class Subject implements Evaluation {
    private final String subjName;
    private final int[] score;

    Subject (String subjectName, int[] score) {
        this.subjName = subjectName;
        this.score = score;
    }

    @Override
    public double evaluate() { return Arrays.stream(this.score).average().orElse(0.0); }

    @Override
    public char grade(double score) { return score < 70 ? 'F' : 'P'; }

    @Override
    public String toString() { return this.subjName; }
}