import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;

/**
 * @author Nave Zehoray < znavez@gmail.com >
 * @version 1.3
 * @since 2023-04-09
 * Represents a line with start and end points.
 */
public class Line {
    //Fields
    private Point start;
    private Point end;
    private Color color = Color.BLACK;
    //Constructors
    /**
     * Constructs a new Line object with the specified coordinates for the start and end points.
     * @param x1 the x-coordinate of the start point
     * @param y1 the y-coordinate of the start point
     * @param x2 the x-coordinate of the end point
     * @param y2 the y-coordinate of the end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        start = new Point(x1, y1);
        end = new Point(x2, y2);
    }

    /**
     * Constructs a new Line object with the specified start and end points.
     * @param start the start point of the line.
     * @param end the end point of the line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }
    /**
     * Constructs a new Line object with the specified coordinates for the start and end points and color.
     * @param x1 the x-coordinate of the start point
     * @param y1 the y-coordinate of the start point
     * @param x2 the x-coordinate of the end point
     * @param y2 the y-coordinate of the end point
     * @param color the color of the line
     */
    public Line(double x1, double y1, double x2, double y2, Color color) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        this.color = color;
    }
    //Commands
    /**
     * Draws this line on the given DrawSurface using its color.
     * @param d the DrawSurface on which to draw this line
     */
    public void drawLine(DrawSurface d) {
        d.setColor(this.color);
        d.drawLine((int) Math.round(this.start.getX()), (int) Math.round(this.start.getY()),
                (int) Math.round(this.end.getX()), (int) Math.round(this.end.getY()));
    }
    //Queries
    /**
     * Returns the Y value of the line at the given X coordinate.
     * @param x the X coordinate
     * @return the Y value of the line at the given X coordinate
     */
    public double getYOfX(double x) {
        return (this.getSlope() * x) + this.intersectWithYAxis(); //Isolate variables in a straight line equation
    }
    /**
     * Returns the slope of the line.
     * @return the slope of the line
     */
    public double getSlope() {
        double deltaX = this.start.getX() - this.end.getX();
        double deltaY = this.start.getY() - this.end.getY();
        return (deltaY / deltaX); //slope formula
    }
    /**
     * Returns the intersection point of the line with the Y-axis.
     * @return the intersection point of the line with the Y-axis
     */
    public double intersectWithYAxis() {
        //Isolate variables in a straight line equation
        return (this.start.getY() - (this.getSlope() * this.start.getX()));
    }
    /**
     * Returns true if the line is parallel to the Y-axis, false otherwise.
     * @return true if the line is parallel to the Y-axis, false otherwise
     */
    public boolean isParallelToY() {
        return (Geometry.doubleEquals(this.start.getX(), this.end.getX()));
    }
    /**
     * Returns true if this line overlaps with the other line, false otherwise.
     * @param other the other line to check for overlap with this line
     * @return true if this line overlaps with the other line, false otherwise
     */
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
    /**
     * Returns true if this line intersects with the other line, false otherwise.
     * @param other the other line to check for intersection with this line
     * @return true if this line intersects with the other line, false otherwise
     */
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
    /**
     * Returns true if this line is equal to the other line, false otherwise.
     * @param other the other line to check for equality with this line
     * @return true if this line is equal to the other line, false otherwise
     */
    public boolean isEquals(Line other) {
        return (((this.start.equals(other.start())) && (this.end.equals(other.end())))
                || ((this.start.equals(other.end())) && (this.end.equals(other.start()))));
    }
    /**
     * Returns the middle point of the line.
     * @return the middle point of the line
     */
    public Point middle() {
        return new Point(((this.start.getX() + this.end.getX()) / 2),
                ((this.start.getY() + this.end.getY()) / 2));
    }
    /**
     * Returns the start point of the line.
     * @return the start point of the line
     */
    public Point start() {
        return this.start;
    }
    /**
     * Returns the end point of the line.
     * @return the end point of the line
     */
    public Point end() {
        return this.end;
    }
    /**
     * Returns the intersection point if this line intersects with the other line,
     * and null otherwise (including cases of inclusion).
     * @param other the other line to check for intersection with this line
     * @return the intersection point if this line intersects with the other line, and null otherwise
     */
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
        } else {
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
    /**
     * Returns the closest intersection point of the line with the given rectangle.
     * If there are no intersection points, returns null.
     * @param rec the rectangle to check for intersection points
     * @return the closest intersection point to the start of the line or null if no intersection points
     */
    public Point closestIntersectionToStartOfLine(Rectangle rec) {
        ArrayList<Point> intersectionPoints = (ArrayList<Point>) rec.intersectionPoints(this);
        if (intersectionPoints.isEmpty()) {
            return null;
        } else {
            return intersectionPoints.get(0);
        }
    }
}
