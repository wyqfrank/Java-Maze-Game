package view;

import exceptions.NoSuchElementException;
import io.FileLoader;
import model.Direction;
import model.MazeGame;
import model.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The MazeView class provides a panel view of the maze.
 */
public class MazeView extends JPanel implements KeyListener{
    /**
     * MazeGame instance associated with this MazeView, providing access to the current maze state.
     */
    private MazeGame mazeGame;

    /**
     * constructs a MazeView panel for displaying the maze.
     *
     * @param maze The initial maze layout to display.
     * @throws NoSuchElementException If a required element is not found in the maze.
     * @require maze != null
     */
    public MazeView(char[][] maze) throws NoSuchElementException {
        mazeGame = new MazeGame(maze);

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);


        int DELAY = 1;
        Timer timer = new Timer(DELAY, e -> {repaint(); checkForExit();});
        timer.start();
    }

    /**
     * Overrides the default painting method to render the maze on the panel.
     *
     * @param g The graphics context to use for painting.
     * @require The {@code mazeGame} instance is properly initialised.
     * @ensure The maze and goose are rendered on the panel based on the current game state.
     */
    @Override
    public void paint(Graphics g){
        super.paint(g);

        char[][] maze = mazeGame.getMazeLayout();
        int [][] visitCounts = mazeGame.getVisitedPositions();

        int cellWidth = getWidth() / maze[0].length;
        int cellHeight = getHeight() / maze.length;

        // Draws the maze
        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze[0].length; col++) {
                Color color;
                switch (maze[row][col]) {
                    case FileLoader.WALL -> color = Color.LIGHT_GRAY;
                    case FileLoader.PATH -> color = Color.WHITE;
                    case FileLoader.START -> color = Color.GREEN;
                    case FileLoader.END -> color = Color.RED;
                    default -> color = Color.BLUE;
                }
                if (visitCounts[row][col] > 0) {
                    color = Color.CYAN;
                } if (visitCounts[row][col] > 1) {
                    color = Color.BLUE;
                }
                g.setColor(color);
                g.fillRect(col * cellWidth, row * cellHeight, cellWidth, cellHeight);
            }
        }

        // Draw the goose at its current position
        Position goosePosition = mazeGame.getGoosePosition();
        g.setColor(Color.GREEN);
        g.fillRect(goosePosition.getCol() * cellWidth, goosePosition.getRow() * cellHeight,
                cellWidth, cellHeight);
    }

    /**
     * Checks if the goose is on the exit position and displays a congratulation message. If the
     * goose is at the exit position the application will terminate
     *
     * @ensure If the goose is on the exit position, a congratulatory message is displayed,
     *          and the application is terminated.
     */
    private void checkForExit() {
        Position goosePosition = mazeGame.getGoosePosition();
        char[][] maze = mazeGame.getMazeLayout();

        // Check if the goose is on the exit
        if (maze[goosePosition.getRow()][goosePosition.getCol()] == 'E') {
            JOptionPane.showMessageDialog(null, "Congrats! You've reached " +
                    "the exit!");
            System.exit(0); // Terminate the application
        }
    }

    /**
     * Handles key-typed events to move the goose in the maze. This method is called when a key is
     * typed by the user. It interprets the key press and moves the goose accordingly in the maze.
     *
     * @param e the event to be processed
     * @require e != null.
     * @ensure \forall Direction d; d == Direction.LEFT || d == Direction.UP || d == Direction.DOWN
     *         || d == Direction.RIGHT;
     *         (e.getKeyChar() == d.getKey()) ==>
     *         (mazeGame.getGoosePosition() == \old(mazeGame.getGoosePosition()).setPosition(d)))
     */
    @Override
    public void keyTyped(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'a' -> mazeGame.moveGoose(Direction.LEFT);
            case 'w' -> mazeGame.moveGoose(Direction.UP);
            case 's' -> mazeGame.moveGoose(Direction.DOWN);
            case 'd' -> mazeGame.moveGoose(Direction.RIGHT);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    /**
     * Updates the displayed maze with a new layout.
     *
     * @param newMaze The new maze layout to display.
     * @throws NoSuchElementException If a required element is not found in the maze.
     * @require newMaze != null
     * @ensure mazeGame == \old(mazeGame) && mazeGame.getMazeLayout() == newMaze
     */
    public void updateMaze(char[][] newMaze) throws NoSuchElementException {
        mazeGame = new MazeGame(newMaze);
        repaint();
    }

}
