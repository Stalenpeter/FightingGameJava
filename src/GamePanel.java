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
        
        player = new Player(100, 400, this); // Pass this (the GamePanel) to the Player constructor
        opponent = new Opponent(500, 400); // Initial position
        background = new Background("res/images/background/street.png");
        
        addKeyListener(new KeyboardInput(player));
        setFocusable(true);
        
        timer = new Timer(16, this); // Approx 60 FPS
        timer.start();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        player.update(); // Now the player updates itself and can access the opponent
        opponent.update();
        checkCollisions(); // Check for collision between player and opponent
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        background.draw(g);
        player.draw(g);
        opponent.draw(g);

        // Draw the player's powers
        for (Power power : player.getPowers()) {
            power.draw(g);
        }
    }
    
    private void checkCollisions() {
        // Check for power collision with the opponent
        for (Power power : player.getPowers()) {
            power.checkCollision(opponent); // Pass the opponent object directly
        }

        // Simple collision check for punches (existing)
        if (player.getHealth() > 0 && player.isPunching() && player.getX() + 50 > opponent.getX() && player.getX() < opponent.getX() + 50) {
            opponent.takeDamage(10); // Deal 10 damage
        }
        
        // Check if opponent attacks back
        if (opponent.getHealth() > 0 && opponent.getX() + 50 > player.getX() && opponent.getX() < player.getX() + 50) {
            player.takeDamage(10); // Opponent deals 10 damage
        }
    }

    // Getter for opponent (needed in Player.java for power collision check)
    public Opponent getOpponent() {
        return opponent;
    }
}
