package hangmanapplication;

import java.util.Scanner;


public class HangManApplication {

    public static void main(String[] args) {
	// write your code here
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to Hangman!"
                +"If you guess wring 6 times then I win"
        );
        System.out.println();
        System.out.println("I have picked my word. Below that is a picture, and below that is your current guess");
        boolean doYouWantToPlay = true;
        while (doYouWantToPlay) {
            //keep playing
            System.out.println("Alright let's play");
            HangMan game = new HangMan();

            do {
                System.out.println();
                System.out.println(game.drawPicture());
                System.out.println();
                System.out.println(game.getFormalCurrentGuess());
                System.out.println(game.mysteryWord);
                System.out.println(game.currentTry+" / "+game.maxTries);

                //Get the guess
                System.out.println("Enter a character as a guess.");
                char guess =  (sc.next().toLowerCase()).charAt(0);
                System.out.println();

                //Guess Failed
                while(game.isGuessedAlready(guess)){
                    System.out.println("Try Again! You've already guessed that character.");
                    guess = (sc.next().toLowerCase()).charAt(0);
                }

                //Play the Guess
                if(game.playGuess(guess)){
                    System.out.println("Great Guess! That character is in the word");
                } else {
                    System.out.println("Unfortunately that Character is not in the word");
                }

            }
            while(!game.gameOver());

            System.out.println();
            System.out.println("Do you want to play? Enter Y if you do");
            Character resp = sc.next().toUpperCase().charAt(0);
            doYouWantToPlay = (resp == 'Y');
        }

    }
}
