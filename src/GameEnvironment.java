import java.awt.Color;
import java.util.ArrayList;

public class GameEnvironment {
    private int width = 850;
    private int height = 650;
    private int borderWidth = 25;
    private Paddle paddle;
    private Borders borders = new Borders(borderWidth, width, height);
    private ArrayList<Collidable> collidables = new ArrayList<Collidable>() {
        {
            try {
                add(borders.getLeft());
                add(borders.getTop());
                add(borders.getRight());
                add(borders.getBottom());
            } catch (Exception e) {
            }
        }
    };

    public GameEnvironment() {
    }

    public GameEnvironment(ArrayList<Collidable> collidables, Borders borders) {
        this.collidables = collidables;
        this.borders = borders;
        addCollidable(borders.getLeft());
        addCollidable(borders.getTop());
        addCollidable(borders.getRight());
        addCollidable(borders.getBottom());
    }

    public Borders getBorders() {
        return borders;
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

    public void setPaddle(Paddle paddle) {
        this.paddle = paddle;
    }

    public Paddle getPaddle() {
        return paddle;
    }
}