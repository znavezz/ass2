import biuoop.DrawSurface;
/**
 * Represents an object in the game that can be collided with.
 * Provides methods to obtain the collision shape, draw the object,
 * handle a hit event, and add the object to a game.
 */
public interface Collidable {
    //Commands
    /**
     * Draws the object on the given DrawSurface.
     * @param surface the DrawSurface on which the object should be drawn
     */
    void drawOn(DrawSurface surface);
    /**
     * Adds the object to the specified game.
     * @param g the Game object to which the collidable should be added
     */
    void addToGame(Game g);
    //Queries
    /**
     * Returns the "collision shape" of the object.
     * @return the Rectangle object representing the collision shape
     */
    Rectangle getCollisionRectangle();
    /**
     * Notifies the object that we collided with it at the specified collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit (based on the force the object inflicted on us).
     * @param collisionPoint the Point object representing the collision point
     * @param currentVelocity the current Velocity object of the colliding object
     * @return the new Velocity object expected after the hit
     */
    Velocity hit(Point collisionPoint, Velocity currentVelocity);
}