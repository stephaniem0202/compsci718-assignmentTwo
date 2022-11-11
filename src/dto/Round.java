package dto;

import java.util.ArrayList;

public class Round {
    private final ArrayList<Integer> humanGuess;
    private final ArrayList<Integer> computerGuess;
    private final ArrayList<Integer> humanResponse;
    private final ArrayList<Integer> computerResponse;
    private final int roundNumber;
    private final String winner;


    public Round(ArrayList<Integer> humanGuess, ArrayList<Integer> computerGuess, ArrayList<Integer> humanResponse, ArrayList<Integer> computerResponse, int roundNumber, String winner) {
        this.humanGuess = humanGuess;
        this.computerGuess = computerGuess;
        this.humanResponse = humanResponse;
        this.computerResponse = computerResponse;
        this.roundNumber = roundNumber;
        this.winner = winner;
    }

    public ArrayList<Integer> getHumanGuess() {
        return humanGuess;
    }

    public ArrayList<Integer> getComputerGuess() {
        return computerGuess;
    }

    public ArrayList<Integer> getHumanResponse() {
        return humanResponse;
    }

    public ArrayList<Integer> getComputerResponse() {
        return computerResponse;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public String getWinner() {
        return winner;
    }

}
