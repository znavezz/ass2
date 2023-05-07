import biuoop.DrawSurface;
import java.util.ArrayList;
/**
 * The SpriteCollection class represents a collection of Sprite objects.
 * This class provides methods to add sprites to the collection, update them,
 * and draw them on a given DrawSurface.
 */
public class SpriteCollection {
    //Fields
    private ArrayList<Sprite> sprites = new ArrayList<Sprite>();
    //Commands
    /**
     * Adds a sprite to the collection.
     * @param s the sprite to be added
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }
    /**
     * Calls the timePassed() method on all sprites in the collection.
     * This method is used to update the state of all sprites.
     */
    public void notifyAllTimePassed() {
        for (Sprite sprite : sprites) {
            sprite.timePassed();
        }
    }
    /**
     * Draws all sprites in the collection on the given DrawSurface.
     * @param d the DrawSurface on which to draw the sprites
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : sprites) {
            sprite.drawOn(d);
        }
    }
    //Queries
    /**
     * Returns the ArrayList of sprites in the collection.
     * @return an ArrayList containing the sprites in the collection
     */
    public ArrayList<Sprite> getSprites() {
        return sprites;
    }
}