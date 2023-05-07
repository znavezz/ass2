import biuoop.DrawSurface;

import java.util.ArrayList;

/**
 * Represents a collection of Ball objects as a Sprite.
 * Manages drawing, updating, and adding balls to a game.
 */
public class BallCollection implements Sprite {
    //Fields
    private ArrayList<Ball> balls = new ArrayList<Ball>();
    //Commands

    /**
     * Adds a Ball object to the collection.
     * @param b the Ball object to add
     */
    public void addBall(Ball b) {
        balls.add(b);
    }
    /**
     * Draws all the Ball objects in the collection on the provided DrawSurface.
     * @param d the DrawSurface to draw the balls on
     */
    public void drawOn(DrawSurface d) {
        for (Ball ball : balls) {
            ball.drawOn(d);
        }
    }
    /**
     * Updates the state of all the Ball objects in the collection based on the time passed.
     */
    public void timePassed() {
        for (Ball ball : balls) {
            ball.timePassed();
        }
    }

    /**
     * Adds all the Ball objects in the collection to a Game.
     * @param g the Game to add the balls to
     */
    public void addToGame(Game g) {
        for (Ball ball : balls) {
            ball.addToGame(g);
        }
    }
    //Queries
    /**
     *
     * @return the ArrayList of Ball objects of the balls in the collection.
     */
    public ArrayList<Ball> getBalls() {
        return balls;
    }
}
