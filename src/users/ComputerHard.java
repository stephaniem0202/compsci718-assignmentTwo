package users;

import helper_classes.Utilities;
import interfaces.User;

import java.util.*;

import static java.lang.Integer.parseInt;


public class ComputerHard extends User {

    public ComputerHard() {
        super("Computer (hard)");
        this.guessArray = Utilities.makeGuessArray();
    }

    public ArrayList<String> getGuessArray() {
        return guessArray;
    }

    public void setGuessArray(ArrayList<String> array) {
        this.guessArray = array;
    }


    @Override
    public ArrayList<Integer> giveGuess(int codeLength, int roundNumber, boolean useFile) {

        Collections.shuffle(guessArray, new Random());
        String input = guessArray.get(0);
        guessArray.remove(0);

        ArrayList<Integer> guess = new ArrayList<>();
        for (char ch : input.toCharArray()) {
            guess.add(parseInt(String.valueOf(ch)));
        }
        return guess;
    }

}





