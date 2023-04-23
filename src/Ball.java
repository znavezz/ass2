import biuoop.DrawSurface;
import java.util.Random;
import java.awt.Color;
/**
 * This class represents a ball object in a 2D environment with borders.
 * The ball has attributes such as size, color, center position, and velocity.
 * The ball can be moved and drawn on a given DrawSurface.
 * @author Nave Zehoray < znavez@gmail.com >
 * ID: 206388746
 * @version 1.6
 * @since 2023-04-12
 */
public class Ball {
    //Frame
    private static final int SCALING_FACTOR = 100;
    private static final double DEFAULT_WIDTH = 800;
    private static final double DEFAULT_HEIGHT = 600;
    private final Random rand = new Random();
    //Fields
    private Borders borders;
    private Point center;
    private int size;
    private Velocity velocity;
    private Color color;

    //constructors
    /**
     * Constructs a Ball with random attributes (center, size, color, and angle).
     * Ensures the ball is within the borders after initialization.
     */
    public Ball() {
            borders = new Borders(DEFAULT_WIDTH, DEFAULT_HEIGHT);
            center = Point.generateRandomPoint(borders);
            size = (int) rand.nextDouble(Math.min(borders.getRight() - borders.getLeft(),
                    borders.getDown() - borders.getUp()) / 6) + 10;
            color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
            velocity = Velocity.fromAngleAndSpeed(Geometry.getRandomAngle(), sizeToSpeed());
            fixBall(); // Ensure the ball is within borders
    }

    /**
     * Constructs a Ball with the specified center, size, random color, and borders. The velocity is randomly generated.
     * Ensures the ball is within the borders after initialization.
     * @param center the center Point of the ball.
     * @param r the radius of the ball.
     * @param borders the borders of the ball.
     */
    public Ball(Point center, int r, Borders borders) {
        this.center = center;
        size = r;
        color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
        velocity = Velocity.fromAngleAndSpeed(Geometry.getRandomAngle(), this.sizeToSpeed());
        this.borders = borders;
        this.fixBall(); // Ensure the ball is within borders
    }
    /**
     * Constructs a Ball with the specified size, random color, and borders. The velocity is randomly generated.
     * Ensures the ball is within the borders after initialization.
     * @param r the radius of the ball.
     * @param borders the borders of the ball.
     */
    public Ball(int r, Borders borders) {
        center = Point.generateRandomPoint(borders);
        size = r;
        color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
        velocity = Velocity.fromAngleAndSpeed(Geometry.getRandomAngle(), this.sizeToSpeed());
        this.borders = borders;
        this.fixBall(); // Ensure the ball is within borders
    }
    /**
     * Constructs a Ball with the specified center, size, random color, and borders. The velocity is randomly generated.
     * Ensures the ball is within the borders after initialization.
     * @param center the center Point of the ball.
     * @param size the radius of the ball.
     * @param color the color of the ball.
     * @param borders the borders of the ball.
     */
    public Ball(Point center, int size, Color color, Borders borders) {
        this.center = center;
        this.size = size;
        this.color = color;
        this.velocity = Velocity.fromAngleAndSpeed(Geometry.getRandomAngle(), this.sizeToSpeed());
        this.borders = borders;
        this.fixBall(); // Ensure the ball is within borders
    }
    /**
     * Constructs a Ball with the specified center, size, color, borders, and velocity.
     * Ensures the ball is within the borders after initialization.
     * @param center the center Point of the ball.
     * @param r the radius of the ball.
     * @param v the velocity of the ball.
     * @param borders the borders of the ball.
     */
    public Ball(Point center, int r, Velocity v, Borders borders) {
        this.center = center;
        size = r;
        Color randColor = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
        color = randColor;
        velocity = v;
        this.borders = borders;
        this.fixBall(); // Ensure the ball is within borders
    }
    /**
     * Constructs a Ball with random attributes (center, size, color, and angle) and specified borders.
     * Ensures the ball is within the borders after initialization.
     * @param borders the borders of the ball.
     */
    public Ball(Borders borders) {
        this.borders = borders;
        center = Point.generateRandomPoint(borders);
        size = (int) rand.nextDouble(Math.min(borders.getRight() - borders.getLeft(),
                borders.getDown() - borders.getUp()) / 6) + 10;
        color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
        velocity = Velocity.fromAngleAndSpeed(Geometry.getRandomAngle(), sizeToSpeed());
        fixBall(); // Ensure the ball is within borders
    }
    //Commands
    /**
     * Sets the center of the ball.
     * @param center the new center Point of the ball.
     */
    public void setCenter(Point center) {
        this.center = center;
    }
    /**
     * Sets the center of the ball using x and y coordinates.
     * @param x the x-coordinate of the new center.
     * @param y the y-coordinate of the new center.
     */
    public void setCenter(double x, double y) {
        center.setX(x);
        center.setY(y);
    }
    /**
     * Sets the size of the ball.
     * @param size the size to set.
     */
    public void setSize(int size) {
        this.size = size;
    }
    /**
     * Sets the color of the ball.
     * @param color the color to set.
     */
    public void setColor(Color color) {
        this.color = color;
    }
    /**
     * Draws the ball on the given DrawSurface.
     * @param surface the DrawSurface on which the ball should be drawn.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) Math.round(this.center.getX()), (int) Math.round(this.center.getY()), this.size);
    }
    /**
     * Sets the velocity of the ball using dx and dy values.
     * @param dx the change in x-axis position.
     * @param dy the change in y-axis position.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity.setDx(dx);
        this.velocity.setDy(dy);
    }
    /**
     * Adjusts the ball's position if it is out of the left border and reverses its horizontal velocity.
     */
    public void fixLeft() {
        if (isOutOfLeft()) {
            this.center.setX(this.size + this.borders.getLeft());
            this.setVelocity(-this.getVelocity().getDx(), this.getVelocity().getDy());
        }
    }
    /**
     * Adjusts the ball's position if it is out of the right border and reverses its horizontal velocity.
     */
    public void fixRight() {
        if (isOutOfRight()) {
            this.center.setX(this.borders.getRight() - this.size);
            this.setVelocity(-this.getVelocity().getDx(), this.getVelocity().getDy());
        }
    }
    /**
     * Adjusts the ball's position if it is out of the top border and reverses its vertical velocity.
     */
    public void fixTop() {
        if (isOutOfTop()) {
            this.center.setY(this.size + this.borders.getUp());
            this.setVelocity(this.getVelocity().getDx(), -this.getVelocity().getDy());
        }
    }
    /**
     * Adjusts the ball's position if it is out of the bottom border and reverses its vertical velocity.
     */
    public void fixBottom() {
        if (isOutOfBottom()) {
            this.center.setY(this.borders.getDown() - this.size);
            this.setVelocity(this.getVelocity().getDx(), -this.getVelocity().getDy());
        }
    }
    /**
     * Adjusts the ball's size if it is larger than a sixth of the smaller border dimension.
     */
    public void fixSize() {
        if (size > (Math.min(borders.getDown(), borders.getRight()) / 6)) {
            size = (int) (Math.min(borders.getDown(), borders.getRight()) / 6);
        }
    }
    /**
     * Fixes the ball's position and size, ensuring it stays within the borders.
     */
    public void fixBall() {
        // edges
        fixTop();
        fixBottom();
        fixLeft();
        fixRight();
        fixSize();

    }
    /**
     * Updates the ball's position based on its velocity and checks for collisions with borders.
     * If a collision occurs, the ball's position is adjusted, and its velocity is updated accordingly.
     */
    public void moveOneStep() {

        Ball check = new Ball(this.center, this.size, this.velocity,
                this.borders);
        // Calculate the ball's new position
        check.getVelocity().applyToPoint(check.center);
        // Check for horizontal collisions (left and right borders)
            if (check.isOutOfFrame()) {
                check.fixBall();
                // Reverse the horizontal velocity (dx) and update the new position accordingly
            }
            this.center = check.getCenter();
        // Check if the ball moves over the bounds. If so, adjust the position and speed.
        }
    //Queries
    // accessors
    /**
     * @return the x-coordinate of the ball's center.
     */
    public int getX() {
        return (int) Math.round(this.center.getX());
    }
    /**
     * @return the y-coordinate of the ball's center.
     */
    public int getY() {
        return (int) Math.round(this.center.getY());
    }
    /**
     * @return the center point of the ball.
     */
    public Point getCenter() {
        return this.center;
    }
    /**
     * @return the size of the ball.
     */
    public int getSize() {
        return this.size;
    }
    /**
     * @return the color of the ball.
     */
    public Color getColor() {
        return this.color;
    }
    /**
     * Gets the current velocity of the ball.
     * @return the Velocity object representing the ball's velocity.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }
    /**
     * @return the default width of the frame.
     */
    public static double getDefaultWidth() {
        return DEFAULT_WIDTH;
    }
    /**
     * @return the default height of the frame.
     */
    public static double getDefaultHeight() {
        return DEFAULT_HEIGHT;
    }
    /**
     * Checks if the ball is out of the top border.
     * @return true if the ball is out of the top border, false otherwise.
     */
    public boolean isOutOfTop() {
        return (center.getY() - borders.getUp() < size);
    }
    /**
     * Checks if the ball is out of the bottom border.
     * @return true if the ball is out of the bottom border, false otherwise.
     */
    public boolean isOutOfBottom() {
        return (center.getY() + size > borders.getDown());
    }
    /**
     * Checks if the ball is out of the right border.
     * @return true if the ball is out of the right border, false otherwise.
     */
    public boolean isOutOfRight() {
        return (center.getX() + size > borders.getRight());
    }

