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
public class Ball implements Sprite {
    //Fields
    private GameEnvironment gameEnvironment;
    private static final int SCALING_FACTOR = 60;
    private final Random rand = new Random();
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
            center = borders.generateRandomPoint();
            size = rand.nextInt(getMaxSize()) + getMinSize();
            color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
            velocity = Velocity.fromAngleAndSpeed(Geometry.getRandomAngle(), sizeToSpeed());
            fixBall(); // Ensure the ball is within borders
    }
    public Ball(GameEnvironment gameEnvironment, Point center) {
        this.center = center;
        this.gameEnvironment = gameEnvironment;
        size = 6;
        color = new Color(Geometry.RAND.nextInt(255), Geometry.RAND.nextInt(255), Geometry.RAND.nextInt(255));
        velocity = Velocity.fromAngleAndSpeed(Geometry.getRandomAngle(), sizeToSpeed());
    }
    /**
     * Constructs a Ball with the specified center, size, random color, and borders. The velocity is randomly generated.
     * Ensures the ball is within the borders after initialization.
     * @param center the center Point of the ball.
     * @param r the radius of the ball.
     */
    public Ball(Point center, int r) {
        this.center = center;
        size = r;
        fixSize();
        color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
        velocity = Velocity.fromAngleAndSpeed(Geometry.getRandomAngle(), this.sizeToSpeed());
        fixBall(); // Ensure the ball is within borders
    }
    /**
     * Constructs a Ball with the specified size, random color, and borders. The velocity is randomly generated.
     * Ensures the ball is within the borders after initialization.
     * @param r the radius of the ball.
     */
    public Ball(int r) {
        center = borders.generateRandomPoint();
        size = r;
        fixSize();
        color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
        velocity = Velocity.fromAngleAndSpeed(Geometry.getRandomAngle(), this.sizeToSpeed());
        fixBall(); // Ensure the ball is within borders
    }
    /**
     * Constructs a Ball with the specified center, size, random color, and borders. The velocity is randomly generated.
     * Ensures the ball is within the borders after initialization.
     * @param center the center Point of the ball.
     * @param size the radius of the ball.
     * @param color the color of the ball.
     */
    public Ball(Point center, int size, Color color) {
        this.center = center;
        this.size = size;
        fixSize();
        this.color = color;
        this.velocity = Velocity.fromAngleAndSpeed(Geometry.getRandomAngle(), this.sizeToSpeed());
        fixBall(); // Ensure the ball is within borders
    }
    /**
     * Constructs a Ball with the specified center, size, color, borders, and velocity.
     * Ensures the ball is within the borders after initialization.
     * @param center the center Point of the ball.
     * @param r the radius of the ball.
     * @param v the velocity of the ball.
     */
    public Ball(Point center, int r, Velocity v) {
        this.center = center;
        size = r;
        fixSize();
        Color randColor = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
        color = randColor;
        velocity = v;
        fixBall(); // Ensure the ball is within borders
    }
    /**
     * Constructs a Ball object with a specified starting point, borders, and velocity components.
     * @param start the starting point of the ball's center.
     * @param dx the horizontal component of the ball's velocity.
     * @param dy the vertical component of the ball's velocity.
     */
    public Ball(Point start, double dx, double dy) {
        center = start;
        if (dx > 30) {
            dx = 30;
        }
        if (dy > 30) {
            dy = 30;
        }
        velocity = new Velocity(dx, dy);
        if (velocity.getMagnitude() == 0) {
            size = (int) Math.min(borders.getRight().getRectangle().getLeftSide().start().getX()
                    - borders.getLeft().getRectangle().getRightSide().start().getX(),
                    borders.getBottom().getRectangle().getTopSide().start().getY()
                            - borders.getTop().getRectangle().getBottomSide().start().getY()) / 4;
        } else {
            size = velocityToSize();
        }
        color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
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
        surface.setColor(Color.BLACK);
        surface.drawCircle((int) Math.round(this.center.getX()), (int) Math.round(this.center.getY()), this.size);
        surface.setColor(this.color);
        surface.fillCircle((int) Math.round(this.center.getX()), (int) Math.round(this.center.getY()), this.size);
    }

    public void timePassed() {
        moveOneStep();
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

    public void putInLeftBorder() {
        if (isOutOfLeft()) {
            this.center.setX(this.size + this.borders.getLeft().getRectangle().getRightSide().start().getX());
        }
    }

    public void putInRightBorder() {
        if (isOutOfRight()) {
            this.center.setX(this.borders.getRight().getRectangle().getLeftSide().start().getX() - this.size);
        }
    }

    public void putInTopBorder() {
        if (isOutOfTop()) {
            this.center.setY(this.size + this.borders.getTop().getRectangle().getBottomSide().start().getY());
        }
    }

    public void putInBottomBorder() {
        if (isOutOfBottom()) {
            this.center.setY(this.borders.getBottom().getRectangle().getTopSide().start().getY() - this.size);
        }
    }
    /**
     * Adjusts the ball's size if it is larger than a sixth of the smaller border dimension.
     */
    public void fixPosition() {
        putInLeftBorder();
        putInTopBorder();
        putInRightBorder();
        putInBottomBorder();
    }
    /**
     * Ensures that the ball's size is within the minimum and maximum allowed size limits.
     * If the size is outside the limits, it randomly assigns a new size within the allowed range.
     */
    public void fixSize() {
        if (size > (getMaxSize())) {
            size = rand.nextInt(getMaxSize()) + getMinSize();
        } else if (size < getMinSize()) {
            size = rand.nextInt(getMaxSize()) + getMinSize();
        }
    }
    /**
     * Fixes the ball's position and size, ensuring it stays within the borders.
     */
    public void fixBall() {
        // edges
        fixSize();
        fixPosition();
    }
    /**
     * Updates the ball's position based on its velocity and checks for collisions with borders.
     * If a collision occurs, the ball's position is adjusted, and its velocity is topdated accordingly.
     */
    public void moveOneStep() {
        Point endOfTrajectory = new Point(center);
        velocity.applyToPoint(endOfTrajectory);
        Line trajectory = new Line(center, endOfTrajectory);
        CollisionInfo closestCollision = gameEnvironment.getClosestCollision(trajectory);
        if (closestCollision == null) {
            velocity.applyToPoint(center);
        } else {
            Line bottomSide = closestCollision.collisionObject().getCollisionRectangle().getBottomSide();
            Line rightSide = closestCollision.collisionObject().getCollisionRectangle().getRightSide();
            Line leftSide = closestCollision.collisionObject().getCollisionRectangle().getLeftSide();
            Line topSide = closestCollision.collisionObject().getCollisionRectangle().getTopSide();
            Point collision = closestCollision.collisionPoint();
            if (collision.isOnLine(bottomSide)) {
               center.setY(1 + (bottomSide.start().getY()));
               // center.setX(collision.getX() + (1 / Math.tan(Math.toRadians(velocity.getAngle()))));
            }
            if (collision.isOnLine(rightSide)) {
                center.setX(rightSide.start().getX() + 1);
            }
            if (collision.isOnLine(topSide)) {
                center.setY(topSide.start().getY() - 1);
            }
            if (collision.isOnLine(leftSide)) {
                center.setX(leftSide.start().getX() - 1);
            }
            velocity = closestCollision.collisionObject().hit(closestCollision.collisionPoint(), velocity);
        }
    }
    public void addToGame(Game g) {
        g.addSprite(this);
        g.getEnvironment().getPaddle().addBall(this);
    }
    //Queries
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
     * Checks if the ball is out of the top border.
     * @return true if the ball is out of the top border, false otherwise.
     */
    public boolean isOutOfTop() {
        return (center.getY() - borders.getTop().getRectangle().getBottomSide().start().getY() < size);
    }
    /**
     * Checks if the ball is out of the bottom border.
     * @return true if the ball is out of the bottom border, false otherwise.
     */
    public boolean isOutOfBottom() {
        return (center.getY() + size > borders.getBottom().getRectangle().getTopSide().start().getY());
    }
    /**
     * Checks if the ball is out of the right border.
     * @return true if the ball is out of the right border, false otherwise.
     */
    public boolean isOutOfRight() {
        return (center.getX() + size > borders.getRight().getRectangle().getLeftSide().start().getX());
    }

    /**
     * Checks if the ball is out of the left border.
     * @return true if the ball is out of the left border, false otherwise.
     */
    public boolean isOutOfLeft() {
        return (center.getX() - borders.getLeft().getRectangle().getRightSide().start().getX() < size);
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
        return "Ball: " + "Size = " + size + ".   Color = " + color.toString()
                + ".    Center = " + center.toString() + ".     Velocity = " + velocity.toString();
    }
    /**
     * Converts the ball's size to an appropriate speed value.
     * @return the calculated speed value based on the ball's size.
     */
    public double sizeToSpeed() {
        if (this.size > 50) {
            return SCALING_FACTOR / 50;
        } else {
            return SCALING_FACTOR / this.size;
        }
    }
    /**
     * Converts the ball's velocity magnitude to a size value by dividing the scaling factor
     * by either the magnitude or 30 (whichever is smaller).
     * @return the calculated size value
     */

    public int velocityToSize() {
        if (velocity.getMagnitude() > 30) {
            return (SCALING_FACTOR / 30);
        } else {
            return (int) (SCALING_FACTOR / velocity.getMagnitude());
        }
    }
    /**
     * Calculates the maximum allowed size for the ball based on the dimensions of the borders.
     * @return the maximum size
     */
    public int getMaxSize() {
        return (int) Math.min(borders.getRight().getRectangle().getLeftSide().start().getX()
                - borders.getLeft().getRectangle().getRightSide().start().getX(),
                borders.getBottom().getRectangle().getTopSide().start().getY()
                - borders.getTop().getRectangle().getBottomSide().start().getY()) / 4;
    }
    /**
     * Calculates the minimum allowed size for the ball based on the dimensions of the borders.
     * The minimum size cannot be smaller than 5.
     * @return the minimum size allowed
     */
    public int getMinSize() {
        return Math.min(((int) Math.min(borders.getRight().getRectangle().getLeftSide().start().getX()
                - borders.getLeft().getRectangle().getRightSide().start().getX(),
                borders.getBottom().getRectangle().getTopSide().start().getY()
                        - borders.getTop().getRectangle().getBottomSide().start().getY()) / 20), 5);
    }
}