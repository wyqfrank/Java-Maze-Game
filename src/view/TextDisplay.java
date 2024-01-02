package view;

import io.FileLoader;
import model.MazeGame;
import model.Position;

/**
 * The TextDisplay class is responsible for displaying the maze in text format when called.
 */
public class TextDisplay {

    /**
     * MazeGame object that containing the logic of the maze game.
     */
    private final MazeGame maze;

    /**
     * Constructs a TextDisplay object for the specified maze.
     *
     * @param maze The MazeGame object representing the maze.
     * @require maze != null
     */
    public TextDisplay(MazeGame maze) {
        this.maze = maze;
    }

    /**
     * Displays the maze with different coloured tiles to represent various elements.
     * Uses ANSI codes for coloured text in the console.
     *
     * @pure
     */
    public void displayMaze() {
        final String CYAN_TILE = "\u001B[36m"+"\u2588" + "\u001B[0m";
        final String GREEN_TILE = "\u001B[32m" + "\u2588" + "\u001B[0m";
        final String RED_TILE = "\u001B[31m" + "\u2588" + "\u001B[0m";
        final String GREY_TILE = "\u001B[90m" + "\u2588" + "\u001B[0m";
        final String BLUE_TILE = "\u001B[34m"+"\u2588" + "\u001B[0m";

        char[][] mazeLayout = maze.getMazeLayout();
        int[][] visitCounts = maze.getVisitedPositions();
        Position goosePosition = maze.getGoosePosition();

        for (int row = 0; row < mazeLayout.length; row++) {
            for (int col = 0; col < mazeLayout[row].length; col++) {
                if (mazeLayout[row][col] == FileLoader.START) {
                    System.out.print(GREEN_TILE);
                }
                else if (row == goosePosition.getRow() && col == goosePosition.getCol()) {
                    System.out.print(GREEN_TILE);
                }
                // backtracking paths represented by blue tiles
                else if (visitCounts[row][col] > 1) {
                    System.out.print(BLUE_TILE);}
                // traversed path represented by cyan tiles
                else if (visitCounts[row][col] > 0) {
                    System.out.print(CYAN_TILE);}
                else if (mazeLayout[row][col] == FileLoader.WALL){
                    System.out.print(GREY_TILE);
                }
                else if (mazeLayout[row][col] == FileLoader.END) {
                    System.out.print(RED_TILE);
                }
                else {
                    System.out.print(mazeLayout[row][col]);
                }
            }

            System.out.println();
        }

    }
}
