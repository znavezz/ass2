/**
 * Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    //Fields
    private double dx;
    private double dy;
    // constructors
    /**
     * Constructor for Velocity.
     * @param dx the change in position on the x-axis.
     * @param dy the change in position on the y-axis.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
        double speed = Math.sqrt((dx * dx) + (dy * dy));
        double angle = Math.atan2(-this.dy, this.dx);
    }
    /**
     * Creates a Velocity object from the given angle and speed.
     * @param angle the angle of the velocity in degrees.
     * @param speed the magnitude of the velocity.
     * @return a Velocity object with the specified angle and speed.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        angle -= 90;
        double dx = speed * Math.cos(Math.toRadians(angle));
        double dy = speed * Math.sin(Math.toRadians(angle));
//        if (angle > 0 &&  angle < 180) {
//            dy = speed * Math.cos(Math.toRadians(angle));
//        } else {
//            dy = -speed * Math.cos(Math.toRadians(angle));
//        }
        return new Velocity(dx, dy);
    }
    //Commands
    /**
     * Sets the change in position on the x-axis.
     * @param dx the change in position on the x-axis.
     */

    public void setDx(double dx) {
        this.dx = dx;
    }
    /**
     * Sets the change in position on the y-axis.
     * @param dy the change in position on the y-axis.
     */
    public void setDy(double dy) {
        this.dy = dy;
    }
    /**
     * Apply the velocity to the given point, updating its position.
     * @param p the point to update with the velocity.
     */
    public void applyToPoint(Point p) {
        p.setX(p.getX() + this.dx);
        p.setY(p.getY() + this.dy);
    }
    //Queries
    /**
     * Returns the change in position on the x-axis.
     * @return the change in position on the x-axis.
     */
    public double getDx() {
        return this.dx;
    }
    /**
     * Returns the change in position on the y-axis.
     * @return the change in position on the y-axis.
     */
    public double getDy() {
        return this.dy;
    }
    /**
     * Returns the angle of the velocity.
     * @return the angle of the velocity in radians.
     */
    public double getAngle() {
        return Math.toDegrees(Math.atan(dy / dx));
    }
    /**
     * Returns the magnitude of the velocity.
     * @return the magnitude of the velocity.
     */
    public double getMagnitude() {
        return Math.sqrt((dx * dx) + (dy * dy));
    }
    /**
     * Returns a string representation of the Velocity object.
     * @return a string representation of the Velocity object.
     */
    public String toString() {
        return "Dx = " + dx + " Dy = " + dy + "     Angle = " + getAngle();
    }
}