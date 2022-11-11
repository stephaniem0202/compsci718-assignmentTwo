package dto;

import java.util.ArrayList;


//DTO
public class Guess {

    private ArrayList<Integer> humanGuess;
    private ArrayList<Integer> computerGuess;
    private int roundNumber = 1;


    public ArrayList<Integer> getHumanGuess() {
        return humanGuess;
    }

    public void setHumanGuess(ArrayList<Integer> humanGuess) {
        this.humanGuess = humanGuess;
    }

    public ArrayList<Integer> getComputerGuess() {
        return computerGuess;
    }

    public void setComputerGuess(ArrayList<Integer> computerGuess) {
        this.computerGuess = computerGuess;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }
}
