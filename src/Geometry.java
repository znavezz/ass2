import java.util.Random;

/**
 * The Geometry class provides utility methods for working with geometric calculations
 * and operations, such as comparing double values and generating random angles.
 * It is also holds static member like EPSILON which used as a treshold for comparing.
 */
public class Geometry {
    //Fields
    public static final double EPSILON = 0.00001;
    public static final Random RAND = new Random();
    /**
     * Compares two double values for equality, considering a margin of error (EPSILON).
     * @param a the first double value.
     * @param b the second double value.
     * @return true if the difference between a and b is less than EPSILON, false otherwise.
     */
   //Queries
    public static boolean doubleEquals(double a, double b) {
        return Math.abs(a - b) < EPSILON;
    }
    /**
     * Generates a random angle between 0 and 360 degrees, excluding multiples of 90 degrees.
     * @return a random angle in degrees.
     */
    public static double getRandomAngle() {
        double angle;
        do {
            angle = RAND.nextDouble() * 360;
        } while (angle % 90 == 0);
        return angle;
    }
}
