/**
 * Represents a point with x and y coordinates.
 */
public class Point {
    //Fields
    private double x;
    private double y;
    //Constructors
    /**
     * Constructs a new Point object with default x and y coordinates (0, 0).
     */
    public Point() {
        this.x = 0;
        this.y = 0;
    }
    /**
     * Constructs a new Point object with the specified x and y coordinates.
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * Constructs a new Point object as the given point.
     * @param point the point.
     */
    public Point(Point point) {
        this.x = point.getX();
        this.y = point.getY();
    }
    //Commands
    /**
     * Sets the x-coordinate of this point.
     * @param x the new x-coordinate of this point.
     */
    public void setX(double x) {
        this.x = x;
    }
    /**
     * Sets the y-coordinate of this point.
     * @param y the new y-coordinate of this point.
     */
    public void setY(double y) {
        this.y = y;
    }
    /**
     * Returns a string representation of this point.
     * @return a string representation of this point
     */
    //Queries
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }
    /**
     * Calculates the distance between this point and another point.
     * @param other the other point to calculate the distance to
     * @return the distance between this point and the other point
     */
    public double distance(Point other) {
        return Math.sqrt(((this.getX() - other.getX()) * (this.getX() - other.getX()))
                + ((this.getY() - other.getY()) * (this.getY() - other.getY())));
    }
    /**
     * Returns the x-coordinate of this point.
     * @return the x-coordinate of this point
     */
    public double getX() {
        return this.x;
    }
    /**
     * Returns the y-coordinate of this point.
     * @return the y-coordinate of this point
     */
    public double getY() {
        return this.y;
    }
    /**
     * Determines whether this point is equal to another point.
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
    /**
     * This static method generates a random point within the given borders.
     * @param borders the Borders object that defines the area in which the point will be generated.
     * @return a random Point object within the given borders.
     */
    public static Point generateRandomPoint(Borders borders) {
        return new Point(Geometry.RAND.nextDouble(borders.getRight()) + borders.getLeft(),
                Geometry.RAND.nextDouble(borders.getDown()) + borders.getUp());
    }
}
