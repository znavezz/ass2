/**
 * Represents a rectangular area with left, up, right, and down borders.
 * This class provides methods for getting and setting the borders, as well
 * as generating random points within the borders.
 */
public class Borders {
    //Fields
    private double left;
    private double up;
    private double right;
    private double down;
    //Constructors
    /**
     * Initializes the borders with default values.
     */
    public Borders() {
        left = 0;
        up = 0;
        right = 800;
        down = 600;
    }
    /**
     * Initializes the borders with specified right and down values.
     * @param right the right border value
     * @param down the down border value
     */
    public Borders(double right, double down) {
        this.right = right;
        this.down = down;
    }
    /**
     * Initializes the borders with specified left, up, right, and down values.
     * @param left the left border value
     * @param up the up border value
     * @param right the right border value
     * @param down the down border value
     */
    public Borders(double left, double up, double right, double down) {
        this.left = left;
        this.up = up;
        this.right = right;
        this.down = down;
    }
    //Commands
    /**
     * Sets the left border value.
     * @param left the left border value to set
     */
    public void setLeft(double left) {
        this.left = left;
    }
    /**
     * Sets the up border value.
     * @param up the up border value to set
     */
    public void setUp(double up) {
        this.up = up;
    }
    /**
     * Sets the right border value.
     * @param right the right border value to set
     */
    public void setRight(double right) {
        this.right = right;
    }
    /**
     * Sets the down border value.
     * @param down the down border value to set
     */
    public void setDown(double down) {
        this.down = down;
    }
    //Queries
    /**
     * Returns the left border value.
     * @return the left border value
     */
    public double getLeft() {
        return left;
    }
    /**
     * Returns the up border value.
     * @return the up border value
     */
    public double getUp() {
        return up;
    }
    /**
     * Returns the right border value.
     * @return the right border value
     */
    public double getRight() {
        return right;
    }
    /**
     * Returns the down border value.
     * @return the down border value
     */
    public double getDown() {
        return down;
    }
    /**
     * Returns a string representation of the borders.
     * @return a string representation of the borders
     */
    public String toString() {
        return "Left border = " + left + " Upper border = " + up + " Right border = "
                + right + " Down border = " + down;
    }
    /**
     * Generates a random point within the borders.
     * @return a random point within the borders
     */
    public Point generateRandomPoint() {
        return new Point(Geometry.RAND.nextDouble(right + left),
                Geometry.RAND.nextDouble(down + up));
    }
}
