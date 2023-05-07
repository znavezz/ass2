import biuoop.DrawSurface;

import java.awt.Color;
/**
 * Represents a Block, which is a Rectangle object with a color, implementing Collidable and Sprite interfaces.
 * Handles drawing, updating, and collision detection for the block.
 */
public class Block implements Collidable, Sprite {
    //Fields
    private Rectangle rectangle;
    private Color color;
    //Constructors
    /**
     * Constructor for the Block class, with a specified Rectangle and color.
     * @param rectangle the Rectangle object representing the block's shape
     * @param color the color of the block
     */
    public Block(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.color = color;
    }
    /**
     * Constructor for the Block class with a specified Rectangle, and a random color.
     * @param rectangle the Rectangle object representing the block's shape
     */
    public Block(Rectangle rectangle) {
        this.rectangle = rectangle;
        color = new Color(Geometry.RAND.nextInt(255), Geometry.RAND.nextInt(255), Geometry.RAND.nextInt(255));
    }
    //Commands
    /**
     * Updates the state of the block based on the time passed.
     * (Currently empty as blocks do not update)
     */
    public void timePassed() {
    }
    /**
     * Draws the block on the provided DrawSurface.
     * @param surface the DrawSurface to draw the block on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) Math.round(rectangle.getTopLeft().getX()),
                (int) Math.round(rectangle.getTopLeft().getY()), (int) rectangle.getWidth(),
                (int) rectangle.getHeight());
        surface.setColor(this.color);
        surface.fillRectangle((int) Math.round(rectangle.getTopLeft().getX()),
                (int) Math.round(rectangle.getTopLeft().getY()), (int) rectangle.getWidth(),
                (int) rectangle.getHeight());
    }
    /**
     * Adds the block to the specified game as a collidable and a sprite.
     * @param g the Game object to add the block to
     */
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
    //Queries
    /**
     * Returns a string representation of the block, including its shape and color.
     * @return a string representation of the block
     */
    public String toString() {
        return "Block: " + rectangle.toString() + ".     Color - " + color.toString();
    }
    /**
     * Returns the "collision shape" of the object.
     * @return the Rectangle object representing the block's shape
     */
    public Rectangle getCollisionRectangle() {
        return getRectangle();
    }
    /**
     * Returns the Rectangle object representing the block's shape.
     * @return the Rectangle object representing the block's shape
     */
    public Rectangle getRectangle() {
        return this.rectangle;
    }

    /**
     * Notifies the block that a collision occurred at the specified collisionPoint with a given velocity.
     * Calculates the new velocity based on the collision point and the object's current velocity.
     * This method takes into account collisions with corners and sides of the block.
     * @param collisionPoint the Point object representing the collision point
     * @param currentVelocity the current Velocity object of the colliding object
     * @return the new Velocity object expected after the hit
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        //if the collision is on one of the corner of the rectangle
        if ((collisionPoint.isOnLine(rectangle.getLeftSide()) || collisionPoint.isOnLine(rectangle.getRightSide()))
                && (collisionPoint.isOnLine(rectangle.getTopSide())
                || collisionPoint.isOnLine(rectangle.getBottomSide()))) {
            //if it is the top left corner
            if (collisionPoint.isOnLine(rectangle.getTopSide()) && collisionPoint.isOnLine(rectangle.getLeftSide())) {
                if (currentVelocity.getDx() > 0) {
                    if (currentVelocity.getDy() >= 0) {
                        return new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());
                    } else {
                        return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
                    }
                } else {
                    return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());

                }
            } else if (collisionPoint.isOnLine(rectangle.getTopSide())
                    && collisionPoint.isOnLine(rectangle.getRightSide())) {
                if (currentVelocity.getDx() < 0) {
                    if (currentVelocity.getDy() >= 0) {
                        return new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());

                    } else {
                        return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
                    }
                } else {
                    return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
                }
            } else if (collisionPoint.isOnLine(rectangle.getBottomSide())
                    && collisionPoint.isOnLine(rectangle.getRightSide())) {
                if (currentVelocity.getDx() < 0) {
                    if (currentVelocity.getDy() <= 0) {
                        return new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());
                    } else {
                        return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
                    }
                } else {
                    return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
                }
            } else if (collisionPoint.isOnLine(rectangle.getBottomSide())
                    && collisionPoint.isOnLine(rectangle.getLeftSide())) {
                if (currentVelocity.getDy() < 0) {
                    if (currentVelocity.getDx() >= 0) {
                        return new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());
                    } else {
                        return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
                    }
                } else {
                    return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
                }
            }
        } else {
            if (collisionPoint.isOnLine(rectangle.getLeftSide()) || collisionPoint.isOnLine(rectangle.getRightSide())) {
                return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
            } else {
                return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
            }
        }
        return currentVelocity;
    }
}
