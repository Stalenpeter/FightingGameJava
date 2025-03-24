import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements ActionListener {
    private Timer timer;
    private Player player;
    private Opponent opponent;
    private Background background;
    
    public GamePanel() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.BLACK);
        
        player = new Player(100, 400); // Initial position
        opponent = new Opponent(500, 400); // Initial position
        background = new Background("res/images/background/street.png");
        
        addKeyListener(new KeyboardInput(player));
        setFocusable(true);
        
        timer = new Timer(16, this); // Approx 60 FPS
        timer.start();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        player.update();
        opponent.update();
        checkCollisions();
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        background.draw(g);
        player.draw(g);
        opponent.draw(g);
    }
    
    private void checkCollisions() {
        // Simple collision check for punches
        if (player.getHealth() > 0 && player.isPunching() && player.getX() + 50 > opponent.getX() && player.getX() < opponent.getX() + 50) {
            opponent.takeDamage(10); // Deal 10 damage
        }
        
        // Check if opponent attacks back
        if (opponent.getHealth() > 0 && opponent.getX() + 50 > player.getX() && opponent.getX() < player.getX() + 50) {
            player.takeDamage(10); // Opponent deals 10 damage
        }
    }
}
