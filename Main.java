import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    public static void main(String arg[])throws FileNotFoundException
    {
        Scanner input=new Scanner(System.in);       //Scanner obj
        Game mainGame=new Game();       //Object of Game class
        int wrongGuesses=0;             //a counter to check the number of wrong guesses

        while((wrongGuesses != 10) && !mainGame.winCheck())
        {
            String letter;
            System.out.print("You are Guessing : ");
            for(int i=0;i<mainGame.getMovie().length();i++)
                System.out.print(mainGame.getGuess(i));

            System.out.println("\nYou Have (" + wrongGuesses + ") wrong guesses");

            System.out.print("Guess a letter : ");
            letter=input.next();

            if(mainGame.guessCheck(letter.charAt(0)))
            {
                System.out.println("Correct guess");
            }
            else
            {
                wrongGuesses++;
                System.out.println("Wrong guess");
            }
        }

        System.out.println("The movie is " + mainGame.getMovie());

        if(mainGame.winCheck())
        {
            System.out.println("You've Guessed the movie ... You Won");
        }
        else
        {
            System.out.println("You've ran out of guesses ... You Lose");
        }
    }
}

class Game {
    private String movie;           //a string that will store movie name taken from file
    private char guess[];           //a char array that will store what user has guessed
    private int movieLength;        //will store length of the movie
    private ArrayList correctGuesses;         //an array list to store all the correct guesses made by the user

    Game() throws FileNotFoundException             //constructor for this class
    {
        File file = new File("D:\\Projects\\GuessTheMovie\\Movies.txt"); //change pathname everytime
        Scanner fScan = new Scanner(file);
        int x = (int) (Math.random() * 100);
        correctGuesses=new ArrayList();

        while (fScan.hasNextLine() && x >= 0)
        {
            movie = fScan.nextLine();
            x--;
        }

        movieLength = movie.length();
        guess = new char[movieLength];

        for (int i = 0; i < movieLength; i++)
        {
            if (movie.charAt(i) == ' ')
            {
                guess[i] = ' ';
            }
            else
            {
                guess[i] = '_';
            }
        }
    }

    public String getMovie() {
        return movie;
    }       //getter method

    public char getGuess(int i) {
        return guess[i];
    }       //getter method

    public void setGuess(char x) {          //setter method
        for (int i = 0; i < movieLength; i++) {
            if (movie.charAt(i) == x) {
                guess[i] = movie.charAt(i);
            }
        }
    }

    public boolean winCheck()           //a method to check if the game has been won by the user or not
    {
        for (int i = 0; i < movieLength; i++) {
            if (guess[i] == '_')
                return false;
        }

        return true;
    }

    public boolean alreadyGuessed(char x)       //a method that checks if the input by user was given by him/her before
    {
        if (correctGuesses.contains(x))
            return true;
        else
            return false;
    }

    public boolean guessCheck(char x)           //a method that checks if the char gussed by the user was correct or not
    {
        boolean flag = false;
        if (alreadyGuessed(x)) {
            System.out.println("Already guessed");
            return true;
        } else {
            for (int i = 0; i < movieLength; i++) {
                if (movie.charAt(i) == x) {
                    this.setGuess(x);
                    flag = true;
                    correctGuesses.add(x);
                    break;
                }
            }

            return flag;
        }
    }
}