import biuoop.DrawSurface;
/**
 * The Sprite interface represents a drawable and updatable object in a game.
 */
public interface Sprite {
    //Commands
    /**
     * Draws the sprite on the given DrawSurface.
     * @param d the DrawSurface on which to draw the sprite
     */
    void drawOn(DrawSurface d);
    /**
     * Notifies the sprite that a unit of time has passed.
     * This method is typically used to update the state of the sprite.
     */
    void timePassed();
    /**
     * Adds the sprite to the specified game.
     * @param g the game to which the sprite should be added
     */
    void addToGame(Game g);
}