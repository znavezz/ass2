/**
 * Represents a point with x and y coordinates.
 * ID - 206388746
 *
 * @author Nave Zehoray <znavez@gmail.com>
 * @version 1.0
 * @since 2023-04-08
 */


import java.util.Random;

public class Point {
    //Fields
    private double x; //x-coordinate
    private double y; //y-coordinate


    /**
     * Constructs a new Point object with default x and y coordinates (0, 0).
     */
    public Point() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * Constructs a new Point object with the specified x and y coordinates.
     *
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }


    /**
     * Constructs a new Point object as the given point.
     *
     * @param point the point.
     */
    public Point(Point point) {
        this.x = point.getX();
        this.y = point.getY();
    }

    /**
     * Returns a string representation of this point.
     *
     * @return a string representation of this point
     */
    public String toString() {
        return "x = " + this.x + "    y = " + this.y;
    }

    /**
     * Calculates the distance between this point and another point.
     *
     * @param other the other point to calculate the distance to
     * @return the distance between this point and the other point
     */
    public double distance(Point other) {
        return Math.sqrt(((this.getX() - other.getX()) * (this.getX() - other.getX()))
                + ((this.getY() - other.getY()) * (this.getY() - other.getY())));
    }

    /**
     * Returns the x-coordinate of this point.
     *
     * @return the x-coordinate of this point
     */
    public double getX() {
        return this.x;
    }

    /**
     * Returns the y-coordinate of this point.
     *
     * @return the y-coordinate of this point
     */
    public double getY() {
        return this.y;
    }

    //setters
    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    /**
     * Determines whether this point is equal to another point.
     *
     * @param other the other point to compare with
     * @return true if the x-coordinate and y-coordinate of this point are equal to the x-coordinate
     * and y-coordinate of the other point, respectively; false otherwise.
     */
    public boolean equals(Point other) {
        return (Geometry.doubleEquals(this.getX(), other.getX())
                && (Geometry.doubleEquals(this.getY(), other.getY())));
    }

    /**
     * Checks if this point is on the given line.
     *
     * @param l the line to check if this point is on
     * @return true if this point is on the given line, false otherwise
     */
    public boolean isOnLine(Line l) {
        //if the line is parallel to Y axis
        if (l.isParallelToY()) {
            return Geometry.doubleEquals(this.x, l.start().getX())
                    && this.y <= Math.max(l.start().getY(), l.end().getY()) + Geometry.EPSILON
                    && this.y >= Math.min(l.start().getY(), l.end().getY()) - Geometry.EPSILON;
        }
        //otherwise
        return ((Geometry.doubleEquals(this.y, ((l.getSlope() * this.x)) + l.intersectWithYAxis()))
                && (this.x <= Math.max(l.start().getX(), l.end().getX()) + Geometry.EPSILON)
                && (this.x >= Math.min(l.start().getX(), l.end().getX()) - Geometry.EPSILON)
                && (this.y <= Math.max(l.start().getY(), l.end().getY()) + Geometry.EPSILON)
                && (this.y >= Math.min(l.start().getY(), l.end().getY()) - Geometry.EPSILON));
    }

    public static Point generateRandomPoint(Borders borders) {
        double x, y, z, w;
        Random rand = new Random(); // create a random-number generator
        x = rand.nextDouble(borders.getRight()) + borders.getLeft(); // get integer in range 1-WIDTH
        y = rand.nextDouble(borders.getDown()) + borders.getUp(); // get integer in range 1-HEIGHT
        Point p = new Point(x, y);
        return p;
    }
    public boolean isInBorders(Borders borders) {
        return this.x <= borders.getRight() && this.x >= borders.getLeft()
                && this.y <= borders.getDown() && this.y >= borders.getUp();
    }
}