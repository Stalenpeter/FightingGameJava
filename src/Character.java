import java.awt.*;
import javax.swing.ImageIcon;

public class Character {
    protected int x, y;
    protected int speed;
    protected int health;
    protected Image characterImage;

    public Character(int x, int y, int speed, String imagePath) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.health = 100; // Default health
        this.characterImage = new ImageIcon(imagePath).getImage();
    }

    public void moveLeft() {
        x -= speed;
    }

    public void moveRight() {
        x += speed;
    }

    public void draw(Graphics g) {
        g.drawImage(characterImage, x, y, 50, 50, null); // Draw the character at its current position
    }

    public void takeDamage(int damage) {
        health -= damage;
    }

    public int getHealth() {
        return health;
    }

    public void update() {
        // Update character logic (can be overridden)
    }
}
