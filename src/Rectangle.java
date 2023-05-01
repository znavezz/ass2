import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    private Line leftSide;
    private Line upSide;
    private Line rightSide;
    private Line downSide;

    // Create a new rectangle with location and width/height.
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        leftSide = new Line(upperLeft, getDownLeft());
        upSide = new Line(upperLeft, getUpperRight());
        rightSide = new Line(getUpperRight(), getDownRight());
        downSide = new Line(getDownRight(), getDownLeft());
    }

    // Return a (possibly empty) List of intersection points
    // with the specified line.
    public java.util.List<Point> intersectionPoints(Line line) {
        ArrayList<Point> intersectionPoints = new ArrayList<Point>();
        Point withLeft = line.intersectionWith(leftSide);
        if (withLeft != null) {
            intersectionPoints.add(withLeft);
        }
        Point withUp = line.intersectionWith(upSide);
        if (withUp != null) {
            intersectionPoints.add(withUp);
        }
        Point withRight = line.intersectionWith(rightSide);
        if (withRight != null) {
            intersectionPoints.add(withRight);
        }
        Point withDown = line.intersectionWith(downSide);
        if (withDown != null) {
            intersectionPoints.add(withDown);
        }
        line.start().sortByDistance(intersectionPoints, 0, intersectionPoints.size() - 1);
        return intersectionPoints;
    }

    // Return the width and height of the rectangle
    public double getWidth() {
        return width;
    }
    public double getHeight() {
        return height;
    }
    // Returns the upper-left point of the rectangle.
    public Point getUpperLeft() {
        return upperLeft;
    }
    public Point getUpperRight() {
        return new Point(upperLeft.getX() + width, upperLeft.getY());
    }
    public Point getDownRight() {
        return new Point(upperLeft.getX() + width, upperLeft.getY() + height);
    }
    public Point getDownLeft() {
        return new Point(upperLeft.getX(), upperLeft.getY() + height);
    }
    public Line getLeftSide() {
        return this.leftSide;
    }
    public Line getUpSide() {
        return this.upSide;
    }
    public Line getRightSide() {
        return this.rightSide;
    }
    public Line getDownSide() {
        return this.downSide;
    }
}