import java.awt.Color;
import java.util.ArrayList;

/**
 * Represents the environment for the game, including borders, background, paddle, balls, and collidables.
 */

public class GameEnvironment {
    //Fields
    private int width = 800;
    private int height = 600;
    private Block backGround = new Block(new Rectangle(new Point(0, 0), width, height),
            new Color(Geometry.RAND.nextInt(255), Geometry.RAND.nextInt(255), Geometry.RAND.nextInt(255)));
    private int horizontalBorderWidth = 25;
    private int verticalBorderWidth = 20;
    private Borders borders = new Borders(horizontalBorderWidth, verticalBorderWidth, width, height);
    private Paddle paddle = new Paddle(this);
    private BallCollection balls = new BallCollection();
    private ArrayList<Collidable> collidables = new ArrayList<Collidable>() {
        {
            add(borders.getLeft());
            add(borders.getTop());
            add(borders.getRight());
            add(borders.getBottom());
            add(paddle);
        }
    };
    //Constructors
    /**
     * Creates a default game environment with default dimensions, borders, and collidables.
     */
    public GameEnvironment() {
    }
    /**
     * Creates a game environment with the specified collidables and borders.
     * @param collidables the list of collidable objects to add to the game environment
     * @param borders the borders of the game environment
     */
    public GameEnvironment(ArrayList<Collidable> collidables, Borders borders) {
        this.collidables = collidables;
        this.borders = borders;
        addCollidable(borders.getLeft());
        addCollidable(borders.getTop());
        addCollidable(borders.getRight());
        addCollidable(borders.getBottom());
    }
    //Commands
    /**
     * Adds the given collidable to the environment.
     *
     * @param c the collidable to add to the environment
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }
    /**
     * Sets the horizontal border width of the game environment.
     * @param width the new horizontal border width
     */
    public void setHorizontalBorderWidth(int width) {
        horizontalBorderWidth = width;
    }
    /**
     * Sets the vertical border width of the game environment.
     *
     * @param width the new vertical border width
     */
    public void setVerticalBorderWidth(int width) {
        verticalBorderWidth = width;
    }
    /**
     * Sets the width of the game environment.
     *
     * @param width the new width of the game environment
     */
    public void setWidth(int width) {
        this.width = width;
    }
    /**
     * Sets the height of the game environment.
     *
     * @param height the new height of the game environment
     */
    public void setHeight(int height) {
        this.height = height;
    }
    /**
     * Sets the paddle of the game environment.
     *
     * @param paddle the new paddle for the game environment
     */
    public void setPaddle(Paddle paddle) {
        this.paddle = paddle;
    }
    /**
     * Sets the ball collection of the game environment.
     *
     * @param balls the new ball collection for the game environment
     */
    public void setBalls(BallCollection balls) {
        this.balls = balls;
    }
    /**
     * Sets the background of the game environment.
     * @param backGround the new background of the game environment.
     */
    public void setBackGround(Block backGround) {
        this.backGround = backGround;
    }
    //Queries
    /**
     * Returns the horizontal border width of the game environment.
     * @return the horizontal border width
     */
    public int getHorizontalBorderWidth() {
        return horizontalBorderWidth;
    }
    /**
     * Returns the vertical border width of the game environment.
     * @return the vertical border width
     */
    public int getVerticalBorderWidth() {
        return verticalBorderWidth;
    }
    /**
     * Returns the width of the game environment.
     *
     * @return the width of the game environment
     */
    public int getWidth() {
        return width;
    }
    /**
     * Returns the height of the game environment.
     *
     * @return the height of the game environment
     */
    public int getHeight() {
        return height;
    }
    /**
     * Returns the borders of the game environment.
     *
     * @return the borders of the game environment
     */
    public Borders getBorders() {
        return borders;
    }

    /**
     * Returns the list of collidable objects in the game environment.
     *
     * @return the list of collidable objects in the game environment
     */
    public ArrayList<Collidable> getCollidables() {
        return collidables;
    }
    /**
     * Returns the closest collision information for the given trajectory.
     * If no collision occurs, returns null.
     *
     * @param trajectory the trajectory to check for collisions
     * @return the closest collision information, or null if no collision occurs
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        ArrayList<CollisionInfo> collisions = new ArrayList<CollisionInfo>();
        CollisionInfo closestCollision;
        for (Collidable collidable : collidables) {
            if (!collidable.getCollisionRectangle().intersectionPoints(trajectory).isEmpty()) {
                Point closestPointInRec = collidable.getCollisionRectangle().intersectionPoints(trajectory).get(0);
                collisions.add(new CollisionInfo(closestPointInRec, collidable));
            }
        }
        if (collisions.isEmpty()) {
            return null;
        } else {
            closestCollision = collisions.get(0);
            for (CollisionInfo collisionInfo : collisions) {
                if (collisionInfo.collisionPoint().distance(trajectory.start())
                        < closestCollision.collisionPoint().distance(trajectory.start())) {
                    closestCollision = collisionInfo;
                }
            }
            return closestCollision;
        }
    }
    /**
     * Returns the paddle of the game environment.
     *
     * @return the paddle of the game environment
     */
    public Paddle getPaddle() {
        return paddle;
    }
    /**
     * Returns the ball collection of the game environment.
     *
     * @return the ball collection of the game environment
     */
    public BallCollection getBalls() {
        return balls;
    }
    /**
     * Returns the background block of the game environment.
     *
     * @return the background block of the game environment
     */
    public Block getBackGround() {
        return backGround;
    }
}