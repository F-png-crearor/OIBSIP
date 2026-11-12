    import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Random rand = new Random();
    List<String> summary = new ArrayList<>(); 
    int round = 0;
    boolean playAgain = true;

    System.out.println("=== Number Gusessing Game ===");

    while (playAgain) {
        round++;

        //Difficulty selection (bonus)
        System.out.println("\nChoose difficulty: ");
        System.out.println("1) Easy (1-50, 10 attempts)");
        System.out.println("2) Medium (1-100, 7 attempts)");
        System.out.println("3) Hard (1-200, 5 attempts)");
        int choice = readInt(sc, "Enter 1, 2 or 3: ", 1, 3); 

        int max;
        int maxAttempts;
        if (choice == 1) {
            max = 50;
            maxAttempts = 10;
        } else if (choice == 2) {
            max = 100; 
            maxAttempts = 7;
        } else {
            max = 200;
            maxAttempts = 5;

        }
        int target = rand.nextInt(max) + 1;
        int attempts = 0;
        boolean won = false;

        System.out.println("\nRound " + round + " : I'm thinking of a number between 1 and " + max + ".");

        while (attempts < maxAttempts && !won) {
            int guess = readInt(sc,
                "Attempt " + (attempts + 1) + " of " + maxAttempts + " - Your guess: ", 1, max);
                attempts++;

                if (guess > target) {
                    System.out.println("Too High!"); 
                } else if (guess < target) {
                    System.out.println("Too Low!"); 
                } else {
                    System.out.println("Correct!");
                    won = true;
                }
        }
        if (won) {
            System.out.println("You won in " + attempts + " attempts!");
            summary.add("Round " + round + " - lost (number was " + target + ")"); 
        }
        //Play again?
        String answer = "";
        while (!answer.equals("yes") && !answer.equals("no")) {
            System.out.print("\nPlay again? (yes/no): ");
            answer = sc.nextLine().trim().toLowerCase();
        }
        playAgain = answer.equals("yes");
        
    }
    // Score summary across rounds
    System.out.println("\n=== Game Summary ===");
    for (String line : summary) {
        System.out.println(line);
    }
    System.out.println("Thanks for playing!");
    sc.close();
}

// Reads a whole number within a range; keeps asking until input is valid 
private static int readInt(Scanner sc, String prompt, int min, int max) {
    while (true) {
        System.out.println(prompt);
        String input = sc.nextLine().trim();
        try {
            int value = Integer.parseInt(input);
            if (value >= min && value <= max) {
                return value;
            }
            System.out.println("Please enter a number between " + min + " and " + max + ".");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a whole number.");
        }
    }
}
}
