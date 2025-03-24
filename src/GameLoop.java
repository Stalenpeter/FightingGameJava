public class GameLoop {
    private static GameLoop instance;

    private GameLoop() {}

    public static GameLoop getInstance() {
        if (instance == null) {
            instance = new GameLoop();
        }
        return instance;
    }

    public void start() {
        while (true) {
            update();
            render();
        }
    }

    public void update() {
        // Update game logic (e.g., update player, enemies, etc.)
    }

    public void render() {
        // Render the game objects (e.g., draw player, background, etc.)
    }
}
