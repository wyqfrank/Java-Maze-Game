package model;

import io.FileLoader;

/**
 * The Position class represents a position in a maze.
 */
public class Position {
    /**
     * row is the row index of the position, and col is the column index of the position
     */
    private int row, col;

    /**
     * Constructs a Position object with the specified row and column values.
     *
     * @param row The row index of the position.
     * @param col The column index of the position.
     * @require row >= 0 || col >= 0
     */
    public Position(int row, int col) {
        this.col = col;
        this.row = row;
    }

    /**
     * Retrieves the row index of the position.
     *
     * @return The row index.
     * @pure
     */
    public int getRow() {
        return row;
    }

    /**
     * Retrieves the column index of the position.
     *
     * @return The column index.
     * @pure
     */
    public int getCol() {
        return col;
    }

    /**
     * Checks if the position is traversable in the given maze.
     *
     * @param maze 2D character array of the maze.
     * @return True if the position is on a traversable path, false otherwise.
     * @pure
     */
    public boolean isTraversable(char[][] maze){
        boolean validPosition = false;
        int numRows = maze.length;
        int numCols = maze[0].length;

        // Check if position is within the map
        if (row >= 0 && row < numRows && col >= 0 && col < numCols) {
            // Check if the position is on a traversable path
            char cell = maze[row][col];
            if (cell != FileLoader.WALL) {
                validPosition = true;
            }
        }
        return validPosition; 
    }

    /**
     * Moves the position in the specified direction.
     *
     * @param direction The direction in which to move the position.
     * @return A new Position object representing the moved position.
     * @ensure \result != null && Position(newRow, newCol) != \oldPosition(newRow, newCol)
     */
    public Position setPosition(Direction direction){
        int newRow = row;
        int newCol = col;
        if (direction == null) {
            System.out.println("please input a move (w/a/s/d). ");
            return this;
        }
        switch (direction) {
            case UP -> newRow--;
            case DOWN -> newRow++;
            case LEFT -> newCol--;
            case RIGHT -> newCol++;
        }

        return new Position(newRow,newCol);
    }
}
