package io;

import exceptions.MazeMalformedException;
import exceptions.MazeSizeMissmatchException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the io.FileLoader class using Junit
 */
class FileLoaderTest {
    /**
     * The instance used for loading maze files in the test cases.
     */
    private FileLoader fileLoader;

    /**
     * Initialises a new FileLoader object before each test.
     */

    @BeforeEach
    public void setUp(){
        fileLoader = new FileLoader();

    }

    /**
     * Tests the loading of a valid maze file.
     *
     * @throws FileNotFoundException        If the specified file is not found.
     * @throws MazeSizeMissmatchException   If the loaded maze's rows or columns do not match
     *                                      the specified size.
     * @throws MazeMalformedException       If the loaded maze is malformed (e.g., even number of
     *                                      rows/columns).
     * @ensure maze != null && maze.length % 2 != 0 && maze[0].length % 2 != 0 &&
     *         maze.length % 2 > 0 && maze[0].length % 2 > 0
     */
    @Test
    public void loadValidMaze() throws FileNotFoundException, MazeSizeMissmatchException,
            MazeMalformedException {

        char[][] maze = fileLoader.load("test/mazeNavTest/io/validMaze.txt");
        File file = new File("test/mazeNavTest/io/validMaze.txt");
        Scanner scanner = new Scanner(file);

        assertNotNull(maze);
        assertTrue(maze.length > 0);
        assertTrue(maze[0].length > 0);

        int rowNum = scanner.nextInt();
        int colNum = scanner.nextInt();

        // to check if row and column numbers are odd
        assertTrue(rowNum % 2 !=0 | colNum % 2 !=0);

        // to check if row and columns are not mismatched
        assertEquals(rowNum, maze.length);
        assertEquals(colNum, maze[0].length);
    }

    /**
     * Tests loading an invalid and malformed maze file.
     *
     * @ensure The appropriate exception is thrown for malformed mazes.
     */
    @Test
    public void loadInvalidMalformedMaze() {

        // Test for when both row and column is even
        assertThrows(MazeMalformedException.class, () -> {
                fileLoader.load("test/mazeNavTest/io/InvalidMazeMalformed.txt");});

        // Test for when only number of rows are even
        assertThrows(MazeMalformedException.class, () -> {
                fileLoader.load("test/mazeNavTest/io/InvalidMazeMalformedEvenRow.txt");});

        // Test for when only number of columns are even
        assertThrows(MazeMalformedException.class, () -> {
                fileLoader.load("test/mazeNavTest/io/InvalidMazeMalformedEvenCol.txt");});
    }

    /**
     * Tests loading an invalid and miss matched maze file.
     *
     * @ensure The appropriate exception is thrown for mismatched mazes.
     */
    @Test
    public void loadInvalidMissMatchedMaze () {
        // Test for if the number of columns given is different to the actual number of columns
        assertThrows(MazeSizeMissmatchException.class, () -> {
            fileLoader.load("test/mazeNavTest/io/mazeMismatchedCols.txt");

        // Test for if the number of rows given is different to the actual number of columns
        assertThrows(MazeSizeMissmatchException.class, () -> {
            fileLoader.load("test/mazeNavTest/io/mazeMismatchedRows.txt");
            });

        // Test for if the number of rows is different to the number given in the first line
        assertThrows(MazeSizeMissmatchException.class, () -> {
            fileLoader.load("test/mazeNavTest/io/mazeMismatchedRows2.txt");
            });

        // Test for if the number of columns is different to the number given in the first line
        assertThrows(MazeSizeMissmatchException.class, () -> {
            fileLoader.load("test/mazeNavTest/io/mazeMismatchedCols2.txt");
            });
        });
    }

    /**
     * Test for when a file that doesn't exist is loaded
     * @ensure FileNotFoundException
     */
    @Test
    public void loadNonexistentFile() {
        assertThrows(FileNotFoundException.class, () -> {
            fileLoader.load("nonexistent_file.txt");
        });
    }

    /**
     * Test for when a file has illegal arguments that is part of the maze components
     * @ensure IllegalArgumentException
     */
    @Test
    public void loadIllegalArgumentInMaze() {
        assertThrows(IllegalArgumentException.class, () -> {
            fileLoader.load("test/mazeNavTest/io/illegalElementMaze.txt");
        });
    }
}