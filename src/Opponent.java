import java.awt.*;
import javax.swing.ImageIcon;

public class Opponent {
    private int x, y;
    private int health = 100;  // Initialize health for the opponent
    private Image[] idleFrames;
    private Image[] hitFrames;
    private Image currentImage;
    private int currentFrame = 0;
    private boolean isWalking = false;

    private int frameDelay = 5;  // Delay between frames (Control frame rate)
    private long lastFrameTime = 0;  // Track the time for frame rate control

    // The size of the opponent sprite (e.g., 128x128 for larger sprite size)
    private int spriteWidth = 128;
    private int spriteHeight = 128;

    public Opponent(int x, int y) {
        this.x = x;
        this.y = y;
        loadImages();  // Load images for Bee's animations
    }

    private void loadImages() {
        // Load frames for Bee's idle and hit animations
        SpriteSheet idleSheet = new SpriteSheet("res/images/sprites/bee/idle/idle_state_bee_sheet.png", 64, 64);  
        idleFrames = idleSheet.getFrames();

        SpriteSheet hitSheet = new SpriteSheet("res/images/sprites/bee/hit/hit_state_bee_sheet.png", 64, 64);  
        hitFrames = hitSheet.getFrames();

        currentImage = idleFrames[0];  // Default to the first idle frame
    }

    public void update() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastFrameTime >= frameDelay * 100) {
            if (isWalking) {
                currentImage = idleFrames[currentFrame];
                currentFrame = (currentFrame + 1) % idleFrames.length;
            } else {
                currentImage = idleFrames[0];  // Static idle frame
            }
            lastFrameTime = currentTime;  // Update the last frame time
        }
    }

    public void moveLeft() {
        x -= 3;
        isWalking = true;
    }

    public void moveRight() {
        x += 3;
        isWalking = true;
    }

    public void stopWalking() {
        isWalking = false;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) health = 0;  // Ensure health doesn't go below zero
    }

    public void draw(Graphics g) {
        // Increased the size of Bee's sprite moderately (1.5x size)
        double scale = 1.5; // Scale by 1.5 for moderate increase

        // Draw Bee's sprite at the new scaled size
        g.drawImage(currentImage, x, y, (int)(spriteWidth * scale), (int)(spriteHeight * scale), null);

        // Draw health bar above the opponent
        g.setColor(Color.RED);
        g.fillRect(x, y - 10, health / 2, 5); // Health bar (scaled)
    }

    public int getHealth() {
        return health;
    }

    public int getX() {
        return x;
    }
}
