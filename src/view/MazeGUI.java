package view;

import exceptions.MazeMalformedException;
import exceptions.MazeSizeMissmatchException;
import exceptions.NoSuchElementException;
import io.FileLoader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;

/**
 * The MazeGui class is main GUI for the maze, handles displaying both the MazeView panel
 * and the loadFile menu.
 */
public class MazeGUI extends JFrame implements ActionListener {
    /**
     * The MazeView component for displaying the maze.
     */
    private final MazeView mazeView;

    /**
     * The JMenuItem component for displaying the load file menu.
     */
    private final JMenuItem load;

    /**
     * Constructs a MazeGUI with the specified maze layout.
     *
     * @param maze The initial maze layout represented as a 2D char array.
     * @throws NoSuchElementException If a required element is not found in the maze.
     *
     */
    public MazeGUI(char[][] maze) throws NoSuchElementException {
        mazeView = new MazeView(maze);

        this.add(mazeView);
        this.setSize(420, 420);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        this.setVisible(true);

        JMenu loadFile = new JMenu("File");
        menuBar.add(loadFile);
        load = new JMenuItem("Load File");
        load.addActionListener(this);
        loadFile.add(load);

    }

    /**
     * Handles the actionPerformed event, triggered when the "Load File" menu item is selected.
     *
     * @param e The ActionEvent object representing the action performed.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == load){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("C:\\Users\\frank\\CSSE2002" +
                    "\\assignment\\mazeNavigator\\src"));
            int response = fileChooser.showOpenDialog(this);

            if (response == JFileChooser.APPROVE_OPTION) {
                // Get the selected file
                File selectedFile = fileChooser.getSelectedFile();
                try {
                    FileLoader fileLoader = new FileLoader();
                    char[][] maze = fileLoader.load(selectedFile.getAbsolutePath());

                    // Update the maze GUI with the new maze
                    mazeView.updateMaze(maze);
                } catch (FileNotFoundException | MazeSizeMissmatchException | MazeMalformedException
                         | NoSuchElementException | InputMismatchException ex) {
                    JOptionPane.showMessageDialog(this, "Error " +
                                    "loading the maze: Try a different file", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }

        }
    }
}

