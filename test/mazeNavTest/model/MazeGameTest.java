package model;

import exceptions.MazeMalformedException;
import exceptions.MazeSizeMissmatchException;
import exceptions.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import io.FileLoader;

/**
 * The MazeGameTest class contains unit tests for the MazeGame class.
 */
class MazeGameTest {
    private MazeGame mazeGame;
    private char[][] validMaze;

    /**
     * Sets up the initial state before each test method execution.
     *
     * @throws MazeMalformedException if the maze is malformed.
     * @throws MazeSizeMissmatchException if the maze has mismatched dimensions.
     * @throws FileNotFoundException if the maze file is not found.
     * @throws NoSuchElementException if a required element is not found in the maze.
     */
    @BeforeEach
    void setUp() throws MazeMalformedException, MazeSizeMissmatchException, FileNotFoundException,
            NoSuchElementException {
        FileLoader fileLoader = new FileLoader();
        validMaze = fileLoader.load("test/mazeNavTest/io/ValidMaze.txt");
        mazeGame = new MazeGame(validMaze);
    }

    /**
     * Tests the setGameOver method of the MazeGame class.
     *
     * @ensure \result == true && \result == false
     */
    @Test
    void setGameOver() {
        MazeGame.setGameOver(true);
        assertTrue(mazeGame.isGameOver());

        MazeGame.setGameOver(false);
        assertFalse(mazeGame.isGameOver());
    }

    /**
     * Tests the isGameOver method of the MazeGame class when the game has not ended.
     *
     * ensure \result == false
     */
    @Test
    void isGameOver() {
        assertFalse(mazeGame.isGameOver());
    }

    /**
     * Tests the isGameOver method of the MazeGame class when the game has ended.
     * ensure \result == true
     */
    @Test
    void isGameOverTrue(){
        // The position of the exit in validMaze
        Position goosePosition = new Position(5,5);

        if (mazeGame.getMazeLayout()[goosePosition.getRow()][goosePosition.getCol()]
                == FileLoader.END) {
            MazeGame.setGameOver(true);
        }
        assertTrue(mazeGame.isGameOver());
    }


    /**
     * Tests the getVisitedPositions method of the MazeGame class.
     *
     * @ensure visitedPositions != null, visitedPositions != null &&
     *         visitedPositions.length == validMaze.length &&
     *         visitedPositions[0].length == validMaze[0].length
     */
    @Test
    void getVisitedPositions() {
        int[][] visitedPositions = mazeGame.getVisitedPositions();
        assertNotNull(visitedPositions);
        assertEquals(validMaze.length, visitedPositions.length);
        assertEquals(validMaze[0].length, visitedPositions[0].length);
    }

    /**
     * Tests the moveGoose method of the MazeGame class with a valid direction.
     * checks if after moving the goose down, its position is updated correctly.
     */
    @Test
    void moveGooseValidDirection(){
        // the goose starts at the position 1, 1
        mazeGame.moveGoose(Direction.DOWN);
        Position goosePosition = mazeGame.getGoosePosition();
        // after moving down goose should be at 2,1
        Position position = new Position(2,1);
        assertEquals(goosePosition.getRow(),position.getRow());
        assertEquals(goosePosition.getCol(),position.getCol());
    }

    /**
     * Tests the moveGoose method of the MazeGame class with an invalid direction.
     * checks if after moving the goose up, its position is updated correctly.
     */
    @Test
    void moveGooseInvalidDirection(){
        mazeGame.moveGoose(Direction.UP);
        // the goose starts at the position 1, 1
        Position goosePosition = mazeGame.getGoosePosition();

        // moving up is an invalid move here as 0, 1 is a wall, therefore position should stay same
        Position position = new Position(1,1);
        assertEquals(goosePosition.getRow(),position.getRow());
        assertEquals(goosePosition.getCol(),position.getCol());
    }

    /**
     * Tests the getMazeLayout method of the MazeGame class.
     *
     * @ensure mazeLayout != null && Arrays.deepEquals(validMaze, mazeLayout)
     */
    @Test
    void getMazeLayout() {
        char[][] mazeLayout = mazeGame.getMazeLayout();
        assertNotNull(mazeLayout);
        assertArrayEquals(validMaze, mazeLayout);
    }

    /**
     * Tests the getInputHandler method of the MazeGame class.
     *
     * @ensure inputHandler != null.
     */
    @Test
    void getInputHandler() {
        assertNotNull(mazeGame.getInputHandler());
    }

    /**
     * Tests the getGoosePosition method of the MazeGame class.
     *
     * @ensure goosePosition != null.
     */
    @Test
    void getGoosePosition() {
        Position goosePosition = mazeGame.getGoosePosition();
        assertNotNull(goosePosition);

    }
}