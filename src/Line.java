/**
 * Represents a line with start and end points.
 *
 * @author Nave Zehoray <znavez@gmail.com>
 * @version 1.0
 * @since 2023-04-09
 */

// Import necessary packages

import biuoop.DrawSurface;

import java.awt.Color;

public class Line {
    //Fields
    private Point start;
    private Point end;
    private double leftBorder = 0;
    private double rightBorder = 800;
    private double upperBorder = 0;
    private double downBorder = 600;

    /**
     * Constructs a new Line object with default start and end points (0, 0).
     */
    public Line() {
        this.start = new Point();
        this.end = new Point();
    }

    /**
     * Constructs a new Line object with the specified start and end points.
     *
     * @param start the start point of the line
     * @param end   the end point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs a new Line object with the specified coordinates for the start and end points.
     *
     * @param x1 the x-coordinate of the start point
     * @param y1 the y-coordinate of the start point
     * @param x2 the x-coordinate of the end point
     * @param y2 the y-coordinate of the end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Returns the Y value of the line at the given X coordinate.
     *
     * @param x the X coordinate
     * @return the Y value of the line at the given X coordinate
     */
    public double getYOfX(double x) {
        return (this.getSlope() * x) + this.intersectWithYAxis(); //Isolate variables in a straight line equation
    }

    /**
     * Returns the X value of the line at the given Y coordinate.
     *
     * @param y the Y coordinate
     * @return the X value of the line at the given Y coordinate
     */
    public double getXOfY(double y) {
        if (this.isParallelToY()) {
            return this.start.getX();
        }
        return (y - this.intersectWithYAxis()) / this.getSlope(); //Isolate variables in a straight line equation
    }


    /**
     * Returns the length of the line.
     *
     * @return the length of the line
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * Returns the slope of the line.
     *
     * @return the slope of the line
     */
    public double getSlope() {
        double deltaX = this.start.getX() - this.end.getX();
        double deltaY = this.start.getY() - this.end.getY();
        return (deltaY / deltaX); //slope formula
    }

    /**
     * Returns the middle point of the line.
     *
     * @return the middle point of the line
     */
    public Point middle() {
        Point middle = new Point(((this.start.getX() + this.end.getX()) / 2),
                ((this.start.getY() + this.end.getY()) / 2));
        return middle;
    }

    /**
     * Returns the start point of the line.
     *
     * @return the start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * Returns the end point of the line.
     *
     * @return the end point of the line
     */
    public Point end() {
        return this.end;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public void setEnd(Point end) {
        this.end = end;
    }

    // Return the intersection point of the line with the Y axis
    public double intersectWithYAxis() {
        //Isolate variables in a straight line equation
        return (this.start.getY() - (this.getSlope() * this.start.getX()));
    }

    public boolean isParallelToY() {
        return (Geometry.doubleEquals(this.start.getX(), this.end.getX()));
    }

    public boolean isOverLap(Line other) {
        if (this.isParallelToY() && other.isParallelToY()
                && Geometry.doubleEquals(this.start.getX(), other.start.getX())) {
            return !(Math.max(this.start.getY(), this.end.getY())
                    < Math.min(other.start().getY(), other.end().getY())
                    || Math.min(this.start.getY(), this.end.getY())
                    > Math.max(other.start().getY(), other.end().getY()));
        } else if ((other.isParallelToY() && !this.isParallelToY())
                || this.isParallelToY() && !other.isParallelToY()) {
            return false;
        } else if (this.getSlope() != other.getSlope()) {
            return false;
        } else {
            return ((this.start.isOnLine(other) || this.end.isOnLine(other))
                    && (other.start.isOnLine(other) || other.end.isOnLine(other)));
        }
    }

    // Returns true if the lines intersect, false otherwise
    public boolean isIntersecting(Line other) {
        // If both lines are parallel to the Y-axis
        if (this.isParallelToY() && other.isParallelToY()) {
            // Check for overlapping segments
            return this.isOverLap(other);
        } else if (this.isParallelToY() || other.isParallelToY()) {
            // If only one of the lines is parallel to the Y-axis
            double x;
            double y;
            Point intersection;

            // Check intersection using isOnLine method
            if (this.isParallelToY()) {
                x = this.start.getX();
                y = other.getYOfX(x);
                intersection = new Point(x, y);
                return intersection.isOnLine(other);
            } else {
                x = other.start.getX();
                y = this.getYOfX(x);
                intersection = new Point(x, y);
                return intersection.isOnLine(this);
            }
        } else {
            // If both lines are not parallel to the Y-axis
            // If both lines have the same slope
            if (Geometry.doubleEquals(this.getSlope(), other.getSlope())) {
                // Check for overlapping segments
                return this.isOverLap(other);
            } else {
                // Compare equations of lines and isolate variables
                double x = (other.intersectWithYAxis() - this.intersectWithYAxis())
                        / (this.getSlope() - other.getSlope());
                double y = this.getYOfX(x);
                Point intersection = new Point(x, y);
                return (intersection.isOnLine(this) && intersection.isOnLine(other));
            }
        }
    }


    // Returns the intersection point if the lines intersect,
    // and null otherwise (including cases of inclusion).
    public Point intersectionWith(Line other) {
        double x;
        Point intersectionPoint;
        if ((!isIntersecting(other))) {
            return null;
        } else if (other.isParallelToY() && !this.isParallelToY()) {
            x = other.start.getX();
            intersectionPoint = new Point(x, this.getYOfX(x));
            return intersectionPoint;
        } else if (this.isParallelToY() && !other.isParallelToY()) {
            x = this.start.getX();
            intersectionPoint = new Point(x, other.getYOfX(x));
            return intersectionPoint;
        } else {
            x = (other.intersectWithYAxis() - this.intersectWithYAxis()) / (this.getSlope() - other.getSlope());
            intersectionPoint = new Point(x, this.getYOfX(x));
            return intersectionPoint;
        }
    }

    // equals -- return true if the lines are equal, false otherwise
    public boolean isEquals(Line other) {
        return (((this.start.equals(other.start())) && (this.end.equals(other.end())))
                || ((this.start.equals(other.end())) && (this.end.equals(other.start()))));
    }

    public void drawLine(Line l, DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawLine((int) Math.round(this.start.getX()), (int) Math.round(start.getY()),
                (int) Math.round(this.end.getX()), (int) Math.round(this.end.getY()));
    }

    public static Line generateRandomLine() {
        Point start = Point.generateRandomPoint(0, 0, Point.WIDTH, Point.HEIGHT);
        Point end = Point.generateRandomPoint(0, 0, Point.WIDTH, Point.HEIGHT);
        Line l = new Line(start, end);
        return l;
    }
}
