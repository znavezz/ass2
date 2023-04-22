/**
 * This class represents a ball object in a 2D environment with borders.
 * The ball has attributes such as size, color, center position, and velocity.
 * The ball can be moved and drawn on a given DrawSurface.
 * @author Nave Zehoray < znavez@gmail.com >
 * ID: 206388746
 * @version 1.3
 * @since 2023-04-12
 */
import biuoop.DrawSurface;

import java.util.Random;
import java.awt.Color;

public class Ball {
    //Frame
    private static final int SCALINGFACTOR = 50;
    //Fields
    private int size;
    private Color color;
    private Point center;
    private Velocity velocity;
    private Borders borders;
    //for convenience so i want create a rand everytime
    private Random rand = new Random();

    //constructors
    /**
     * Constructs a Ball with random attributes (center, size, color, and angle).
     * Ensures the ball is within the borders after initialization.
     */
    public Ball() {
            this.center = Point.generateRandomPoint(borders);
            this.size = (int) rand.nextDouble(Math.min(borders.getRight() - borders.getLeft(),
                    borders.getDown() - borders.getUp()) / 6) + 5;
            this.color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
            this.velocity = Velocity.fromAngleAndSpeed(rand.nextDouble() * 2 * Math.PI, this.sizeToSpeed());
            this.fixBall(); // Ensure the ball is within borders
    }
    /**
     * Constructs a Ball with the specified center, size, and color. The angle is randomly generated.
     * Ensures the ball is within the borders after initialization.
     * @param center the center Point of the ball.
     * @param r the radius of the ball.
     * @param color the color of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.size = r;
        this.color = color;
        this.velocity = Velocity.fromAngleAndSpeed(rand.nextDouble() * 2 * Math.PI, this.sizeToSpeed());
        this.fixBall(); // Ensure the ball is within borders
    }
    /**
     * Constructs a Ball with the specified x and y coordinates, size, and color. The velocity is randomly generated.
     * Ensures the ball is within the borders after initialization.
     * @param x the x-coordinate of the center of the ball.
     * @param y the y-coordinate of the center of the ball.
     * @param r the radius of the ball.
     * @param color the color of the ball.
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.size = r;
        this.color = color;
        this.velocity = Velocity.fromAngleAndSpeed(rand.nextDouble() * 2 * Math.PI, this.sizeToSpeed());
        this.fixBall(); // Ensure the ball is within borders
    }
    /**
     * Constructs a Ball with a random center and size, the specified color, and a randomly generated angle.
     * Ensures the ball is within the borders after initialization.
     * @param color the color of the ball.
     */
    public Ball(Color color) {

            this.center = Point.generateRandomPoint(borders);
            this.size = (int) rand.nextDouble(Math.min(borders.getRight() - borders.getLeft(),
                    borders.getDown() - borders.getUp()) / 6) + 1;
            this.color = color;
            this.velocity = Velocity.fromAngleAndSpeed(rand.nextDouble() * 2 * Math.PI, this.sizeToSpeed());
            this.fixBall(); // Ensure the ball is within borders
    }
    /**
     * Constructs a Ball with the specified center, a random size and color, and a randomly generated velocity.
     * Ensures the ball is within the borders after initialization.
     * @param center the center point of the ball.
     */
    public Ball(Point center) {
            this.center = center;
            this.size = (int) rand.nextDouble(Math.min(borders.getRight() - borders.getLeft(),
                    borders.getDown() - borders.getUp()) / 6) + 1;
            this.color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
            this.velocity = Velocity.fromAngleAndSpeed(rand.nextDouble() * 2 * Math.PI, this.sizeToSpeed());
            this.fixBall(); // Ensure the ball is within borders
    }
    /**
     * Constructs a Ball with the specified x and y coordinates, a random size and color,
     * and a randomly generated velocity.
     * Ensures the ball is within the borders after initialization.
     * @param x the x-coordinate of the center of the ball.
     * @param y the y-coordinate of the center of the ball.
     */
    public Ball(double x, double y) {
            center.setX(x);
            center.setY(y);
            size = (int) rand.nextDouble(Math.min(borders.getRight() - borders.getLeft(),
                    borders.getDown() - borders.getUp()) / 6) + 1;
            color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
            velocity = Velocity.fromAngleAndSpeed(rand.nextDouble() * 2 * Math.PI, this.sizeToSpeed());
            this.fixBall(); // Ensure the ball is within borders
    }
    /**
     * Constructs a Ball with a random center, the specified size, a random color, and a randomly generated velocity.
     * Ensures the ball is within the borders after initialization.
     * @param size the radius of the ball.
     */
    public Ball(int size) {
            center = Point.generateRandomPoint(borders);
            this.size = size;
            color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
            velocity = Velocity.fromAngleAndSpeed(rand.nextDouble() * 2 * Math.PI, this.sizeToSpeed());
            this.fixBall(); // Ensure the ball is within borders
    }
    /**
     * Constructs a Ball with the specified center and radius, a random color, and a randomly generated velocity.
     * Ensures the ball is within the borders after initialization.
     * @param center the center point of the ball.
     * @param r the radius of the ball.
     */
    public Ball(Point center, int r) {
        this.center = center;
        size = r;
        color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
        velocity = Velocity.fromAngleAndSpeed(rand.nextDouble() * 2 * Math.PI, this.sizeToSpeed());
        this.fixBall(); // Ensure the ball is within borders
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
        velocity = Velocity.fromAngleAndSpeed(rand.nextDouble() * 2 * Math.PI, this.sizeToSpeed());
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
        velocity = Velocity.fromAngleAndSpeed(rand.nextDouble() * 2 * Math.PI, this.sizeToSpeed());
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
        this.velocity = Velocity.fromAngleAndSpeed(rand.nextDouble() * 2 * Math.PI, this.sizeToSpeed());
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

    public Ball(Borders borders) {
        this.borders = borders;
    }



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
     * @return the size of the ball.
     */
    public int getSize() {
        return this.size;
    }
    /**
     * @return the color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }
    /**
     * @return the center point of the ball.
     */
    public Point getCenter() {
        return this.center;
    }

    // setters
    /**
     * Sets the x-coordinate of the ball's center.
     * @param x the x-coordinate to set.
     */
    public void setX(double x) {
        this.center.setX(x);
    }

    /**
     * Sets the y-coordinate of the ball's center.
     * @param y the y-coordinate to set.
     */

    public void setY(double y) {
        this.center.setX(y);
    }
    /**
     * Sets the size of the ball.
     * @param size the size to set.
     */
    public void setSize(int size) {
        this.size = size;
    }

    public String toString() {
        return "Size = " + size + "Color = " + color.toString() + center.toString() + borders.toString();
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    /**
     * Draws the ball on the given DrawSurface.
     * @param surface the DrawSurface on which the ball should be drawn.
     */    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) Math.round(this.center.getX()), (int) Math.round(this.center.getY()), this.size);
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
     * Sets the velocity of the ball.
     * @param v the Velocity object to set.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
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
     * Gets the current velocity of the ball.
     * @return the Velocity object representing the ball's velocity.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }
    public void fixLeft() {
        if (isOutOfLeft()) {
            this.center.setX(this.size + this.borders.getLeft() + 1);
        }
    }
    public void fixRight() {
        if (isOutOfRight()) {
            this.center.setX(this.borders.getRight() - this.size - 1);
        }
    }
    public void fixTop() {
        if (isOutOfTop()) {
            this.center.setY(this.size + this.borders.getUp() + 1);
        }
    }
    public void fixBottom() {
        if (isOutOfBottom()) {
            this.center.setY(this.borders.getDown() - this.size - 1);
        }
    }

