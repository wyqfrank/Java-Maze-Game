package controller;

import model.Direction;
import model.MazeGame;

import java.util.Scanner;

/**
 * The InputHandler class provides methods for handling user input for the textDisplay of the game.
 * It reads user input from the console and translates it into game actions.
 */
public class InputHandler {
    /**
     * scanner object used to read inputs
     */
    private final Scanner scanner;
    /**
     *  length of a character needed for a move, specifically of length 1.
     */
    private final int CHAR_LENGTH = 1;

    /**
     * Constructs a new InputHandler with a Scanner for reading user input from the console.
     */
    public InputHandler(){
        scanner = new Scanner(System.in);
    }

    /**
     * Reads user input from the console and returns the corresponding game direction or action.
     * @return The game direction to move the player or a game action.
     *          Returns null if the input is invalid or not recognised.
     * @ensure  \result == null || \result == Direction.UP || \result == Direction.LEFT ||
     *          \result == Direction.DOWN || \result == Direction.RIGHT;
     */
    public Direction readInput() {
        System.out.println("Enter a move (w/a/s/d) or q to quit: ");

        String input = scanner.nextLine();

        if (input.length() == CHAR_LENGTH) {
            char move = input.charAt(0);
            switch (move) {
                case 'w' -> {return Direction.UP;}
                case 'a' -> {return Direction.LEFT;}
                case 's' -> {return Direction.DOWN;}
                case 'd' -> {return Direction.RIGHT;}
                case 'q' -> {MazeGame.setGameOver(true);}
                default -> {System.out.println("Please enter a valid move");}
            }

        }

        return null;
    }

}
