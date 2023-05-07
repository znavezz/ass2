import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;
/**
 * Paddle class represents a paddle in the game, which can move left or right.
 * Implements Sprite and Collidable interfaces.
 */
public class Paddle implements Sprite, Collidable {
    //Fields
    private int paddleWidth = 150;
    private int paddleHeight = 10;
    private GameEnvironment environment;
    private Rectangle rectangle;
    private double speed = 8;
    private Color color = new Color(Geometry.RAND.nextInt(255), Geometry.RAND.nextInt(255), Geometry.RAND.nextInt(255));
    private KeyboardSensor keyboard;
    //constructors
    /**
     * Constructs a new Paddle with the given game environment.
     * @param environment the game environment the paddle will be placed in
     */
    public Paddle(GameEnvironment environment) {
        this.environment = environment;
        rectangle = new Rectangle(new Point(environment.getBorders().getLeft().getRectangle().
                getRightSide().start().getX()
                + (environment.getBorders().getRight().getRectangle().getLeftSide().start().getX()
                + environment.getBorders().getLeft().getRectangle().getRightSide().start().getX()) / 2,
                (environment.getBorders().getBottom().getRectangle().
                        getTopSide().start().getY() - paddleHeight)), paddleWidth, paddleHeight);
    }
    //Commands
    /**
     * Moves the paddle to the left by its speed.
     * Prevents the paddle from moving outside the game borders.
     */
    public void moveLeft() {
        Point nextTopLeft = new Point(rectangle.getTopLeft().getX() - speed, rectangle.getTopLeft().getY());
        Rectangle nextRec = new Rectangle(nextTopLeft, paddleWidth, paddleHeight);
        if (environment.getBorders().getLeft().getRectangle().getRightSide().start().getX() > nextTopLeft.getX()) {
            setRectangle(new Rectangle(new Point(environment.getBorders().getLeft().getRectangle().
                    getRightSide().start().getX(),
                    rectangle.getTopLeft().getY()), paddleWidth, paddleHeight));
        } else {
            setRectangle(new Rectangle(nextTopLeft, paddleWidth, paddleHeight));
        }
    }
    /**
     * Moves the paddle to the right by its speed.
     * Prevents the paddle from moving outside the game borders.
     */
    public void moveRight() {
        Point nextTopLeft = new Point(rectangle.getTopLeft().getX() + speed, rectangle.getTopLeft().getY());
        Rectangle nextRec = new Rectangle(nextTopLeft, paddleWidth, paddleHeight);
        if (environment.getBorders().getRight().getRectangle().getLeftSide().start().getX()
                - paddleWidth < nextTopLeft.getX()) {
            setRectangle(new Rectangle(new Point(environment.getBorders().getRight().
                    getRectangle().getLeftSide().start().getX()
                    - paddleWidth, rectangle.getTopLeft().getY()), paddleWidth, paddleHeight));
        } else {
            setRectangle(new Rectangle(new Point(rectangle.getTopLeft().getX()
                    + speed, rectangle.getTopLeft().getY()), paddleWidth, paddleHeight));
        }
    }
    /**
     * Sets the paddle's rectangle to the specified rectangle.
     * @param rec the new rectangle for the paddle
     */
    private void setRectangle(Rectangle rec) {
        rectangle = rec;
    }