    /**
     * Checks if the ball is out of the left border.
     * @return true if the ball is out of the left border, false otherwise.
     */
    public boolean isOutOfLeft() {
        return (center.getX() - borders.getLeft() < size);
    }
    /**
     * Checks if the ball is out of the width borders (left or right).
     * @return true if the ball is out of the width borders, false otherwise.
     */
    public boolean isOutOfWidth() {
        return this.isOutOfRight() || this.isOutOfLeft();
    }

    /**
     * Checks if the ball is out of the height borders (top or bottom).
     * @return true if the ball is out of the height borders, false otherwise.
     */
    public boolean isOutOfHeight() {
        return this.isOutOfBottom() || this.isOutOfTop();
    }
    /**
     * Checks if the ball is out of the frame (any border).
     * @return true if the ball is out of the frame, false otherwise.
     */
    public boolean isOutOfFrame() {
        return this.isOutOfWidth() || this.isOutOfHeight();
    }

    /**
     * Returns a string representation of the ball, including its size, color, center position, and borders.
     * @return a string representation of the ball.
     */
    public String toString() {
        return "Size = " + size + "Color = " + color.toString() + center.toString() + borders.toString();
    }
    /**
     * Converts the ball's size to an appropriate speed value.
     * @return the calculated speed value based on the ball's size.
     */
    private double sizeToSpeed() {
        double speed;
        if (this.size > 50) {
            speed = SCALING_FACTOR / 50;
        } else {
            speed = SCALING_FACTOR / this.size;
        }
        return speed;
    }
}