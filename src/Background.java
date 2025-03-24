import java.awt.*;
import javax.swing.ImageIcon;

public class Background {
    private Image bgImage;
    
    public Background(String path) {
        bgImage = new ImageIcon(path).getImage();
    }
    
    public void draw(Graphics g) {
        g.drawImage(bgImage, 0, 0, 800, 600, null);
    }
}
