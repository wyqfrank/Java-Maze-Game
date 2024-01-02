package model;

import controller.InputHandler;
import exceptions.NoSuchElementException;
import io.FileLoader;

/**
 * The MazeGame class represents the game logic for the maze game.
 */
public class MazeGame {

    /**
     * The maze object containing the maze layout and the visited position layout.
     */
    private final Maze maze;

    /**
     * The current position of the goose in the maze
     */
    private Position goosePosition;

    /**
     * Flag to indicate if the game is over or not.
     */
    private static boolean gameOver;

    /**
     * input handler to handle movement of the goose inside the maze
     */
    private final InputHandler inputHandler;

    /**
     * Constructs a MazeGame with the given maze layout.
     *
     * @param maze the 2D character array representing the game layout.
     * @throws NoSuchElementException If the game lacks a valid starting position.
     * @require maze != null && goosePosition != null && inputHandler != null.
     */
    public MazeGame(char[][] maze) throws NoSuchElementException {
        this.maze = new Maze(maze);
        this.goosePosition = this.maze.findStartPosition();
        gameOver = false;
        this.inputHandler = new InputHandler();
    }

    /**
     * Sets if the game is over or not.
     * @param state The state in which the game is in (true for game over, false for otherwise).
     * @ensure gameOver == state
     */
    public static void setGameOver(boolean state) {
        gameOver = state;
    }

    /**
     * checks if game is game over or not
     *
     * @return True if the game is over, false otherwise.
     * @pure
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * Retrieves the 2D array representing visited positions in the maze.
     *
     * @return A 2D integer array of the visited positions.
     * @pure
     */
    public int[][] getVisitedPositions() {
        return maze.getVisitPositions();
    }

    /**
     * Moves the goose in the specified direction.
     * @param direction The direction in which to move the goose.
     * @ensure The goose is moved to a valid position in the maze.
     * @pure
     */
    public void moveGoose(Direction direction) {
        Position newPosition = goosePosition.setPosition(direction);

        // Check if the new position is valid (e.g., not a wall)
        if (newPosition.isTraversable(maze.getMaze())) {
            goosePosition = newPosition;
            maze.incrementVisitCount(goosePosition);
        } else {
            System.out.println("Invalid move!");
        }

        if (maze.getMaze()[goosePosition.getRow()][goosePosition.getCol()] == FileLoader.END) {
            System.out.println("Congratulations! You've reached the exit!");
            gameOver = true;
        }
    }

    /**
     * Retrieves the layout of the maze.
     * @return A 2D character array representing the maze layout.
     * @pure
     */
    public char[][] getMazeLayout() {
        return maze.getMaze();
    }

    /**
     * Retrieves the input handler for the game.
     * @return The InputHandler object.
     * @pure
     */
    public InputHandler getInputHandler(){
        return inputHandler;
    }

    /**
     * Retrieves the current position of the goose in the maze.
     *
     * @return The Position object representing the goose's position.
     * @pure
     */
    public Position getGoosePosition() {
        return goosePosition;
    }
}
