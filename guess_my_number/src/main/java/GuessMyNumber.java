import java.util.Scanner;

public class GuessMyNumber {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        System.out.println("*******************************");
        System.out.println("******* Guess My Number *******");
        System.out.println("*******************************");
        System.out.println();
        System.out.println("I've chosen a number between 0 and 100.");
        System.out.println("What's the fewest number of guesses you can use?");
        System.out.println();
        System.out.println("Are you ready (Y/N)?");
        String input = scanner.nextLine();

        while (input.toUpperCase().equals("N")) {
            System.out.println("Thanks for playing, Goodbye!");
            System.exit(0);

        } while (input.toUpperCase().equals("Y")){
            int answer = 0;
            answer = (int) Math.floor(Math.random() * 100);

            System.out.println("Enter your guess here: ");
            int guess = Integer.parseInt(scanner.nextLine());
            int count = 0;


            while (guess != answer) {

                if (answer > guess) {
                    System.out.println("Sorry, my number is higher than " + guess);
                    count++;
                    System.out.println("That's " + count + " guesses. Please, guess again! ");
                    guess = Integer.parseInt(scanner.nextLine());


                } else if (guess > answer) {
                    System.out.println("Sorry, my number is lower than " + guess);
                    count++;
                    System.out.println("That's " + count + " guesses. Please, guess again! ");
                    guess = Integer.parseInt(scanner.nextLine());

                }
                if (answer == guess) {
                    count++;
                    System.out.println("Congratulations! My number was " + answer + "!");
                    System.out.println("It took you " + count + " guesses to guess my number!");
                    System.out.println("Would you like to play again (Y/N)?");
                    input = scanner.nextLine();
                }
            }
        }
    }
}
