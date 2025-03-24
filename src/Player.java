import java.awt.*;
import javax.swing.ImageIcon;
import java.util.ArrayList;

public class Player {
    private int x, y;
    private int speed = 5;
    private int health = 100;
    private Image[] idleFrames;
    private Image[] hitFrames;
    private Image[] shootFrames;
    private Image currentImage;
    private int currentFrame = 0;
    private boolean isWalking = false;
    private boolean isPunching = false;

    private int frameDelay = 5;  // Delay between frames (Control frame rate)
    private long lastFrameTime = 0;  // Track the time for frame rate control

    private int spriteWidth = 128; // Original sprite width
    private int spriteHeight = 128; // Original sprite height

    private ArrayList<Power> powers; // List to keep track of active powers
    private GamePanel gamePanel; // Reference to the GamePanel to access opponent

    public Player(int x, int y, GamePanel gamePanel) { // Added gamePanel parameter
        this.x = x;
        this.y = y;
        this.gamePanel = gamePanel; // Store reference to GamePanel
        this.powers = new ArrayList<>(); // Initialize powers list
        loadImages();  // Load images for animations
    }

    private void loadImages() {
        SpriteSheet idleSheet = new SpriteSheet("res/images/sprites/kree/idle/idle_state_kree_sheet.png", 64, 64);
        idleFrames = idleSheet.getFrames();

        SpriteSheet hitSheet = new SpriteSheet("res/images/sprites/kree/shoot/shoot_state_kree_sheet.png", 64, 64);
        hitFrames = hitSheet.getFrames();

        SpriteSheet shootSheet = new SpriteSheet("res/images/sprites/kree/shoot/shoot_state_kree_sheet.png", 64, 64);
        shootFrames = shootSheet.getFrames();

        currentImage = idleFrames[0];
    }

    public void update() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastFrameTime >= frameDelay * 100) {
            if (isPunching) {
                currentImage = hitFrames[currentFrame];
                currentFrame = (currentFrame + 1) % hitFrames.length;
            } else if (isWalking) {
                currentImage = idleFrames[currentFrame];
                currentFrame = (currentFrame + 1) % idleFrames.length;
            } else {
                currentImage = idleFrames[0];
            }
            lastFrameTime = currentTime;
        }

        // Update powers
        for (int i = 0; i < powers.size(); i++) {
            Power power = powers.get(i);
            power.move();
            power.checkCollision(gamePanel.getOpponent()); // Use GamePanel to get opponent
            if (!power.isVisible()) {
                powers.remove(i); // Remove power if it is no longer visible
                i--; // Adjust index after removal
            }
        }
    }

    public void moveLeft() {
        x -= speed;
        isWalking = true;
    }

    public void moveRight() {
        x += speed;
        isWalking = true;
    }

    public void stopWalking() {
        isWalking = false;
    }

    public void punch() {
        isPunching = true;
    }

    public void stopPunching() {
        isPunching = false;
    }

    public void takeDamage(int damage) {
        health -= damage;
    }

    public void draw(Graphics g) {
        // Increased the size of Kree's sprite moderately (1.5x size)
        double scale = 1.5; // Scale by 1.5 for moderate increase

        // Draw Kree's sprite at the new scaled size
        g.drawImage(currentImage, x, y, (int)(spriteWidth * scale), (int)(spriteHeight * scale), null);

        g.setColor(Color.RED);
        g.fillRect(x, y - 10, health / 2, 5); 

        // Draw all powers
        for (Power power : powers) {
            power.draw(g);
        }
    }

    // Method to shoot power
    public void shootPower() {
        Power power = new Power(x + spriteWidth, y + spriteHeight / 2, "res/images/powers/kree_power.png"); // Power starts from Kree
        powers.add(power);
    }

    // This method returns the list of powers currently active
    public ArrayList<Power> getPowers() {
        return powers; // This returns the list of active powers
    }

    public int getHealth() {
        return health;
    }

    public boolean isPunching() {
        return isPunching;
    }

    public int getX() {
        return x;
    }
}
