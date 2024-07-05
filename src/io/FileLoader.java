package io;

import  exceptions.MazeMalformedException;
import exceptions.MazeSizeMissmatchException;
import java.io.FileNotFoundException;

import java.io.File;
import java.util.Scanner;
/**
 *  The fileLoader class loads maze files and converts them into 2D char arrays that represent
 *  the maze structure.
 *
 */
public class FileLoader implements FileInterface{
    /**
     * Represents a traversable path in the maze.
     */
    public static final char PATH = ' ';

    /**
     * Represents a traversable path in the maze.
     */
    public static final char DOT = '.';

    /**
     * Represents a wall character in the maze.
     */
    public static final char WALL = '#';

    /**
     * Represents the start point character in the maze.
     */
    public static final char START = 'S';

    /**
     * Represents the end point character in the maze.
     */
    public static final char END = 'E';

    /**
     * Loads a maze given the specific string name of the file and converts it into a 2D char array.
     * @
     * @param filename The path to the maze file.
     * @return char[][] representing the loaded maze.
     * @throws MazeMalformedException If the maze has an invalid structure like even number of
     *                                rows and columns.
     * @throws MazeSizeMissmatchException If the maze's dimensions do not match specified size.
     * @throws IllegalArgumentException If an invalid character is found in the file.
     * @throws FileNotFoundException If the specified file is not found.
     *
     * @require filename != null && filename.length() > 0 && first line has two integers and
     *          nothing else.
     * @ensure \result != null && \result.length > 0 && \result[0].length > 0
     */
    @Override
    public char[][] load(String filename) throws MazeMalformedException, MazeSizeMissmatchException,
            IllegalArgumentException, FileNotFoundException {

        char[][] mazeMap;
        File file = new File(filename);
        try ( Scanner scanner = new Scanner(file);){
            // Reads the number of rows and columns from the first line of the file
            int rowsNum = scanner.nextInt();
            int colsNum = scanner.nextInt();
            if (rowsNum % 2 == 0 | colsNum % 2 == 0) {
                throw  new MazeMalformedException("Maze Malformed, ensure that the number of " +
                        "rows or columns are odd.");
            }
            scanner.nextLine();

            mazeMap = new char[rowsNum][colsNum];

            int actualRows = 0;

            // Read each line of the file
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.length() != colsNum) {
                    throw new MazeSizeMissmatchException("Maze columns do not match with the " +
                            "specified size");
                }
                // Fill the mazeMap with characters from the file
                for (int col = 0; col < colsNum; col++) {
                    if (line.charAt(col) == PATH || line.charAt(col) == DOT ||
                            line.charAt(col) == WALL || line.charAt(col) ==START ||
                            line.charAt(col) == END) {
                        mazeMap[actualRows][col] = line.charAt(col);
                    } else {
                        throw new IllegalArgumentException("Invalid character in file: "+
                                line.charAt(col));
                    }
                }
                actualRows ++;}

            if (actualRows != rowsNum) {
                throw new MazeSizeMissmatchException("Maze rows do not match with the specified " +
                        "size");
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File not found: " + filename);
        }
        return mazeMap;
    }
}
