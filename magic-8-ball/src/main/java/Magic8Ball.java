import java.util.Scanner;

public class Magic8Ball {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        System.out.println("******************************");
        System.out.println("*** The Mysterious Katie's ***");
        System.out.println("***     Magic-8-Ball       ***");
        System.out.println("******************************");
        System.out.println(" ");
        System.out.println("Enter your question");


        String[] answersArray;

        answersArray = new String[]{
                "It is Certain", "It is decidedly so", "Without a doubt", "Yes - definitely", "You may rely on it", "As I see it, yes", "Most Likely",
                "Outlook good", "Yes", "Signs point to yes", "Reply hazy, try again", "Ask again later", "Better not tell you now",
                "Cannot predict now", "Concentrate and ask again", "Don't count on it", "My reply is no", "My sources say no",
                "Outlook not so good", "Very doubtful"};


        String input = scanner.nextLine();


        String answer = null;

        if (input.contains(" ")) {
            int location = (int) Math.floor(Math.random() * 20);
            answer = answersArray[location].toString();
            System.out.println(answer);


        } else {
            System.out.println("The Great and Mysterious Katie knows that you have not entered a question.");
        }


    }
}




