import biuoop.DrawSurface;

import java.awt.Color;

public class Block implements Collidable, Sprite {
    private Rectangle rectangle;
    private Color color;
    private int numberOfHits = 0;

    public Block(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.color = color;
    }

    public Block(Rectangle rectangle) {
        this.rectangle = rectangle;
        color = new Color(Geometry.RAND.nextInt(255), Geometry.RAND.nextInt(255), Geometry.RAND.nextInt(255));
    }

    // Return the "collision shape" of the object.
    public Rectangle getCollisionRectangle() {
        return getRectangle();
    }

    // Notify the object that we collided with it at collisionPoint with
    // a given velocity.
    // The return is the new velocity expected after the hit (based on
    // the force the object inflicted on us).
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        numberOfHits++;
        //if the collision is on one of the corner of the rectangle
        if ((collisionPoint.isOnLine(rectangle.getLeftSide()) || collisionPoint.isOnLine(rectangle.getRightSide()))
                && (collisionPoint.isOnLine(rectangle.getTopSide()) || collisionPoint.isOnLine(rectangle.getBottomSide()))) {
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
            } else if (collisionPoint.isOnLine(rectangle.getTopSide()) && collisionPoint.isOnLine(rectangle.getRightSide())) {
                if (currentVelocity.getDx() < 0) {
                    if (currentVelocity.getDy() >= 0) {
                        return new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());

                    } else {
                        return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
                    }
                } else {
                    return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
                }
            } else if (collisionPoint.isOnLine(rectangle.getBottomSide()) && collisionPoint.isOnLine(rectangle.getRightSide())) {
                if (currentVelocity.getDx() < 0) {
                    if (currentVelocity.getDy() <= 0) {
                        return new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());
                    } else {
                        return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
                    }
                } else {
                    return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
                }
            } else {
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
        } else if (collisionPoint.isOnLine(rectangle.getLeftSide()) || collisionPoint.isOnLine(rectangle.getRightSide())) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        } else {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
    }

    public Rectangle getRectangle() {
        return this.rectangle;
    }

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

    public void timePassed() {

    }

    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
    public String toString() {
        return "Block: " + rectangle.toString() + ".     Color - " + color.toString();
    }
}
