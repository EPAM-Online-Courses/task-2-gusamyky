package efs.task.syntax;

import java.util.Random;
import java.util.Scanner;

public class GuessNumberGame {
    public int upperBound;
    public int leftAttempts = 0;
    public int currentAttempt = 0;
    public int numToGuess;
    public int attemptNum;


    //Do not modify main method
    public static void main(String[] args) {
        try {
            GuessNumberGame game = new GuessNumberGame(args.length > 0 ? args[0] : "");
            game.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GuessNumberGame(String argument) {
        try {
            this.upperBound = Integer.parseInt(argument);

            if (upperBound < 1 || upperBound > UsefulConstants.MAX_UPPER_BOUND) {
                System.out.println(UsefulConstants.WRONG_ARGUMENT);
                throw new IllegalArgumentException();
            }
        } catch (NumberFormatException e) {
            System.out.println(UsefulConstants.WRONG_ARGUMENT);
            throw new IllegalArgumentException();
        }
    }

    public static double log2(int n) {
        return Math.log(n) / Math.log(2);
    }

    public void countAttempts() {
        leftAttempts = (int) Math.floor(log2(upperBound)) + 1;
    }

    public String getLeftAttempts() {

        return "[" + "*".repeat(currentAttempt + 1) + ".".repeat(leftAttempts - 1) + "]";
    }

    public boolean readAndCheckNum() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println(UsefulConstants.GIVE_ME+" liczbe");
            attemptNum = Integer.parseInt(scanner.next());
        } catch (NumberFormatException e) {
            System.out.println(UsefulConstants.NOT_A_NUMBER);
            return false;
        }

        if (attemptNum > numToGuess) {
            System.out.println("To " + UsefulConstants.TO_MUCH);
            return false;
        } else if (attemptNum < numToGuess) {
            System.out.println("To " + UsefulConstants.TO_LESS);
            return false;
        } else {

            return true;
        }

    }

    public void generateNumToGuess() {
        Random rand = new Random();
        int n = rand.nextInt(upperBound);
        numToGuess = n + 1;
    }

    public void play() {
        countAttempts();
        generateNumToGuess();
        System.out.println("Zagrajmy. Zgadnij liczbe z zakresu<1,"+upperBound+">");
        while (leftAttempts > 0) {
            System.out.println("Twoje proby: " + getLeftAttempts());
            if (readAndCheckNum()) {
                var secondPart = (currentAttempt + 1) + "- tyle prob zajelo Ci odgadniecie liczby " + numToGuess;
                System.out.println(UsefulConstants.YES);
                System.out.println(UsefulConstants.CONGRATULATIONS + ", " + secondPart);
                break;
            } else {
                leftAttempts--;
                currentAttempt++;
            }
        }
        if (leftAttempts == 0) {
            var secondPart = ", wyczerpales limit prob (" + currentAttempt + ") do odgadniecia liczby " + numToGuess;
            System.out.println(UsefulConstants.UNFORTUNATELY + secondPart);
        }

    }
}
