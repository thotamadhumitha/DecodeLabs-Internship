import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
public class NumberGuessingGame {
    public static void main(String[] args) {
       Random rand = new Random();
        Scanner sc = new Scanner(System.in);
        int totalRounds = 0;
        int score = 0;
        int totalAttempts = 0;
        boolean playAgain = true;
        System.out.println("Welcome to the Number Guessing Game!");
        while(playAgain){
            int secret_number = rand.nextInt(100)+1;
            int maxAttempts = 10;
            int attempts = 0;
            boolean guessed = false;
            totalRounds++;
            System.out.println("\n Round " + totalRounds + " - Guess between 1 and 100");
            System.out.println("You have " + maxAttempts + " attempts.\n");
            while (attempts < maxAttempts && !guessed) {
                try{
                    System.out.println("Guess a number between 1 and 100 : ");
                    int guess = sc.nextInt();
                    attempts++;
                    if (guess < 1 || guess > 100) {
                            System.out.println("Guess between 1 and 100 only!");
                            continue;
                        }
                    if(guess == secret_number){
                        System.out.println("Congratulations! You guessed the number.");
                        guessed = true;
                        totalAttempts += attempts;
                        score++;
                        break;
                    }else if(guess < secret_number){
                        System.out.println("Your guess is low. Try again.");
                    }else{
                        System.out.println("Your guess is high. Try again.");
                    }
                }catch(InputMismatchException e){
                    System.out.println("Invalid input. Please enter a number between 1 and 100.");  
                    sc.next(); // Clear the invalid input
                }
            }
            if (!guessed) {
                System.out.println(" Out of attempts! The number was: " + secret_number);
                totalAttempts += maxAttempts;
            }
            System.out.print("\n Play another round? (yes/no): ");
            sc.nextLine();  // ⭐ CRITICAL: Consume leftover newline
            String response = sc.nextLine().trim().toLowerCase();
            playAgain = response.equals("yes") || response.equals("y");
        }
        System.out.println("\n Game Over! You played " + totalRounds + " round(s).");
        if (totalRounds > 0) {
            double avgAttempts = (double) totalAttempts / totalRounds;
            System.out.println(" Average attempts per round: " + String.format("%.2f", avgAttempts));
            System.out.println("Score: " + score + " out of " + totalRounds + " rounds won");
    
            // ⭐ ADD PERFORMANCE CHECK
            if (score >= totalRounds / 2.0) {
                System.out.println(" Good Performance! Keep it up!");
            } else {
                System.out.println(" Better luck next time! Practice makes perfect!");
            }
        }
        sc.close();
        System.out.println(" Thanks for playing!");
    }
}
