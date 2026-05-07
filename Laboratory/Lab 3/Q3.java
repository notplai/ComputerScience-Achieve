import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Q3 {
    static void main(String[] args) throws IOException {
        new Game().play();
    }
}

class Game {
    static final int ROCK = 0;
    static final int PAPER = 1;
    static final int SCISSORS = 2;

    private int userScore = 0;
    private int comScore = 0;
    // 272666

    private final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    private final Random random = new Random();

    void play() throws IOException {
            while (Math.abs(userScore - comScore) < 2) {
                int user = getUserChoice();
                int com = getComputerChoice();

                System.out.printf("You enter: %s%n", choice2String(user));
                System.out.printf("Computer: %s%n", choice2String(com));

                // 1 = win, 0 = tie, -1 = lose
                // using an if-else statement as you want.
                int result = (user == com) // contitional expression
                        ? 0 // True
                        : (user - com + 3) % 3 == 1 // False and contitional expression
                        ? 1 // True
                        : -1; // False

                switch (result) {
                    case 1 -> {
                        System.out.println("You win!");
                        userScore++;
                    }
                    case -1 -> {
                        System.out.println("You lose!");
                        comScore++;
                    }
                    default -> System.out.println("It's a tie.");
                }
            }

            System.out.println("----------------------------------------");
            System.out.println(userScore > comScore
                    ? "Congrats! You win." // True
                    : "Too bad! You lose." // False
            );
            System.out.printf("User Score: %d%n", userScore);
            System.out.printf("Computer Score: %d%n ", comScore);
    }

    private int getUserChoice() throws IOException {
        while (true) {
            try {
                System.out.print("Enter 0 for ROCK, 1 for PAPER, 2 for SCISSORS: ");
                int choice = Integer.parseInt(input.readLine());
                if (choice < 0 || choice > 2) throw new NumberFormatException();
                return choice;
            } catch (NumberFormatException _) {}
        }
    }

    private int getComputerChoice() {
        return random.nextInt(3);
    }

    private @NotNull String choice2String(int choice) {
        return switch (choice) {
            case ROCK -> "ROCK";
            case PAPER -> "PAPER";
            case SCISSORS -> "SCISSORS";
            default -> throw new IllegalStateException();
        };
    }
}