    public void fixBall() {
        // edges
        this.fixTop();
        this.fixBottom();
        this.fixLeft();
        this.fixRight();

    }
    /**
     * Updates the ball's position based on its velocity and checks for collisions with borders.
     * If a collision occurs, the ball's position is adjusted, and its velocity is updated accordingly.
     */
    public void moveOneStep() {

        Ball check = new Ball(this.center, this.size, this.velocity,
                this.borders);
        check.center = check.getVelocity().applyToPoint(check.center);

        // Calculate the ball's new position
        this.getVelocity().applyToPoint(this.center);


            // Check for horizontal collisions (left and right borders)
            if (check.isOutOfWidth()) {
                // Reverse the horizontal velocity (dx) and update the new position accordingly
                this.setVelocity(-this.getVelocity().getDx(), this.getVelocity().getDy());
                this.getVelocity().applyToPoint(this.center);
            } else {
                // No collision, update the x position normally
                this.center.setX(check.getX());
            }

            // Check for vertical collisions (top and bottom borders)
            if (check.isOutOfHeight()) {
                // Reverse the vertical velocity (dy) and update the new position accordingly
                this.fixBall();
                this.setVelocity(this.getVelocity().getDx(), -this.getVelocity().getDy());
                this.getVelocity().applyToPoint(this.center);
            } else {
                // No collision, update the y position normally
                this.center.setY(check.getY());
            }
        // Check if the ball moves over the bounds. If so, adjust the position and speed.
        }

    /**
     * Converts the ball's size to an appropriate speed value.
     * @return the calculated speed value based on the ball's size.
     */
    private double sizeToSpeed() {
        double speed;
        if (this.size > 50) {
            speed = SCALINGFACTOR / 50;
        } else {
            speed = SCALINGFACTOR / this.size;
        }
        return speed;
    }

//    private double speedToSize() {
//        double size =
//    }

}