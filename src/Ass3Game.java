/**
 * The Ass3Game class is the entry point of the game application.
 * It creates a new Game instance, initializes it, and starts the game loop.
 */
public class Ass3Game {
    /**
     * The main method of the Ass3Game class, responsible for creating
     * a new Game instance, initializing it, and starting the game loop.
     * @param args command-line arguments (not used in this application)
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}
