package users;

import helper_classes.Keyboard;
import helper_classes.Utilities;
import interfaces.User;
import java.util.ArrayList;


public class Human extends User {

    private boolean useFile = false;

    private ArrayList<String> file;



    public Human(String name) {
        super(name);
    }



    public boolean getUseFile() {
        return useFile;
    }

    public void setUseFile(boolean useFile) {
        this.useFile = useFile;
    }

    public ArrayList<String> getFile() {
        return file;
    }

    public void setFile(ArrayList<String> file) {
        this.file = file;
    }


    @Override
    public ArrayList<Integer> giveGuess(int codeLength, int roundNumber, boolean useFile) {
        String input = null;
        boolean valid = false;

        while(!valid) {

            if (useFile) {
                if (!this.file.isEmpty()) {
                    String inputGuess = this.file.get(0);
                    this.file.remove(0);
                    if (Utilities.isValidInput(inputGuess, codeLength)) { //Instructions do not explicitly state not to do this ('You may assume that each line of the file contains a valid guess.')
                        input = inputGuess;
                        valid = true;
                    } else {
                        Utilities.computerChat("File has invalid entry on line " + roundNumber + ". You will need change the guess to " + codeLength + " unique digits, and start again");
                    }
                } else {
                    String inputGuess = Keyboard.readInput();
                    if (Utilities.isValidInput(inputGuess, codeLength)) {
                        input = inputGuess;
                        valid =true;
                    } else {
                        Utilities.computerChat("Invalid entry. Type in " + codeLength + " unique digits");
                    }
                }
            } else {
                String inputGuess = Keyboard.readInput();
                if (Utilities.isValidInput(inputGuess, codeLength)) {
                    input = inputGuess;
                    valid =true;
                } else {
                    Utilities.computerChat("Invalid entry. Type in " + codeLength + " unique digits");
                }

            }

        }

        ArrayList<Integer> guess = new ArrayList<>();
        for (char ch : input.toCharArray()) {
            guess.add(Integer.parseInt(String.valueOf(ch)));
        }
        return guess;
    }


}
