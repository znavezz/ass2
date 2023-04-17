// Velocity specifies the change in position on the `x` and the `y` axes.
public class Velocity {

    //Fields
    private double dx;
    private double dy;
    private double angle;
    private double speed;

    // constructor
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
        double speed = Math.sqrt((dx * dx) + (dy * dy));
        double angle = Math.atan2(-this.dy, this.dx);
    }

    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.sin(angle);
        double dy = -speed * Math.cos(angle);
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
        return this.angle;
    }
    public double getSpeed() {
        return this.speed;
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
    public Point applyToPoint(Point p) {
        double newX = p.getX() + this.dx;
        double newY = p.getY() + this.dy;
        return new Point(newX, newY);
    }

//    public Point potentialMove(Ball ball) {
//        Point potential = new Point(ball.getCenter());
//
//    }
}