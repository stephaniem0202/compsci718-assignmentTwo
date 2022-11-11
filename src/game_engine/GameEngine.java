package game_engine;

import interfaces.User;
import users.ComputerEasy;
import users.ComputerHard;
import users.ComputerMedium;

import java.util.ArrayList;


public class GameEngine {
    private int maxNumberRounds = 7;
    private String difficulty;
    private int codeLength = 4;


    public int getMaxNumberRounds() {
        return maxNumberRounds;
    }

    public void setMaxNumberRounds(int maxNumberRounds) {

        this.maxNumberRounds = maxNumberRounds;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {

        this.difficulty = difficulty;
    }

    public int getCodeLength() {
        return codeLength;
    }

    public void setCodeLength(int codeLength) {

        this.codeLength = codeLength;
    }


    public User makeComputer(String difficulty) {
        switch (difficulty) {
            case "MEDIUM":
                return new ComputerMedium();
            case "HARD":
                return new ComputerHard();
            default:
                return new ComputerEasy();

        }
    }

    public ArrayList<Integer> calculateResponse(ArrayList<Integer> code, ArrayList<Integer> guess) {
        int bulls = 0;
        int cows = 0;

        for (int i = 0; i < codeLength; i++) {
            if (code.get(i).equals(guess.get(i))) {
                bulls++;
            }
        }

        for (int i = 0; i < codeLength; i++) {
            for (int j = 0; j < codeLength; j++) {
                if (code.get(i).equals(guess.get(j)) && (i != j)) {
                    cows++;
                }
            }
        }

        ArrayList<Integer> response = new ArrayList<>();
        response.add(bulls);
        response.add(cows);

        return response;
    }

    public boolean checkWinner(ArrayList<Integer> response, int codeLength) {
        return response.get(0) == codeLength;
    }

}

