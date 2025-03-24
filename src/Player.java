import java.awt.*;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;

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
    
    // The new size for the sprite (width, height)
    private int spriteWidth = 128; // Increased width for better view
    private int spriteHeight = 128; // Increased height for better view
    
    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        loadImages();  // Load images for animations
    }

    private void loadImages() {
        // Load frames for idle, hit, and shoot animations (Kree's sprite sheets)
        SpriteSheet idleSheet = new SpriteSheet("res/images/sprites/kree/idle/idle_state_kree_sheet.png", 64, 64);  // Kree's idle sprite sheet
        idleFrames = idleSheet.getFrames();

        SpriteSheet hitSheet = new SpriteSheet("res/images/sprites/kree/hit/hit_state_kree_sheet.png", 64, 64);  // Kree's hit sprite sheet
        hitFrames = hitSheet.getFrames();

        SpriteSheet shootSheet = new SpriteSheet("res/images/sprites/kree/shoot/shoot_state_kree_sheet.png", 64, 64);  // Kree's shoot sprite sheet
        shootFrames = shootSheet.getFrames();

        // Default to the idle animation frame
        currentImage = idleFrames[0];
    }

    public void update() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastFrameTime >= frameDelay * 100) { // If enough time has passed, change the frame
            if (isPunching) {
                currentImage = hitFrames[currentFrame];
                currentFrame = (currentFrame + 1) % hitFrames.length;
            } else if (isWalking) {
                currentImage = idleFrames[currentFrame];
                currentFrame = (currentFrame + 1) % idleFrames.length;
            } else {
                currentImage = idleFrames[0];  // Static idle frame
            }
            lastFrameTime = currentTime;  // Update the last frame time
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
        // Scale the image to a larger size
        g.drawImage(currentImage, x, y, spriteWidth, spriteHeight, null); // Increased size for better view
        g.setColor(Color.RED);
        g.fillRect(x, y - 10, health / 2, 5); // Health bar
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
