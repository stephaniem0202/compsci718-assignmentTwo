package users;

import helper_classes.Utilities;
import interfaces.User;

import java.util.*;

public class ComputerEasy extends User {

    public ComputerEasy() {
        super("Computer (easy)");
    }

    @Override
    public ArrayList<Integer> giveGuess(int codeLength, int roundNumber, boolean useFile) {
        return Utilities.randomDigits(codeLength);
    }

}
