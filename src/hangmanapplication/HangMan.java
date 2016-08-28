package hangmanapplication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;



public class HangMan {

    String mysteryWord;
    StringBuilder currentGuess;
    ArrayList<Character> previousGuesses = new ArrayList<Character>();

    int maxTries = 6;
    int currentTry = 0;

    ArrayList<String> dictionary = new ArrayList<String>();
    private static FileReader fileReader;
    private static BufferedReader bufferedFileReader;

    public HangMan(){
        initializeStreams();
        mysteryWord = pickWord();
        currentGuess = initCurrentGuess();
    }

    public void initializeStreams() {
        try {
            File inFile = new File("dictionary.txt");
            //System.out.print( inFile.length() );
            fileReader = new FileReader(inFile);
            bufferedFileReader = new BufferedReader(fileReader);

            String currentLine = bufferedFileReader.readLine();
            //System.out.println(inFile.length());
            while(currentLine != null){
//                System.out.println(currentLine);
                dictionary.add(currentLine);
                currentLine = bufferedFileReader.readLine();

            }

            bufferedFileReader.close();
            fileReader.close();

        }catch (IOException e){
            System.out.println("IOException e Could not init Streams");
            e.printStackTrace();

        }

    }

    public String pickWord(){
        Random rand = new Random();
        int wordIndex =  Math.abs(rand.nextInt() % dictionary.size());
        return dictionary.get(wordIndex);
    }

    public StringBuilder initCurrentGuess(){
        StringBuilder current = new StringBuilder();
        for(int i = 0; i < mysteryWord.length() * 2; i++){
            if(i%2 == 0){
                current.append('-');
            } else {
                current.append(' ');
            }
        }
        return  current;
    }

    private String getCurrentGuessString(){
        StringBuilder tmp = new StringBuilder();
        for(int i = 0; i < currentGuess.length(); i++){
            if(i%2 == 0){
                tmp.append(currentGuess.charAt(i));
            }
        }
        return tmp.toString();
    }


    public String getFormalCurrentGuess(){
        return "Current Guess: "+currentGuess.toString();
    }

    public boolean gameOver(){
        String currentGuessString = getCurrentGuessString();
        if((currentTry >= maxTries)){
            System.out.println("You lost with too many gueses");
            System.out.println( drawPicture() );
            return true;
        } else if((currentGuessString.equals(mysteryWord))){
            System.out.println("You Guessed the right word: "+currentGuessString+"!");
            return true;
        }
        return false;
    }


    public boolean isGuessedAlready(Character guess){
        return previousGuesses.contains(guess);
    }

    public boolean playGuess(Character guess){
        previousGuesses.add(guess);
        boolean goodGuess = false;
        for(int i = 0; i < mysteryWord.length(); i++){
            if(guess == mysteryWord.charAt(i)) {
                currentGuess.setCharAt(i * 2, guess);
                goodGuess = true;
            }
        }
        if(!goodGuess) {  currentTry++;  }
        return goodGuess;
    }

    public String drawPicture(){
        switch(currentTry){
            case 0: return noPersonDraw();
            case 1: return addHeadDraw();
            case 2: return addBodyDraw();
            case 3: return addOneArmDraw();
            case 4: return addSecondArmDraw();
            case 5: return addFirstLegDraw();
            default: return fullPersonDraw();
        }
    }

    private String noPersonDraw(){
        return      " - - - - - -\n"+
                    "|          |\n"+
                    "|           \n"+
                    "|           \n"+
                    "|           \n"+
                    "|           \n"+
                    "|           \n";
    };

    private String addHeadDraw(){
        return     " - - - - - -\n"+
                    "|          | \n"+
                    "|          O \n"+
                    "|           \n"+
                    "|           \n"+
                    "|            \n"+
                    "|            \n";
        }

    private String addBodyDraw(){
        return     " - - - - - -\n"+
                "|          | \n"+
                "|          O \n"+
                "|          | \n"+
                "|          | \n"+
                "|            \n"+
                "|            \n";
    }

    private String addOneArmDraw(){
        return      " - - - - - -\n"+
                    "|          | \n"+
                    "|          O \n"+
                    "|         /| \n"+
                    "|          |   \n"+
                    "|              \n"+
                    "|              \n";
    }


    private String addSecondArmDraw(){
        return      " - - - - - -\n"+
                "|          | \n"+
                "|          O \n"+
                "|         /|\\ \n"+
                "|          |   \n"+
                "|              \n"+
                "|              \n";
    }

    private String addFirstLegDraw(){
        return      " - - - - - -\n"+
                "|          | \n"+
                "|          O \n"+
                "|         /|\\ \n"+
                "|          |   \n"+
                "|         /    \n"+
                "|              \n";
    }

    private String fullPersonDraw(){
        return      " - - - - - -\n"+
                "|          | \n"+
                "|          O \n"+
                "|         /|\\ \n"+
                "|          |   \n"+
                "|         / \\    \n"+
                "|              \n";
    }

}