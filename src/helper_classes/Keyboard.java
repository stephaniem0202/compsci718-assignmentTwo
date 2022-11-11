package helper_classes;

import java.io.IOException;
import java.util.*;

public class Keyboard {

    private static final Scanner in = new Scanner(System.in);
    private static boolean redirected = false;

    public static String readInput() {

        try {
            if (!redirected) {
                redirected = System.in.available() != 0;
            }
        } catch (IOException e) {
            System.err.println("An error has occurred in the helpers.Keyboard constructor.");
            e.printStackTrace();
            System.exit(-1);
        }

        try {
            String input = in.nextLine();
            if (redirected) {
                System.out.println(input);
            }
            return input;
        } catch (NoSuchElementException e) {
            return null; // End of file
        } catch (IllegalStateException e) {
            System.err.println("An error has occurred in the helpers.Keyboard.readInput() method.");
            e.printStackTrace();
            System.exit(-1);
        }
        return null;
    }


    //additional code added by Steph Morton below this line -----

    public static String readInputCaps(){
        return Keyboard.readInput().toUpperCase();
    }

    public static String introduction() {
        Utilities.computerChatDelay("Hello human", 1);
        String input = Keyboard.askQuestionOpen("What is your name? ");
        System.out.println("Hello " + input);
        return input;
    }

    public static String askQuestionStr(String question, ArrayList<String> answers) {
        System.out.println(question);
        boolean correctInput = false;
        String answer = "";
        while (!correctInput) {
            String input = Keyboard.readInputCaps();
            if (answers.contains(input)) {
                answer = input;
                correctInput = true;

            } else {
                System.out.println("incorrect input, please return: ");
                for (String e : answers) {
                    System.out.println(e);
                }
            }
        }
        return answer;
    }

    public static boolean askQuestionBool(String question, ArrayList<String> answers) {
        return askQuestionStr(question, answers).equals("YES");
    }

    public static int askQuestionInt(String question, int lowerLimit, int upperLimit) {
        System.out.println(question);
        int input = 0;
        while (input < lowerLimit || upperLimit < input) {
            try {
                int answer = Integer.parseInt(Keyboard.readInput());
                if (lowerLimit < answer && answer < upperLimit) {
                    input = answer;
                    break;
                }else{
                    System.out.println("Please return a number from " + lowerLimit + " to " + upperLimit);
                }
            } catch (NumberFormatException e) {
                System.out.println("Incorrect input, please enter a NUMBER");
            }
        }
        return input;
    }

    public static String askQuestionOpen(String str) {
        System.out.println(str);
        return Keyboard.readInput();
    }

}
