import java.awt.Color;

/**
 * Represents a rectangular area with left, top, right, and bottom borders.
 * This class provides methods for getting and setting the borders, as well
 * as generating random points within the borders.
 */
public class Borders {
    //Fields
    private Block left;
    private Block top;
    private Block right;
    private Block bottom;
    //Constructors
    /**
     * Constructor for the Borders class, using a GameEnvironment object.
     * @param g the GameEnvironment object containing information about the game's borders
     */
    public Borders(GameEnvironment g) {
        left = new Block(new Rectangle(new Point(0, g.getHorizontalBorderWidth()),
                g.getVerticalBorderWidth(), g.getHeight() - g.getHorizontalBorderWidth()), Color.GRAY);
        top = new Block(new Rectangle(new Point(0, 0), g.getWidth(), g.getHorizontalBorderWidth()), Color.GRAY);
        right = new Block(new Rectangle(new Point(g.getWidth() - g.getVerticalBorderWidth(),
                g.getHorizontalBorderWidth()),
                g.getVerticalBorderWidth(), g.getHeight() - g.getHorizontalBorderWidth()), Color.GRAY);
        bottom = new Block(new Rectangle(new Point(g.getVerticalBorderWidth(),
                g.getHeight() - g.getHorizontalBorderWidth()),
                g.getWidth() - (g.getVerticalBorderWidth() * 2), g.getHorizontalBorderWidth()), Color.GRAY);
    }
    /**
     * Constructor for the Borders class, using individual border and environment dimensions.
     * @param horizontalBorderWidth the width of the horizontal borders
     * @param verticalBorderWidth the width of the vertical borders
     * @param environmentWidth the width of the game environment
     * @param environmentHeight the height of the game environment
     */
    public Borders(int horizontalBorderWidth, int verticalBorderWidth, int environmentWidth, int environmentHeight) {
        left = new Block(new Rectangle(new Point(0, horizontalBorderWidth),
                verticalBorderWidth, environmentHeight - horizontalBorderWidth), Color.GRAY);
        top = new Block(new Rectangle(new Point(0, 0), environmentWidth, horizontalBorderWidth), Color.GRAY);
        right = new Block(new Rectangle(new Point(environmentWidth - verticalBorderWidth, horizontalBorderWidth),
                verticalBorderWidth, environmentHeight - horizontalBorderWidth), Color.GRAY);
        bottom = new Block(new Rectangle(new Point(verticalBorderWidth, environmentHeight - horizontalBorderWidth),
                environmentWidth - (verticalBorderWidth * 2), horizontalBorderWidth), Color.GRAY);
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
     * Sets the top border value.
     * @param top the top border value to set
     */
    public void setTop(Block top) {
        this.top = top;
    }
    /**
     * Sets the right border value.
     * @param right the right border value to set
     */
    public void setRight(Block right) {
        this.right = right;
    }
    /**
     * Sets the bottom border value.
     * @param bottom the bottom border value to set
     */
    public void setBottom(Block bottom) {
        this.bottom = bottom;
    }
    //Queries
    /**
     * Returns a string representation of the borders.
     * @return a string representation of the borders
     */
    public String toString() {
        return "Left Border: " + left.toString() + ".     Top Border: " + top.toString()
                + ".     Right Border: " + right.toString() + ".     Bottom Border: " + bottom.toString();
    }
    /**
     * Returns the left border value.
     * @return the left border value
     */
    public Block getLeft() {
        return left;
    }
    /**
     * Returns the top border value.
     * @return the top border value
     */
    public Block getTop() {
        return top;
    }
    /**
     * Returns the right border value.
     * @return the right border value
     */
    public Block getRight() {
        return right;
    }
    /**
     * Returns the bottom border value.
     * @return the bottom border value
     */
    public Block getBottom() {
        return bottom;
    }

    /**
     * Generates a random point within the borders.
     * @return a random point within the borders
     */
    public Point generateRandomPoint() {
        return new Point(Geometry.RAND.nextDouble(right.getRectangle().getLeftSide().start().getX()
                + left.getRectangle().getRightSide().start().getX()),
                Geometry.RAND.nextDouble(bottom.getRectangle().getTopSide().start().getY()
                        + top.getRectangle().getBottomSide().start().getY()));
    }
}
