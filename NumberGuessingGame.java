import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        int gamesPlayed = 0;
        int gamesWon = 0;
        int bestScore = 999;

        System.out.println("================================");
        System.out.println("     NUMBER GUESSING GAME");
        System.out.println("================================");

        String playAgain = "yes";

        while (playAgain.equalsIgnoreCase("yes")
                || playAgain.equalsIgnoreCase("y")) {

            System.out.println("\nChoose Difficulty:");
            System.out.println("1. Easy   (1-50, 10 attempts)");
            System.out.println("2. Medium (1-100, 7 attempts)");
            System.out.println("3. Hard   (1-200, 8 attempts)");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            int maxNumber;
            int maxAttempts;

            if (choice == 1) {
                maxNumber = 50;
                maxAttempts = 10;
            } else if (choice == 2) {
                maxNumber = 100;
                maxAttempts = 7;
            } else if (choice == 3) {
                maxNumber = 200;
                maxAttempts = 8;
            } else {
                System.out.println("Invalid choice! Selecting Easy mode.");
                maxNumber = 50;
                maxAttempts = 10;
            }

            int secretNumber = random.nextInt(maxNumber) + 1;
            int attempts = 0;
            boolean guessedCorrectly = false;

            System.out.println("\nI have selected a number between 1 and "
                    + maxNumber + ".");
            System.out.println("You have " + maxAttempts + " attempts to guess it.");

            while (attempts < maxAttempts && !guessedCorrectly) {

                System.out.print("Enter your guess: ");
                int guess = sc.nextInt();
                attempts++;

                if (guess < 1 || guess > maxNumber) {
                    System.out.println("Please enter a number between 1 and "
                            + maxNumber + ".");
                    attempts--;
                    continue;
                }

                if (guess == secretNumber) {
                    guessedCorrectly = true;
                    System.out.println("Congratulations! You guessed it correctly!");
                    System.out.println("Number of attempts: " + attempts);

                    gamesWon++;

                    if (attempts < bestScore) {
                        bestScore = attempts;
                    }

                } else if (guess < secretNumber) {
                    System.out.println("Too low! Try a bigger number.");
                } else {
                    System.out.println("Too high! Try a smaller number.");
                }

                if (!guessedCorrectly) {
                    int difference = Math.abs(guess - secretNumber);

                    if (difference <= 5) {
                        System.out.println("Hint: You are very close!");
                    } else if (difference <= 15) {
                        System.out.println("Hint: You are getting warmer!");
                    } else {
                        System.out.println("Hint: You are still far away!");
                    }

                    System.out.println("Attempts remaining: "
                            + (maxAttempts - attempts));
                }
            }

            if (!guessedCorrectly) {
                System.out.println("\nSorry! You ran out of attempts.");
                System.out.println("The correct number was " + secretNumber);
            }

            gamesPlayed++;

            double winRate = (gamesWon * 100.0) / gamesPlayed;

            System.out.println("\n--------- GAME STATS ---------");
            System.out.println("Games played: " + gamesPlayed);
            System.out.println("Games won: " + gamesWon);
            System.out.printf("Win rate: %.1f%%\n", winRate);

            if (gamesWon > 0) {
                System.out.println("Best score: " + bestScore + " attempts");
            }

            System.out.print("\nDo you want to play again? (yes/no): ");
            playAgain = sc.next();
        }

        System.out.println("\nThanks for playing!");
        System.out.println("Final score: " + gamesWon + "/" + gamesPlayed);

        sc.close();
    }
}