    /**
     * Notify the paddle that time passed and updates the paddle's position based on keyboard input.
     * Moves the paddle left if the left key is pressed, and right if the right key is pressed.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }
    /**
     * Draws the paddle on the specified draw surface.
     * @param d the draw surface on which to draw the paddle
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawRectangle((int) rectangle.getTopLeft().getX(), (int) rectangle.getTopLeft().getY(),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());
        d.setColor(color);
        d.fillRectangle((int) rectangle.getTopLeft().getX(), (int) rectangle.getTopLeft().getY(),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());
    }
    /**
     * Adds the paddle to the game as a collidable and a sprite.
     * @param g the game to which the paddle should be added
     */

    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
        setKeyboard(g.getGui().getKeyboardSensor());
    }
    /**
     * Sets the width of the paddle.
     * @param paddleWidth the new width of the paddle
     */
    public void setPaddleWidth(int paddleWidth) {
        this.paddleWidth = paddleWidth;
    }
    /**
     * Sets the height of the paddle.
     * @param paddleHeight the new height of the paddle
     */
    public void setPaddleHeight(int paddleHeight) {
        this.paddleHeight = paddleHeight;
    }
    /**
     * Sets the game environment for the paddle.
     * @param environment the new game environment for the paddle
     */
    public void setEnvironment(GameEnvironment environment) {
        this.environment = environment;
    }
    /**
     * Sets the keyboard sensor for the paddle.
     * @param keyboard the new keyboard sensor for the paddle
     */
    public void setKeyboard(KeyboardSensor keyboard) {
        this.keyboard = keyboard;
    }
    //Queries
    /**
     * Returns the width of the paddle.
     * @return the paddle width
     */
    public int getPaddleWidth() {
        return paddleWidth;
    }
    /**
     * Returns the height of the paddle.
     * @return the paddle height
     */
    public int getPaddleHeight() {
        return paddleHeight;
    }
    /**
     * Returns a string representation of the paddle, including its rectangle and color.
     * @return a string representation of the paddle
     */
    public String toString() {
        return "Paddle: " + rectangle.toString() + ".     Color - " + color.toString();
    }
    /**
     * Returns the collision rectangle of the paddle.
     * @return the paddle's collision rectangle
     */
    public Rectangle getCollisionRectangle() {
        return rectangle;
    }
    /**
     * Determines the new velocity of a ball after colliding with the paddle.
     * @param collisionPoint the point of collision between the ball and the paddle
     * @param currentVelocity the current velocity of the ball
     * @return the new velocity of the ball after the collision
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        ArrayList<Line> fiveRegion = getFiveRegions();
        if (collisionPoint.isOnLine(fiveRegion.get(0))) {
            System.out.println("Hit at 1 Region");
            return Velocity.fromAngleAndSpeed(-60, currentVelocity.getMagnitude());
        } else if (collisionPoint.isOnLine(fiveRegion.get(1))) {
            System.out.println("Hit at 2 Region");
            return Velocity.fromAngleAndSpeed(-30, currentVelocity.getMagnitude());
        } else if (collisionPoint.isOnLine(fiveRegion.get(2))) {
            System.out.println("Hit at 3 Region");
            return Velocity.fromAngleAndSpeed(0, currentVelocity.getMagnitude());
        } else if (collisionPoint.isOnLine(fiveRegion.get(3))) {
            System.out.println("Hit at 4 Region");
            return Velocity.fromAngleAndSpeed(30, currentVelocity.getMagnitude());
        } else if (collisionPoint.isOnLine(fiveRegion.get(4))) {
            System.out.println("Hit at 5 Region");
            return Velocity.fromAngleAndSpeed(60, currentVelocity.getMagnitude());
        } else if (collisionPoint.isOnLine(rectangle.getLeftSide())
                || collisionPoint.isOnLine(rectangle.getRightSide())) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        } else {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
    }
    /**
     * Divides the top side of the paddle into five equal regions and returns a list
     * of lines representing the boundaries between these regions.
     * These regions are used to calculate the angle at which the ball should bounce
     * off the paddle based on the point of collision.
     * @return an ArrayList of Line objects representing the boundaries between the five regions
     */
    public ArrayList<Line> getFiveRegions() {
        double regionY = rectangle.getTopLeft().getY();
        ArrayList<Line> fiveRegions = new ArrayList<Line>();
        for (double i = 0; i < 5; i++) {
            fiveRegions.add(new Line(rectangle.getTopLeft().getX() + ((i / 5) * rectangle.getWidth()),
                    regionY, rectangle.getTopLeft().getX() + (((i + 1) / 5) * rectangle.getWidth()), regionY));
        }
        return fiveRegions;
    }
    /**
     * Returns the game environment the paddle is placed in.
     * @return the game environment
     */
    public GameEnvironment getEnvironment() {
        return environment;
    }
}