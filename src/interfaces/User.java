package interfaces;

import java.util.*;

public abstract class User {

    protected String name;
    protected ArrayList<Integer> secretCode;
    protected ArrayList<String> guessArray;


    public User(String name) {
        this.name = name;
    }


    public ArrayList<String> getGuessArray() {
        return guessArray;
    }

    public void setGuessArray(ArrayList<String> guessArray) {
        this.guessArray = guessArray;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Integer> getSecretCode() {
        return secretCode;
    }

    public void setSecretCode(ArrayList<Integer> secretCode) {
        this.secretCode = secretCode;
    }

    public abstract ArrayList<Integer> giveGuess(int codeLength, int roundNumber, boolean useFile);



}
