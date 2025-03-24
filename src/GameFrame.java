import javax.swing.*;

public class GameFrame extends JFrame {
    public GameFrame() {
        setTitle("JFighter Rebuilt");  // Title of the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Close the window on exit
        setResizable(false);  // Set window to non-resizable
        setSize(800, 600);  // Set window size

        GamePanel gamePanel = new GamePanel();  // Create the game panel
        add(gamePanel);  // Add the game panel to the frame

        setVisible(true);  // Make the frame visible
    }
}
