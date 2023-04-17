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
    private static final int SCALINGFACTOR = 100;
    //Fields
    private int size;
    private Color color;
    private Point center;
    private Velocity velocity;
    private double leftBorder = 0;
    private double rightBorder = 800;
    private double upperBorder = 0;
    private double downBorder = 600;
    //for convenience so i want create a rand everytime
    private Random rand = new Random();

    //constructors
    /**
     * Constructs a Ball with random attributes (center, size, color, and angle).
     * Ensures the ball is within the borders after initialization.
     */
    public Ball() {
            this.center = Point.generateRandomPoint(leftBorder, upperBorder, rightBorder, downBorder);
            this.size = (int) rand.nextDouble(Math.min(rightBorder - leftBorder, downBorder - upperBorder) / 6) + 5;
            Color randColor = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
            this.color = randColor;
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

            this.center = Point.generateRandomPoint(leftBorder, upperBorder, rightBorder, downBorder);
            this.size = (int) rand.nextDouble(Math.min(rightBorder - leftBorder, downBorder - upperBorder) / 6) + 1;
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
            this.size = (int) rand.nextDouble(Math.min(rightBorder - leftBorder, downBorder - upperBorder) / 6) + 1;
            Color randColor = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
            this.color = randColor;
            this.velocity = Velocity.fromAngleAndSpeed(rand.nextDouble() * 2 * Math.PI, this.sizeToSpeed());
            this.fixBall(); // Ensure the ball is within borders
    }
    /**
     * Constructs a Ball with the specified x and y coordinates, a random size and color, and a randomly generated velocity.
     * Ensures the ball is within the borders after initialization.
     * @param x the x-coordinate of the center of the ball.
     * @param y the y-coordinate of the center of the ball.
     */
    public Ball(double x, double y) {
            this.center.setX(x);
            this.center.setY(y);
            this.size = (int) rand.nextDouble(Math.min(rightBorder - leftBorder, downBorder - upperBorder) / 6) + 1;
            Color randColor = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
            this.color = randColor;
            this.velocity = Velocity.fromAngleAndSpeed(rand.nextDouble() * 2 * Math.PI, this.sizeToSpeed());
            this.fixBall(); // Ensure the ball is within borders
    }
    /**
     * Constructs a Ball with a random center, the specified size, a random color, and a randomly generated velocity.
     * Ensures the ball is within the borders after initialization.
     * @param size the radius of the ball.
     */
    public Ball(int size) {
            this.center = Point.generateRandomPoint(leftBorder, upperBorder, rightBorder, downBorder);
            this.size = size;
            Color randColor = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
            this.color = randColor;
            this.velocity = Velocity.fromAngleAndSpeed(rand.nextDouble() * 2 * Math.PI, this.sizeToSpeed());
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
        this.size = r;
        Color randColor = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
        this.color = randColor;
        this.velocity = Velocity.fromAngleAndSpeed(rand.nextDouble() * 2 * Math.PI, this.sizeToSpeed());
        this.fixBall(); // Ensure the ball is within borders
    }
    /**
     * Constructs a Ball with the specified center, size, random color, and borders. The velocity is randomly generated.
     * Ensures the ball is within the borders after initialization.
     * @param center the center Point of the ball.
     * @param r the radius of the ball.
     * @param leftBorder the left border of the ball's environment.
     * @param upperBorder the upper border of the ball's environment.
     * @param rightBorder the right border of the ball's environment.
     * @param downBorder the bottom border of the ball's environment.
     */
    public Ball(Point center, int r, double leftBorder, double upperBorder, double rightBorder, double downBorder) {
        this.center = center;
        this.size = r;
        Color randColor = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
        this.color = randColor;
        this.velocity = Velocity.fromAngleAndSpeed(rand.nextDouble() * 2 * Math.PI, this.sizeToSpeed());
        this.upperBorder = upperBorder;
        this.leftBorder = leftBorder;
        this.downBorder = downBorder;
        this.rightBorder = rightBorder;
        this.fixBall(); // Ensure the ball is within borders
    }
    /**
     * Constructs a Ball with the specified size, random color, and borders. The velocity is randomly generated.
     * Ensures the ball is within the borders after initialization.
     * @param r the radius of the ball.
     * @param leftBorder the left border of the ball's environment.
     * @param upperBorder the upper border of the ball's environment.
     * @param rightBorder the right border of the ball's environment.
     * @param downBorder the bottom border of the ball's environment.
     */
    public Ball(int r, double leftBorder, double upperBorder, double rightBorder, double downBorder) {
        this.size = r;
        Color randColor = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
        this.color = randColor;
        this.velocity = Velocity.fromAngleAndSpeed(rand.nextDouble() * 2 * Math.PI, this.sizeToSpeed());
        this.upperBorder = upperBorder;
        this.leftBorder = leftBorder;
        this.downBorder = downBorder;
        this.rightBorder = rightBorder;
        this.center = Point.generateRandomPoint(leftBorder, upperBorder, rightBorder, downBorder);
        this.fixBall(); // Ensure the ball is within borders
    }
    /**
     * Constructs a Ball with the specified center, size, random color, and borders. The velocity is randomly generated.
     * Ensures the ball is within the borders after initialization.
     * @param center the center Point of the ball.
     * @param size the radius of the ball.
     * @param color the color of the ball.
     * @param leftBorder the left border of the ball's environment.
     * @param upperBorder the upper border of the ball's environment.
     * @param rightBorder the right border of the ball's environment.
     * @param downBorder the bottom border of the ball's environment.
     */
    public Ball(Point center, int size, Color color, double leftBorder, double upperBorder,
                double rightBorder, double downBorder) {
        this.center = center;
        this.size = size;
        Color randColor = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
        this.color = randColor;
        this.velocity = Velocity.fromAngleAndSpeed(rand.nextDouble() * 2 * Math.PI, this.sizeToSpeed());
        this.upperBorder = upperBorder;
        this.leftBorder = leftBorder;
        this.downBorder = downBorder;
        this.rightBorder = rightBorder;
        this.fixBall(); // Ensure the ball is within borders
    }
    /**
     * Constructs a Ball with the specified center, size, color, borders, and velocity.
     * Ensures the ball is within the borders after initialization.
     * @param center the center Point of the ball.
     * @param r the radius of the ball.
     * @param v the velocity of the ball.
     * @param lBorder the left border of the ball's environment.
     * @param uBorder the upper border of the ball's environment.
     * @param rBorder the right border of the ball's environment.
     * @param dBorder the bottom border of the ball's environment.
     */
    public Ball(Point center, int r, Velocity v, double lBorder, double uBorder, double rBorder, double dBorder) {
        this.center = center;
        this.size = r;
        Color randColor = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
        this.color = randColor;
        this.velocity = v;
        this.upperBorder = uBorder;
        this.leftBorder = lBorder;
        this.downBorder = dBorder;
        this.rightBorder = rBorder;
        this.fixBall(); // Ensure the ball is within borders
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

    public void getInfo() {
        System.out.println("Size = " + size);
        System.out.println("Color = " + color.toString());
        center.toString();
        System.out.println("Left border = " + leftBorder);
        System.out.println("Upper border = " + upperBorder);
        System.out.println("Right border = " + rightBorder);
        System.out.println("Down border = " + downBorder);
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
        return (this.center.getY() - this.upperBorder <= this.size);
    }
    /**
     * Checks if the ball is out of the bottom border.
     * @return true if the ball is out of the bottom border, false otherwise.
     */
    public boolean isOutOfBottom() {
        return (this.center.getY() + this.size >= downBorder);
    }
    /**
     * Checks if the ball is out of the right border.
     * @return true if the ball is out of the right border, false otherwise.
     */
    public boolean isOutOfRight() {
        return (this.center.getX() + this.size >= rightBorder);
    }

    /**
     * Checks if the ball is out of the left border.
     * @return true if the ball is out of the left border, false otherwise.
     */
    public boolean isOutOfLeft() {
        return (this.center.getX() - this.leftBorder <= this.size);
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
            this.center.setX(this.size + leftBorder + 1);
        }
    }
    public void fixRight() {
        if (isOutOfRight()) {
            this.center.setX(rightBorder - this.size - 1);
        }
    }
    public void fixTop() {
        if (isOutOfTop()) {
            this.center.setY(this.size + upperBorder + 1);
        }
    }
    public void fixBottom() {
        if (isOutOfBottom()) {
            this.center.setY(downBorder - this.size - 1);
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
                this.leftBorder, this.upperBorder, this.rightBorder, this.downBorder);
        check.center = check.getVelocity().applyToPoint(check.center);

            // Check for horizontal collisions (left and right borders)
            if (check.isOutOfLeft() || check.isOutOfRight()) {
                // Reverse the horizontal velocity (dx) and update the new position accordingly
                this.setVelocity(-this.getVelocity().getDx(), this.getVelocity().getDy());
                this.center.setX(this.center.getX() + this.getVelocity().getDx());
            } else {
                // No collision, update the x position normally
                this.center.setX(check.getX());
            }

            // Check for vertical collisions (top and bottom borders)
            if (check.isOutOfTop() || check.isOutOfBottom()) {
                // Reverse the vertical velocity (dy) and update the new position accordingly
                this.setVelocity(this.getVelocity().getDx(), -this.getVelocity().getDy());
                this.center.setY(this.center.getY() + this.getVelocity().getDy());
            } else {
                // No collision, update the y position normally
                this.center.setY(check.getY());
            }
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