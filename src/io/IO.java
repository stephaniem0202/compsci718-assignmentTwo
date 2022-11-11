package io;

import dto.Round;
import helper_classes.Keyboard;
import helper_classes.Utilities;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IO {

    public static String findFile() {

        boolean isFile = false;
        String fileName = "";

        while (!isFile) {
            String name = Keyboard.readInput();
            File file = new File(name);

            if (name.equals("NO")){
                fileName = name;
                break;
            }

            if (file.exists()) {
                fileName = name;
                isFile = true;
            } else {
                System.out.println("File does not exist. Please try another file name or type NO to continue without using a file");
            }
        }
        return fileName;
    }

    public static ArrayList<String> readFile(String input) {

        boolean file = false;
        ArrayList<String> newFile = new ArrayList<>();

        while (!file) {
            try (BufferedReader reader = new BufferedReader(new FileReader(input))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    newFile.add(line);
                }
                reader.close();
                file = true;
            } catch (IOException e) {
                Utilities.computerChat("I can not find the file. Please try another file name");
            }
        }
        return newFile;
    }

    public static String createFile() {

        Utilities.computerChat("What would you like to name your file?");
        boolean isFile = false;
        String fileName = "";

        while (!isFile) {
            try {
                String name = Keyboard.readInput();
                File file = new File(name);
                if (file.createNewFile()) {
                    fileName = name;
                    isFile = true;

                } else {
                    System.out.println("File already exists. Please try another file name.");
                }
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        return fileName;
    }

    public static void writeFile(String file, ArrayList<Integer> humanCode, ArrayList<Integer> computerCode, ArrayList<Object> game) {

        ArrayList<Integer> marker = new ArrayList<>(List.of(0, 0, 0, 0));
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            writer.write("BULLS AND COWS GAME");
            writer.write(System.lineSeparator());
            writer.write("Your secret code " + humanCode);
            writer.write(System.lineSeparator());
            writer.write("Computer secret code " + computerCode);
            writer.write(System.lineSeparator());

            for (Object o : game) {
                Round round = (Round) o;

                if (round.getComputerGuess().equals(marker)) {
                    writer.write("TURN " + round.getRoundNumber());
                    writer.write(System.lineSeparator());
                    writer.write("You guessed " + round.getHumanGuess() + "  Scoring " + round.getHumanResponse().get(0) + " Bulls and " + round.getHumanResponse().get(1) + " Cows");
                    writer.write(System.lineSeparator());
                    break;
                } else {
                    writer.write("TURN " + round.getRoundNumber());
                    writer.write(System.lineSeparator());
                    writer.write("You guessed " + round.getHumanGuess() + "  Scoring " + round.getHumanResponse().get(0) + " Bulls and " + round.getHumanResponse().get(1) + " Cows");
                    writer.write(System.lineSeparator());
                    writer.write("Computer guessed " + round.getComputerGuess() + "  Scoring " + round.getComputerResponse().get(0) + " Bulls and " + round.getComputerResponse().get(1) + " Cows");
                    writer.write(System.lineSeparator());
                }
            }
            Round round = (Round) game.get(game.size() - 1);
            writer.write("WINNER: " + round.getWinner());

            writer.close();

        } catch (IOException ex) {
            System.out.println("An error occurred and the file could not be saved");
            ex.printStackTrace();
        }

        Utilities.computerChat("Your file " + file + " is written");
    }

}
