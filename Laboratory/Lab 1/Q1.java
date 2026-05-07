import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

class Q1 {
    private static int input(String prompt) throws IOException {
        System.out.print(prompt);
        return Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
    }

    static void main(String[] args) throws IOException {
        LocalDate today = LocalDate.now();
        System.out.println("Current Date: " + today.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));

        int dd = input("Enter day: ");
        int mm = input("Enter month: ");
        int yy = input("Enter year: ");

        LocalDate date = LocalDate.of(yy, mm, dd);
        Period p = Period.between(today, date);

        System.out.printf(
                "Duration is: %d years, %d months, and %d days.%n",
                p.getYears(), p.getMonths(), p.getDays()
        );
    }
}
