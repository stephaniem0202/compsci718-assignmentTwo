package dto;

import java.util.ArrayList;

public class Response {
    private ArrayList<Integer> humanResponse;
    private ArrayList<Integer> computerResponse;
    private String winner;


    public ArrayList<Integer> getHumanResponse() {
        return humanResponse;
    }

    public void setHumanResponse(ArrayList<Integer> humanResponse) {
        this.humanResponse = humanResponse;
    }

    public ArrayList<Integer> getComputerResponse() {
        return computerResponse;
    }

    public void setComputerResponse(ArrayList<Integer> computerResponse) {
        this.computerResponse = computerResponse;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
}
