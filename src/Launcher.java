import exceptions.MazeMalformedException;
import exceptions.MazeSizeMissmatchException;
import exceptions.NoSuchElementException;
import io.FileLoader;
import model.Direction;
import model.MazeGame;
import view.MazeGUI;
import view.TextDisplay;

import java.io.FileNotFoundException;

/**
 * The Launcher class runs the maze navigation application.
 */
public class Launcher {

    /**
     * Main method to start the maze navigator application.
     *
     * @param args The command-line arguments.
     *             Usage: "GUI maze.txt" to start the GUI with a specific maze file, or provide the
     *             maze file as the first argument to run the text-based version.
     *
     * @throws FileNotFoundException      If the specified maze file is not found.
     * @throws MazeSizeMissmatchException If the maze has mismatched row or column sizes.
     * @throws MazeMalformedException     If the maze is malformed (e.g., even number
     *                                    of rows/columns).
     * @throws NoSuchElementException     If a required element is not found in the maze.
     * @require args != null
     */
    public static void main(String[] args) throws FileNotFoundException,
            MazeSizeMissmatchException, MazeMalformedException, NoSuchElementException {
        FileLoader fileLoader = new FileLoader();

        // GUI display if GUI is entered in terminal line
        if (args[0].equals("GUI")) {
            try{
                char[][] maze = fileLoader.load(args[1]);
                new MazeGUI(maze);
            } catch (ArrayIndexOutOfBoundsException e){
                throw new ArrayIndexOutOfBoundsException("Please enter a maze File of the " +
                        ".txt format after GUI");
            }
        }
        // Text-based display in terminal if GUI is not entered in terminal line
        else {
            char[][] maze = fileLoader.load(args[0]);
            MazeGame mazeGame = new MazeGame(maze);
            TextDisplay textdisplay = new TextDisplay(mazeGame);

            while (!mazeGame.isGameOver()){
                textdisplay.displayMaze();
                Direction move = mazeGame.getInputHandler().readInput();
                mazeGame.moveGoose(move);
            }
        }
    }

}