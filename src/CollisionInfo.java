/**
 * Represents collision information, including the collision point and the collidable object involved in the collision.
 */
public class CollisionInfo {
    //Fields
    private Point collisionPoint;
    private Collidable collisionObject;
    //Constructors
    /**
     * Constructs a new CollisionInfo object with the given collision point and collidable object.
     * @param collisionPoint the Point object representing the collision point
     * @param collisionObject the Collidable object involved in the collision
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }
    //Queries
    /**
     * Returns the collision point.
     * @return the Point object representing the collision point
     */
    public Point collisionPoint() {
        return collisionPoint;
    }
    /**
     * Returns the collidable object involved in the collision.
     * @return the Collidable object involved in the collision
     */
    public Collidable collisionObject() {
        return collisionObject;
    }
}