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
    private GameEnvironment environment;
    private static final int SCALING_FACTOR = 40;
    private final Random rand = new Random();
    private Point center;
    private int size;
    private Velocity velocity;
    private Color color;

    //constructors
    /**
     * Constructs a Ball object with a specified GameEnvironment and center point.
     * Sets the ball's size to 6, assigns a random color, and initializes its velocity.
     * @param environment the GameEnvironment the ball is placed in
     * @param center the center point of the ball
     */
    public Ball(GameEnvironment environment, Point center) {
        this.center = center;
        this.environment = environment;
        size = 6;
        color = new Color(Geometry.RAND.nextInt(255), Geometry.RAND.nextInt(255), Geometry.RAND.nextInt(255));
        velocity = Velocity.fromAngleAndSpeed(120, sizeToSpeed());
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
    /**
     * Notify the ball that time has passed and activate moveOneStep() method.
     */
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
    /**
     * Adjusts the ball's position to keep it inside the left border.
     */
    public void putInLeftBorder() {
        if (isOutOfLeft()) {
            this.center.setX(this.size + this.environment.getBorders().getLeft().
                    getRectangle().getRightSide().start().getX());
        }
    }
    /**
     * Adjusts the ball's position to keep it inside the right border.
     */
    public void putInRightBorder() {
        if (isOutOfRight()) {
            this.center.setX(this.environment.getBorders().getRight().getRectangle().
                    getLeftSide().start().getX() - this.size);
        }
    }
    /**
     * Adjusts the ball's position to keep it inside the top border.
     */
    public void putInTopBorder() {
        if (isOutOfTop()) {
            this.center.setY(this.size + this.environment.getBorders().getTop().getRectangle().
                    getBottomSide().start().getY());
        }
    }
    /**
     * Adjusts the ball's position to keep it inside the bottom border.
     */
    public void putInBottomBorder() {
        if (isOutOfBottom()) {
            this.center.setY(this.environment.getBorders().getBottom().getRectangle().
                    getTopSide().start().getY() - this.size);
        }
    }
    /**
     * Fixes the ball's position, ensuring it stays within the borders.
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
     * If a collision occurs, the ball's position is adjusted, and its velocity is updated accordingly.
     */
    public void moveOneStep() {
        Point endOfTrajectory = new Point(center);
        velocity.applyToPoint(endOfTrajectory);
        Line trajectory = new Line(center, endOfTrajectory);
        CollisionInfo closestCollision = environment.getClosestCollision(trajectory);
        if (closestCollision == null) {
            velocity.applyToPoint(center);
        } else {
            Line bottomSide = closestCollision.collisionObject().getCollisionRectangle().getBottomSide();
            Line rightSide = closestCollision.collisionObject().getCollisionRectangle().getRightSide();
            Line leftSide = closestCollision.collisionObject().getCollisionRectangle().getLeftSide();
            Line topSide = closestCollision.collisionObject().getCollisionRectangle().getTopSide();
            Point collision = closestCollision.collisionPoint();
            if (collision.isOnLine(bottomSide)) {
                center.setY(bottomSide.start().getY() + size);
            }
            if (collision.isOnLine(rightSide)) {
                center.setX(rightSide.start().getX() + size);
            }
            if (collision.isOnLine(topSide)) {
                center.setY(topSide.start().getY() - size);
            }
            if (collision.isOnLine(leftSide)) {
                center.setX(leftSide.start().getX() - size);
            }
            velocity = closestCollision.collisionObject().hit(closestCollision.collisionPoint(), velocity);
        }
    }
    /**
     * Adds the ball to the game environment and associates it with the paddle.
     * @param g the game environment
     */
    public void addToGame(Game g) {
        g.addSprite(this);
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
     * @return the size of the ball.
     */
    public int getSize() {
        return this.size;
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
        return (int) Math.min(environment.getBorders().getRight().getRectangle().getLeftSide().start().getX()
                        - environment.getBorders().getLeft().getRectangle().getRightSide().start().getX(),
                environment.getBorders().getBottom().getRectangle().getTopSide().start().getY()
                        - environment.getBorders().getTop().getRectangle().getBottomSide().start().getY()) / 4;
    }
    /**
     * Calculates the minimum allowed size for the ball based on the dimensions of the borders.
     * The minimum size cannot be smaller than 5.
     * @return the minimum size allowed
     */
    public int getMinSize() {
        return Math.min(((int) Math.min(environment.getBorders().getRight().getRectangle().getLeftSide().start().getX()
                        - environment.getBorders().getLeft().getRectangle().getRightSide().start().getX(),
                environment.getBorders().getBottom().getRectangle().getTopSide().start().getY()
                        - environment.getBorders().getTop().getRectangle().getBottomSide().start().getY()) / 20), 5);
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
     * Checks if the ball is out of the top border.
     * @return true if the ball is out of the top border, false otherwise.
     */
    public boolean isOutOfTop() {
        return (center.getY() - environment.getBorders().getTop().getRectangle().getBottomSide().start().getY() < size);
    }
    /**
     * Checks if the ball is out of the bottom border.
     * @return true if the ball is out of the bottom border, false otherwise.
     */
    public boolean isOutOfBottom() {
        return (center.getY() + size > environment.getBorders().getBottom().getRectangle().getTopSide().start().getY());
    }
    /**
     * Checks if the ball is out of the right border.
     * @return true if the ball is out of the right border, false otherwise.
     */
    public boolean isOutOfRight() {
        return (center.getX() + size > environment.getBorders().getRight().getRectangle().getLeftSide().start().getX());
    }

    /**
     * Checks if the ball is out of the left border.
     * @return true if the ball is out of the left border, false otherwise.
     */
    public boolean isOutOfLeft() {
        return (center.getX() - environment.getBorders().getLeft().getRectangle().getRightSide().start().getX() < size);
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
     * Checks if the ball overlaps the given rectangle.
     * @param rec the rectangle to check for overlap
     * @return true if the ball overlaps the rectangle, false otherwise
     */
    public boolean isOverLapRec(Rectangle rec) {
        return ((center.getX() + size > rec.getTopLeft().getX() && center.getX() < rec.getTopRight().getX())
                || (center.getX() - size < rec.getTopRight().getX() && center.getX() - size > rec.getTopLeft().getX()))
                && (center.getY() + size > rec.getTopRight().getY() && center.getY()
                + size < rec.getBottomRight().getY())
                || (center.getY() - size < rec.getBottomLeft().getY() && center.getY() - size
                > rec.getTopLeft().getY());
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
     * @return the center point of the ball.
     */
    public Point getCenter() {
        return this.center;
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
}