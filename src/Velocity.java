// Velocity specifies the change in position on the `x` and the `y` axes.
public class Velocity {

    //Fields
    private double dx;
    private double dy;
    // constructor
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
        double speed = Math.sqrt((dx * dx) + (dy * dy));
        double angle = Math.atan2(-this.dy, this.dx);
    }

    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.sin(Math.toRadians(angle));
        double dy = -speed * Math.cos(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }

    //Accessors
    public double getDx() {
        return this.dx;
    }

    public double getDy() {
        return this.dy;
    }
    public double getAngle() {
        return Math.atan2(dy, dx);
    }
    public double getSpeed() {
        return Math.sqrt((dx * dx) + (dy * dy));
    }

    //Setters

    public void setDx(double dx) {
        this.dx = dx;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }


    // Take a point with position (x,y) and return a new point
    // with position (x+dx, y+dy)

    public void applyToPoint(Point p) {
        p.setX(p.getX() + this.dx);
        p.setY(p.getY() + this.dy);
    }
}