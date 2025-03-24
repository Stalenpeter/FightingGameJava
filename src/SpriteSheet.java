import java.awt.Image; // Import Image class
import java.awt.image.BufferedImage; // Import BufferedImage class
import javax.imageio.ImageIO; // Import ImageIO for reading the images
import java.io.File; // Import File for reading files

public class SpriteSheet {
    private BufferedImage spriteSheet; // Holds the sprite sheet
    private int frameWidth; // The width of each frame
    private int frameHeight; // The height of each frame
    private int frames; // Number of frames in the sprite sheet

    // Constructor
    public SpriteSheet(String path, int frameWidth, int frameHeight) {
        spriteSheet = loadImage(path); // Load the sprite sheet image
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.frames = spriteSheet.getWidth() / frameWidth; // Calculate the number of frames
    }

    // Load the sprite sheet image from file
    private BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(new File(path)); // Load the image file into BufferedImage
        } catch (Exception e) {
            e.printStackTrace(); // Print any error to the console
        }
        return null; // Return null in case of error
    }

    // Extract frames from the sprite sheet
    public Image[] getFrames() {
        Image[] framesArray = new Image[frames]; // Create an array to hold the frames
        for (int i = 0; i < frames; i++) {
            framesArray[i] = spriteSheet.getSubimage(i * frameWidth, 0, frameWidth, frameHeight); // Crop each frame from the sprite sheet
        }
        return framesArray; // Return the array of frames
    }
}
