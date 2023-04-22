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
    private Borders borders;
    private Color color = Color.BLACK;

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
    public Line(Point start, Point end, Color color) {
        this.start = start;
        this.end = end;
        this.color = color;
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
    public Line(double x1, double y1, double x2, double y2, Color color) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        this.color = color;
    }
    public Color getColor() {
        return this.color;
    }
    public void setColor(Color color) {
        this.color = color;
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
        double x;
        double y;
        Point intersection;
        // If both lines are parallel to the Y-axis
        if (this.isParallelToY() && other.isParallelToY()) {
            // Check for overlapping segments
            return this.isOverLap(other);
        } else if (this.isParallelToY() || other.isParallelToY()) {
            // If only one of the lines is parallel to the Y-axis
            // Check intersection using isOnLine method
            if (this.isParallelToY()) {
                x = this.start.getX();
                y = other.getYOfX(x);
                intersection = new Point(x, y);
                return intersection.isOnLine(other) && intersection.isOnLine(this);
            } else {
                x = other.start.getX();
                y = this.getYOfX(x);
                intersection = new Point(x, y);
                return intersection.isOnLine(this) && intersection.isOnLine(other);
            }
        } else {
            // If both lines are not parallel to the Y-axis
            // If both lines have the same slope
            if (Geometry.doubleEquals(this.getSlope(), other.getSlope())) {
                // Check for overlapping segments
                return this.isOverLap(other);
            } else {
                // Compare equations of lines and isolate variables
                x = (other.intersectWithYAxis() - this.intersectWithYAxis())
                        / (this.getSlope() - other.getSlope());
                y = this.getYOfX(x);
                intersection = new Point(x, y);
                return (intersection.isOnLine(this) && intersection.isOnLine(other));
            }
        }
    }


    // Returns the intersection point if the lines intersect,
    // and null otherwise (including cases of inclusion).
    public Point intersectionWith(Line other) {
        double x;
        if ((!isIntersecting(other))) {
            return null;
        } else if (other.isParallelToY() && !this.isParallelToY()) {
            x = other.start.getX();
            return new Point(x, this.getYOfX(x));
        } else if (this.isParallelToY() && !other.isParallelToY()) {
            x = this.start.getX();
            return new Point(x, other.getYOfX(x));
        }  else {
            if (this.isOverLap(other)) {
                if (this.start.equals(other.start)) {
                    return this.start;
                } else if (this.end.equals(other.start)) {
                    return this.end;
                } else if (this.start.equals(other.end)) {
                    return this.start;
                } else {
                    return this.end;
                }
            }
            x = (other.intersectWithYAxis() - this.intersectWithYAxis()) / (this.getSlope() - other.getSlope());
            return new Point(x, this.getYOfX(x));
        }
    }

    // equals -- return true if the lines are equal, false otherwise
    public boolean isEquals(Line other) {
        return (((this.start.equals(other.start())) && (this.end.equals(other.end())))
                || ((this.start.equals(other.end())) && (this.end.equals(other.start()))));
    }

    public void drawLine(DrawSurface d) {
        d.setColor(this.color);
        d.drawLine((int) Math.round(this.start.getX()), (int) Math.round(this.start.getY()),
                (int) Math.round(this.end.getX()), (int) Math.round(this.end.getY()));
    }

    public static Line generateRandomLine(Borders borders) {
        Point start = Point.generateRandomPoint(borders);
        Point end = Point.generateRandomPoint(borders);
        Line l = new Line(start, end);
        return l;
    }
}
