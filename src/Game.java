import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;

public class Game {
    private GameEnvironment environment = new GameEnvironment();
    private SpriteCollection sprites = new SpriteCollection();
    private String gameName = "Arkanoid";
    private GUI gui = new GUI(gameName, environment.getWidth(), environment.getHeight());
    private Sleeper sleeper = new Sleeper();

    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }


    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    // Initialize a new game: create the Blocks and Ball (and Paddle)
    // and add them to the game.
    public void initialize() {
        int blockWidth = 75;
        int blockHeight = 25;
        int widthSpace = 50;
        int heightSpace = 25;
        setEnvironment(environment);

        Color blockColor = new Color(Geometry.RAND.nextInt(100), Geometry.RAND.nextInt(100), Geometry.RAND.nextInt(100));
        for (int i = 0; i <= 5; i++) {
            blockColor = new Color(blockColor.getRed() + (i * 10), blockColor.getGreen() + (i * 10), blockColor.getBlue() + (i * 10));
            for (int j = environment.getWidth() - blockWidth - environment.getBorderWidth(); j >= (3 + i) * blockWidth;
                 j -= blockWidth) {
                Block newBlock = new Block(new Rectangle(new Point(j, environment.getBorderWidth() + ((4 + i) * blockHeight)),
                        blockWidth, blockHeight), blockColor);
                newBlock.addToGame(this);
            }
        }
        Paddle paddle = new Paddle(gui.getKeyboardSensor());
        environment.setPaddle(paddle);
        environment.getPaddle().addToGame(this);
        Ball ball = new Ball(environment, new Point(300, 550));
        ball.addToGame(this);
    }

    // Run the game -- start the animation loop.
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
    public GameEnvironment getEnvironment() {
        return environment;
    }
    public void setEnvironment(GameEnvironment environment) {
        this.environment = environment;
        for (Collidable collidable : environment.getCollidables()) {
            collidable.addToGame(this);
        }
    }
}



//    public void initialize() {
//        int blockWidth = 75;
//        int blockHeight = 25;
//        int widthSpace = 50;
//        int heightSpace = 25;
//        environment.getBottomBorder().addToGame(this);
//        environment.getRightBorder().addToGame(this);
//        environment.getTopBorder().addToGame(this);
//        environment.getLeftBorder().addToGame(this);
//        for (int i = blockWidth; i < environment.getHeight() / 2; i += blockHeight + heightSpace) {
//            Color blockColor = new Color(Geometry.RAND.nextInt(255),
//                    Geometry.RAND.nextInt(255), Geometry.RAND.nextInt(255));
//            for (int j = blockWidth; j + blockWidth + widthSpace < environment.getWidth();
//                 j += blockWidth + widthSpace) {
//                Block newBlock = new Block(new Rectangle(new Point(j, i),
//                        blockWidth, blockHeight), blockColor);
//                newBlock.addToGame(this);
//            }
//        }
//        Paddle paddle = new Paddle(gui.getKeyboardSensor());
//        paddle.addToGame(this);
//        Ball ball = new Ball(environment, new Point(300, 550));
//        ball.addToGame(this);
//    }