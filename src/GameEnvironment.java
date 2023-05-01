import java.awt.*;
import java.util.ArrayList;

public class GameEnvironment {
    public static final int WIDTH = 850;
    public static final int HEIGHT = 600;
    public static final int DEFAULT_BORDERS_WIDTH = 10;

    private ArrayList<Collidable> collidables;
    private Block leftBorder;
    private Block upperBorder;
    private Block rightBorder;
    private Block downBorder;
    private int borderWidth;
    private int width;
    private int height;
    public GameEnvironment() {
        collidables = new ArrayList<Collidable>();
        leftBorder = new Block(new Rectangle(new Point(0, 0), BORDERS_WIDTH, HEIGHT), Color.BLACK);
        upperBorder = new Block(new Rectangle(new Point(0, 0), WIDTH, BORDERS_WIDTH), Color.BLACK);
        rightBorder = new Block(new Rectangle(new Point(WIDTH - BORDERS_WIDTH, 0), BORDERS_WIDTH, HEIGHT), Color.BLACK);
        downBorder = new Block(new Rectangle(new Point(0, HEIGHT - BORDERS_WIDTH), WIDTH, BORDERS_WIDTH), Color.BLACK);
        collidables.add(leftBorder);
        collidables.add(upperBorder);
        collidables.add(rightBorder);
        collidables.add(downBorder);
    }
    public GameEnvironment(ArrayList<Collidable> collidables, Block leftBorder, Block upperBorder,
                           Block rightBorder, Block downBorder) {
        this.collidables = collidables;
        this.leftBorder = leftBorder;
        this.upperBorder = upperBorder;
        this.rightBorder = rightBorder;
        this.downBorder = downBorder;
    }

    public Block getLeftBorder() {
        return leftBorder;
    }
    public Block getUpperBorder() {
        return upperBorder;
    }
    public Block getRightBorder() {
        return rightBorder;
    }
    public Block getDownBorder() {
        return downBorder;
    }
    public ArrayList<Collidable> getCollidables() {
        return collidables;
    }

    // add the given collidable to the environment.
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    // Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.
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

    public int getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}