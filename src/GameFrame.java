import javax.swing.*;

public class GameFrame extends JFrame {
    public GameFrame() {
        setTitle("JFighter Rebuilt");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(800, 600);
        
        GamePanel gamePanel = new GamePanel();
        add(gamePanel);
        
        setVisible(true);
    }
}
