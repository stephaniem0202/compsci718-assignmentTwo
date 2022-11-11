package main;

import dto.Guess;
import dto.Response;
import dto.Round;
import game_engine.GameEngine;
import io.IO;
import helper_classes.Keyboard;
import helper_classes.Utilities;
import interfaces.User;
import users.Human;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private void start() {
        GameEngine gameEngine = new GameEngine();
        Guess guess = new Guess();
        Response response = new Response();
        ArrayList<Object> game = new ArrayList<>();

        //make human object
        String name = Keyboard.introduction();
        User human = new Human(name);

        //make computer object
        String answer = Keyboard.askQuestionStr("Do you want to play Easy, Medium, or Hard?", new ArrayList<>(List.of("EASY", "MEDIUM", "HARD")));
        gameEngine.setDifficulty(answer);
        User computer = gameEngine.makeComputer(gameEngine.getDifficulty());

        //customise settings
        if (answer.equals("EASY")) {
            boolean customise = Keyboard.askQuestionBool("Do you want to customise other settings?", new ArrayList<>(List.of("YES", "NO")));
            if (customise) {
                int rounds = Keyboard.askQuestionInt("How many rounds do you want?", 1, 1000);
                gameEngine.setMaxNumberRounds(rounds);
                int length = Keyboard.askQuestionInt("What code length do you want?", 1, 10);
                gameEngine.setCodeLength(length);

            }
        }
        boolean inputType = Keyboard.askQuestionBool("Do you want to use a file to input guesses?", new ArrayList<>(List.of("YES", "NO")));
        ((Human) human).setUseFile(inputType);
        if(((Human) human).getUseFile()) {
            Utilities.computerChat("Which file would you like to use?");
            String fileName = IO.findFile();
            if(!fileName.equals("NO")) {
                ArrayList<String> file = IO.readFile(fileName);
                ((Human) human).setFile(file);
            }else{
                ((Human) human).setUseFile(false);
            }
        }

        //set secret codes
        Utilities.computerChat("Give me your secret code. Enter " + gameEngine.getCodeLength() + " different numbers from 0-9. I won't peak, I swear");
        ArrayList<Integer> codeHuman = human.giveGuess(gameEngine.getCodeLength(), guess.getRoundNumber(), false);
        human.setSecretCode(codeHuman);

        ArrayList<Integer> codeComputer = Utilities.randomDigits(gameEngine.getCodeLength());
        computer.setSecretCode(codeComputer);
        Utilities.computerChat("There, I have chosen my numbers now. I hope you are ready!");

        //play
        while (guess.getRoundNumber() < gameEngine.getMaxNumberRounds() + 1) {

            //human
            Utilities.computerChat("\n" + "Guess number "+ guess.getRoundNumber());
            ArrayList<Integer> guessHuman = human.giveGuess(gameEngine.getCodeLength(), guess.getRoundNumber(), ((Human) human).getUseFile());
            guess.setHumanGuess(guessHuman);
            ArrayList<Integer> resultHuman = gameEngine.calculateResponse(computer.getSecretCode(), guessHuman);
            response.setHumanResponse(resultHuman);
            Utilities.computerChat("Bulls = " + response.getHumanResponse().get(0) + "   Cows = " + response.getHumanResponse().get(1));

            //check winner
            if (gameEngine.checkWinner(response.getHumanResponse(), gameEngine.getCodeLength())) {
                Utilities.computerChat("You won!");
                response.setWinner(human.getName() + " Won");
                ArrayList<Integer> marker = new ArrayList<>(List.of(0, 0, 0, 0));
                game.add(new Round(guess.getHumanGuess(), marker, response.getHumanResponse(), response.getComputerResponse(), guess.getRoundNumber(), response.getWinner()));//guess.getComputer() incorrect, but not needed later for writeFile()
                break;
            }

            //computer
            ArrayList<Integer> guessComputer = computer.giveGuess(gameEngine.getCodeLength(), guess.getRoundNumber(), false);
            guess.setComputerGuess(guessComputer);
            Utilities.computerChat("My guess is " + Utilities.getStringOfArray(guess.getComputerGuess()));
            ArrayList<Integer> resultComputer = gameEngine.calculateResponse(human.getSecretCode(), guessComputer);
            response.setComputerResponse(resultComputer);
            Utilities.computerChat("Bulls = " + response.getComputerResponse().get(0) + "   Cows = " + response.getComputerResponse().get(1));

            if (gameEngine.getDifficulty().equals("HARD")) {
                ArrayList<String> arr = Utilities.trimGuessArray(response.getComputerResponse(), guess.getComputerGuess(), computer.getGuessArray());
                computer.setGuessArray(arr);
            }

            //check winner
            if (gameEngine.checkWinner(response.getComputerResponse(), gameEngine.getCodeLength())) {
                Utilities.computerChat("I win");
                response.setWinner(computer.getName() + " Won");
                game.add(new Round(guess.getHumanGuess(), guess.getComputerGuess(), response.getHumanResponse(), response.getComputerResponse(), guess.getRoundNumber(), response.getWinner()));
                break;
            }

            //check winner (rounds)
            if (guess.getRoundNumber() == (gameEngine.getMaxNumberRounds())) {
                Utilities.computerChat("Rounds are up. Looks like we both loose");
                response.setWinner("Draw");
                game.add(new Round(guess.getHumanGuess(), guess.getComputerGuess(), response.getHumanResponse(), response.getComputerResponse(), guess.getRoundNumber(), response.getWinner()));
                break;
            }

            //rounds
            game.add(new Round(guess.getHumanGuess(), guess.getComputerGuess(), response.getHumanResponse(), response.getComputerResponse(), guess.getRoundNumber(), response.getWinner()));
            guess.setRoundNumber(Utilities.increaseRoundNumber(guess.getRoundNumber()));

        }

        //saving the game
        boolean save = Keyboard.askQuestionBool("Do you want to save your game to a text file?", new ArrayList<>(List.of("YES", "NO")));
        if (save) {
            String file = IO.createFile();
            IO.writeFile(file, human.getSecretCode(), computer.getSecretCode(), game);
        }

    }

    public static void main(String[] args) {

        Main ex = new Main();
        ex.start();
    }

}