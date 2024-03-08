package efs.task.syntax;

public class GuessNumberGame {

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
        //TODO: Implement the constructor
    }

    public void play() {
        //TODO: Implement the method that executes the game session
    }
}
