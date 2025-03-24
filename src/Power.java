import java.awt.*;
import javax.swing.ImageIcon;

public class Power {
    private int x, y;
    private int speed = 10; // Speed at which the power moves
    private boolean isVisible; // Flag to check if the power is still visible (active)
    private Image powerImage; // Image for the power

    public Power(int startX, int startY, String imagePath) {
        this.x = startX;
        this.y = startY;
        this.isVisible = true; // Initially, the power is visible
        this.powerImage = new ImageIcon(imagePath).getImage(); // Load the power image
    }

    public void move() {
        x += speed; // Move the power to the right (towards the opponent)
    }

    public void checkCollision(Opponent opponent) {
        // Check if the power hits the opponent
        if (isVisible && x + 50 > opponent.getX() && x < opponent.getX() + 50) {
            opponent.takeDamage(20); // Deal 20 damage to the opponent
            isVisible = false; // Hide the power after it hits the opponent
        }
    }

    public void draw(Graphics g) {
        if (isVisible) {
            g.drawImage(powerImage, x, y, 50, 50, null); // Draw the power image at position (x, y)
        }
    }

    public boolean isVisible() {
        return isVisible; // Return visibility status
    }
}
