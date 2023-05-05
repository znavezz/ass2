import java.util.ArrayList;

public class Rectangle {
    private Point topLeft;
    private double width;
    private double height;
    private Line leftSide;
    private Line topSide;
    private Line rightSide;
    private Line bottomSide;

    // Create a new rectangle with location and width/height.
    public Rectangle(Point topLeft, double width, double height) {
        this.topLeft = topLeft;
        this.width = width;
        this.height = height;
        leftSide = new Line(topLeft, getBottomLeft());
        topSide = new Line(topLeft, getTopRight());
        rightSide = new Line(getTopRight(), getBottomRight());
        bottomSide = new Line(getBottomRight(), getBottomLeft());
    }

    // Return a (possibly empty) List of intersection points
    // with the specified line.
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

    // Return the width and height of the rectangle
    public double getWidth() {
        return width;
    }
    public double getHeight() {
        return height;
    }
    // Returns the top-left point of the rectangle.
    public Point getTopLeft() {
        return topLeft;
    }
    public Point getTopRight() {
        return new Point(topLeft.getX() + width, topLeft.getY());
    }
    public Point getBottomRight() {
        return new Point(topLeft.getX() + width, topLeft.getY() + height);
    }
    public Point getBottomLeft() {
        return new Point(topLeft.getX(), topLeft.getY() + height);
    }
    public Line getLeftSide() {
        return this.leftSide;
    }
    public Line getTopSide() {
        return this.topSide;
    }
    public Line getRightSide() {
        return this.rightSide;
    }
    public Line getBottomSide() {
        return this.bottomSide;
    }
    public void setTopLeft(Point point) {
        topLeft = point;
    }
    public String toString() {
        return "Top Left: " + topLeft.toString() + ".     Width - " + width + ".     Height - " + height;
    }
}