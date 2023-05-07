import java.util.ArrayList;
/**
 * Represents a rectangle with a top-left point, width, and height.
 * This class also provides methods to interact with and obtain information about the rectangle.
 */
public class Rectangle {
    //Fields
    private Point topLeft;
    private double width;
    private double height;
    private Line leftSide;
    private Line topSide;
    private Line rightSide;
    private Line bottomSide;
    //Constructors
    /**
     * Constructs a new rectangle with the given top-left point, width, and height.
     * @param topLeft the top-left point of the rectangle
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     */
    public Rectangle(Point topLeft, double width, double height) {
        this.topLeft = topLeft;
        this.width = width;
        this.height = height;
        leftSide = new Line(topLeft, getBottomLeft());
        topSide = new Line(topLeft, getTopRight());
        rightSide = new Line(getTopRight(), getBottomRight());
        bottomSide = new Line(getBottomLeft(), getBottomRight());
    }
    //Commands
    /**
     * Sets the top-left point of the rectangle.
     * @param point the new top-left point of the rectangle
     */
    public void setTopLeft(Point point) {
        topLeft = point;
    }
    //Queries
    /**
     * Returns the width of the rectangle.
     * @return the width of the rectangle
     */
    public double getWidth() {
        return width;
    }
    /**
     * Returns the height of the rectangle.
     * @return the height of the rectangle
     */
    public double getHeight() {
        return height;
    }
    /**
     * Returns a string representation of the rectangle.
     * @return a string representation of the rectangle, displaying its top-left point, width, and height
     */
    public String toString() {
        return "Top Left: " + topLeft.toString() + ".     Width - " + width + ".     Height - " + height;
    }
    /**
     * Returns the top-left point of the rectangle.
     * @return the top-left point of the rectangle
     */
    public Point getTopLeft() {
        return topLeft;
    }
    /**
     * Returns the top-right point of the rectangle.
     * @return the top-right point of the rectangle
     */

    public Point getTopRight() {
        return new Point(topLeft.getX() + width, topLeft.getY());
    }
    /**
     * Returns the bottom-right point of the rectangle.
     * @return the bottom-right point of the rectangle
     */
    public Point getBottomRight() {
        return new Point(topLeft.getX() + width, topLeft.getY() + height);
    }
    /**
     * Returns the bottom-left point of the rectangle.
     * @return the bottom-left point of the rectangle
     */
    public Point getBottomLeft() {
        return new Point(topLeft.getX(), topLeft.getY() + height);
    }
    /**
     * Returns a (possibly empty) sorted list by distance from the start of the line
     * of intersection points with the line.
     * @param line the line to find intersection points with.
     * @return a sorted list by distance from the start of the line of intersection points with the line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        ArrayList<Point> intersectionPoints = new ArrayList<Point>();
        Point withLeft = line.intersectionWith(leftSide);
        if (withLeft != null) {
            intersectionPoints.add(withLeft);
        }
        Point withTop = line.intersectionWith(topSide);
        if (withTop != null) {
            intersectionPoints.add(withTop);
        }
        Point withRight = line.intersectionWith(rightSide);
        if (withRight != null) {
            intersectionPoints.add(withRight);
        }
        Point withBottom = line.intersectionWith(bottomSide);
        if (withBottom != null) {
            intersectionPoints.add(withBottom);
        }
        line.start().sortByDistance(intersectionPoints, 0, intersectionPoints.size() - 1);
        return intersectionPoints;
    }
    /**
     * Returns the left side of the rectangle as a Line object.
     * @return the left side of the rectangle
     */
    public Line getLeftSide() {
        return this.leftSide;
    }
    /**
     * Returns the top side of the rectangle as a Line object.
     * @return the top side of the rectangle
     */
    public Line getTopSide() {
        return this.topSide;
    }
    /**
     * Returns the right side of the rectangle as a Line object.
     * @return the right side of the rectangle
     */
    public Line getRightSide() {
        return this.rightSide;
    }
    /**
     * Returns the bottom side of the rectangle as a Line object.
     * @return the bottom side of the rectangle
     */
    public Line getBottomSide() {
        return this.bottomSide;
    }
}