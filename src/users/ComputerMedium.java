package users;

import helper_classes.Utilities;
import interfaces.User;


import java.util.*;


public class ComputerMedium extends User {
    private final Set<Object> set;

    public ComputerMedium() {
        super("Computer (medium)");
        this.set = new HashSet<>();
    }

    @Override
    public ArrayList<Integer> giveGuess(int codeLength, int roundNumber, boolean useFile) {
        boolean newGuess = false;
        ArrayList<Integer> list = null;
        while (!newGuess) {
            ArrayList<Integer> guess = Utilities.randomDigits(codeLength);
            if (this.set.add(guess)) {
                list = guess;
                newGuess = true;
            }
        }
        return list;
    }

}

















