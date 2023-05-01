import java.awt.*;

/**
 * Represents a rectangular area with left, up, right, and down borders.
 * This class provides methods for getting and setting the borders, as well
 * as generating random points within the borders.
 */
public class Borders {
    //Fields
    private Block left;
    private Block up;
    private Block right;
    private Block down;
    //Constructors
    /**
     * Initializes the borders with default values.
     */
    public Borders(GameEnvironment g) {
        left = new Block(new Rectangle(new Point(0, 0), g.getBorderWidth(), g.getHeight()), Color.BLACK);
        up = new Block(new Rectangle(new Point(0, 0), g.getWidth(), g.getBorderWidth()), Color.BLACK);
        right = new Block(new Rectangle(new Point(g.getWidth() - g.getBorderWidth(), 0), g.getBorderWidth(), g.getHeight()), Color.BLACK);
        down = new Block(new Rectangle(new Point(0, g.getHeight() - g.getBorderWidth()), g.getWidth(), g.getBorderWidth()), Color.BLACK);
    }


    //Commands
    /**
     * Sets the left border value.
     * @param left the left border value to set
     */
    public void setLeft(Block left) {
        this.left = left;
    }
    /**
     * Sets the up border value.
     * @param up the up border value to set
     */
    public void setUp(Block up) {
        this.up = up;
    }
    /**
     * Sets the right border value.
     * @param right the right border value to set
     */
    public void setRight(Block right) {
        this.right = right;
    }
    /**
     * Sets the down border value.
     * @param down the down border value to set
     */
    public void setDown(Block down) {
        this.down = down;
    }
    //Queries
    /**
     * Returns the left border value.
     * @return the left border value
     */
    public Block getLeft() {
        return left;
    }
    /**
     * Returns the up border value.
     * @return the up border value
     */
    public Block getUp() {
        return up;
    }
    /**
     * Returns the right border value.
     * @return the right border value
     */
    public Block getRight() {
        return right;
    }
    /**
     * Returns the down border value.
     * @return the down border value
     */
    public Block getDown() {
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
