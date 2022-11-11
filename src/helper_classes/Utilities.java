package helper_classes;

import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Utilities {

    public static void computerChat(String str) {
        System.out.println(str);
    }

    public static void computerChatDelay(String str, int secondsDelay) {
        try {
            System.out.println(str);
            TimeUnit.SECONDS.sleep(secondsDelay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Integer> randomDigits(int codeLength) {
        ArrayList<Integer> numList = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 0));
        Collections.shuffle(numList, new Random());
        ArrayList<Integer> computerCode = new ArrayList<>();

        for (int i = 0; i < codeLength; i++) {
            computerCode.add(numList.get(i));
        }
        return computerCode;
    }

    public static boolean isValidInput(String input, int codeLength) {

        if (input.matches("[0-9]+")) {
            if (Utilities.isUnique(input)) {
                return input.length() == codeLength;
            }
        }
        return false;
    }

    public static boolean isUnique(String input) {
        Set<Character> set = new HashSet<>();

        char[] characters = input.toCharArray();
        for (Character c : characters) {
            if (!set.add(c)) {
                return false;
            }
        }
        return true;
    }

    public static ArrayList<String> makeGuessArray() {
        ArrayList<String> array = new ArrayList<>();
        for (int i = 123; i < 10000; i++) {
            DecimalFormat df = new DecimalFormat("0000");
            String str = df.format(i);
            if (Utilities.isUnique(str)) {
                array.add(str);
            }
        }
        return array;
    }

    public static int increaseRoundNumber(int number) {
        int num = number;
        num++;
        return num;
    }

    public static String getStringOfArray(ArrayList<Integer> arr) {
        StringBuilder str = new StringBuilder();
        for (Integer num : arr) {
            str.append(num);
        }
        return String.valueOf(str);
    }

    //trim array
    public static ArrayList<String> trimGuessArray(ArrayList<Integer> rs, ArrayList<Integer> gs, ArrayList<String> guessArray) {
        String response = Utilities.getStringOfArray(rs);
        String guess = Utilities.getStringOfArray(gs);

        deleteNoBullNoCow(response, guess, guessArray);
        deleteNoBull(response, guess, guessArray);
        deleteIfBull(response, guess, guessArray);
        deleteNoCow(response, guess, guessArray);
        deleteIfCow(response, guess, guessArray);

        return guessArray;
    }

    //if no bulls or cows delete all numbers in list containing guess numbers
    private static void deleteNoBullNoCow(String response, String guess, ArrayList<String> array) {
        if (response.equals("00")) {
            ArrayList<String> found = new ArrayList<>();
            for (String str : array) {
                if (str.contains(String.valueOf(guess.charAt(0)))) {
                    found.add(str);
                }
                if (str.contains(String.valueOf(guess.charAt(1)))) {
                    found.add(str);
                }
                if (str.contains(String.valueOf(guess.charAt(2)))) {
                    found.add(str);
                }
                if (str.contains(String.valueOf(guess.charAt(3)))) {
                    found.add(str);
                }
            }
            array.removeAll(found);
        }
    }

    //if no bulls delete all numbers with bulls compared to guess
    private static void deleteNoBull(String response, String guess, ArrayList<String> array) {
        if (response.charAt(0) == '0') {
            ArrayList<String> found = new ArrayList<>();
            for (String str : array) {
                if (str.charAt(0) == (guess.charAt(0)) || str.charAt(1) == (guess.charAt(1))
                        || str.charAt(2) == (guess.charAt(2)) || str.charAt(3) == (guess.charAt(3))) {
                    found.add(str);
                }
            }
            array.removeAll(found);
        }
    }

    //if bulls delete any numbers that don't have one number aligned with guess
    private static void deleteIfBull(String response, String guess, ArrayList<String> array) {
        if (Integer.parseInt(String.valueOf(response.charAt(0))) > 0) {
            ArrayList<String> found = new ArrayList<>();
            for (String str : array) {
                if (str.charAt(0) != (guess.charAt(0)) && str.charAt(1) != (guess.charAt(1))
                        && str.charAt(2) != (guess.charAt(2)) && str.charAt(3) != (guess.charAt(3))) {
                    found.add(str);
                }
            }
            array.removeAll(found);
        }
    }

    //if no cows delete any numbers with guess numbers in cow position
    private static void deleteNoCow(String response, String guess, ArrayList<String> array) {
        if (response.charAt(1) == '0') {
            ArrayList<String> found = new ArrayList<>();
            for (String str : array) {
                if (str.charAt(0) == (guess.charAt(1)) || str.charAt(0) == (guess.charAt(2)) || str.charAt(0) == (guess.charAt(3))) {
                    found.add(str);
                }
                if (str.charAt(1) == (guess.charAt(0)) || str.charAt(1) == (guess.charAt(2)) || str.charAt(1) == (guess.charAt(3))) {
                    found.add(str);
                }
                if (str.charAt(2) == (guess.charAt(0)) || str.charAt(2) == (guess.charAt(1)) || str.charAt(2) == (guess.charAt(3))) {
                    found.add(str);
                }
                if (str.charAt(3) == (guess.charAt(0)) || str.charAt(3) == (guess.charAt(1)) || str.charAt(3) == (guess.charAt(2))) {
                    found.add(str);
                }
            }
            array.removeAll(found);
        }
    }

    //if cows delete any numbers that don't have at least one number in cow position compared with guess
    private static void deleteIfCow(String response, String guess, ArrayList<String> array) {

        int i = Integer.parseInt(String.valueOf(response.charAt(1)));

        if (i > 0) {
            ArrayList<String> found = new ArrayList<>();
            for (String str : array) {
                int count = 0;

                if (str.charAt(0) == (guess.charAt(1)) || str.charAt(0) == (guess.charAt(2)) || str.charAt(0) == (guess.charAt(3))) {
                    count++;
                }
                if (str.charAt(1) == (guess.charAt(0)) || str.charAt(1) == (guess.charAt(2)) || str.charAt(1) == (guess.charAt(3))) {
                    count++;
                }
                if (str.charAt(2) == (guess.charAt(0)) || str.charAt(2) == (guess.charAt(1)) || str.charAt(2) == (guess.charAt(3))) {
                    count++;
                }
                if (str.charAt(3) == (guess.charAt(0)) || str.charAt(3) == (guess.charAt(1)) || str.charAt(3) == (guess.charAt(2))) {
                    count++;
                }

                if (count < i) {
                    found.add(str);
                }
            }
            array.removeAll(found);
        }
    }

}
