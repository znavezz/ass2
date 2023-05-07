import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
/**
 * Represents the main game class, which initializes and runs the game.
 */
public class Game {
    //Fields
    private GameEnvironment environment = new GameEnvironment();
    private SpriteCollection sprites = new SpriteCollection();
    private String gameName = "Arkanoid";
    private GUI gui = new GUI(gameName, environment.getWidth(), environment.getHeight());
    private Sleeper sleeper = new Sleeper();
    /**
     * The main entry point of the application.
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
    //Constructors
    /**
     * Constructs a new Game object amd set the environment of the game.
     */
    public Game() {
        setEnvironment(environment);
    }
    //Commands
    /**
     * Adds a collidable object to the game environment.
     * @param c the Collidable object to be added
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }
    /**
     * Adds a sprite object to the game.
     * @param s the Sprite object to be added
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }
    /**
     * Sets the game environment and adds the necessary sprites and collidables.
     * @param environment the GameEnvironment object to be set
     */
    public void setEnvironment(GameEnvironment environment) {
        this.environment = environment;
        sprites.addSprite(environment.getBackGround());
        for (Collidable collidable : environment.getCollidables()) {
            sprites.addSprite((Sprite) collidable);
        }
        environment.getPaddle().setKeyboard(gui.getKeyboardSensor());
    }
    /**
     * Initializes a new game by creating the Blocks, Ball, and Paddle and adding them to the game.
     */
    public void initialize() {

        int blockWidth = 50;
        int blockHeight = 20;
        Color blockColor = new Color(Geometry.RAND.nextInt(100), Geometry.RAND.nextInt(100),
                Geometry.RAND.nextInt(100));
        for (int i = 0; i <= 5; i++) {
            blockColor = new Color(blockColor.getRed() + (i * 10), blockColor.getGreen() + (i * 10),
                    blockColor.getBlue() + (i * 10));
            for (int j = environment.getWidth() - blockWidth - environment.getVerticalBorderWidth();
                 j >= (3 + i) * blockWidth;
                 j -= blockWidth) {
                Block newBlock = new Block(new Rectangle(new Point(j, environment.getHorizontalBorderWidth()
                        + ((7 + i) * blockHeight)),
                        blockWidth, blockHeight), blockColor);
                newBlock.addToGame(this);
            }
        }

        environment.getBalls().addBall(new Ball(environment, new Point(600, 550)));
        environment.getBalls().addBall(new Ball(environment, new Point(550, 550)));
        environment.getBalls().addToGame(this);
    }

    /**
     * Runs the game by starting the animation loop.
     */
    public void run() {
        //...
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
            for (Sprite sprite : sprites.getSprites()) {
                System.out.println(sprite.toString());
            }
        }
    }
    //Queries
    /**
     * Returns the game environment.
     * @return the GameEnvironment object
     */
    public GameEnvironment getEnvironment() {
        return environment;
    }
    /**
     * Returns the GUI object of the game.
     * @return the GUI object
     */
    public GUI getGui() {
        return gui;
    }
}