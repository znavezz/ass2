import biuoop.DrawSurface;

public interface Collidable {
    // Return the "collision shape" of the object.
    Rectangle getCollisionRectangle();
    void drawOn(DrawSurface surface);

    // Notify the object that we collided with it at collisionPoint with
    // a given velocity.
    // The return is the new velocity expected after the hit (based on
    // the force the object inflicted on us).
    Velocity hit(Point collisionPoint, Velocity currentVelocity);
    void addToGame(Game g);
}