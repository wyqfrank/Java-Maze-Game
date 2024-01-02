package model;

import exceptions.NoSuchElementException;
import io.FileLoader;

/**
 * The Maze class represents a maze and its layout and visited positions.
 */
public class Maze {

    /**
     * 2D char array that represents the maze layout.
     */
    private final char[][] maze;

    /**
     * 2D integer array that represents the visited positions of the player.
     */
    private final int[][] visitedPositions;

    /**
     * Constructs a Maze with the given maze layout.
     *
     * @param maze The 2D array representing the maze layout.
     */
    public Maze(char[][] maze) {
        this.maze = maze;
        this.visitedPositions = new int[maze.length][maze[0].length];
    }

    /**
     * Gets the maze layout.
     *
     * @return The 2D array representing the maze layout.
     * @pure
     */
    public char[][] getMaze() {
        return maze;
    }

    /**
     * Gets the visited positions in the maze.
     *
     * @return The 2D array representing visited positions.
     * @pure
     */
    public int[][] getVisitPositions() {
        return visitedPositions;
    }

    /**
     * Increments the visit count for a specific position in the maze.
     *
     * @param position The position to increment visit count for.
     * @throws IllegalArgumentException If the position is outside the maze bounds.
     * @require position != null;
     * @ensure ensures visitedPositions[position.getRow()][position.getCol()] >
     *         \old(visitedPositions[position.getRow()][position.getCol()]);
     */
    public void incrementVisitCount (Position position) {
        int row = position.getRow();
        int col = position.getCol();

        if (position.isTraversable(maze)) {
            visitedPositions[row][col]++;
        } else {
        throw new IllegalArgumentException("Position is outside of the maze bounds.");}
    }

    /**
     * Finds the starting position 'S' in the maze.
     *
     * @return The starting position in the maze.
     * @throws NoSuchElementException If the 'S' start position is not found in the maze.
     * @ensure \result != null;
     */

    public Position findStartPosition() throws NoSuchElementException{
        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze[0].length; col++) {
                if (maze[row][col] == FileLoader.START) {
                    return new Position(row, col);
                }
            }
        }
        throw new NoSuchElementException("Start position 'S' not found in the maze.");
    }
}
