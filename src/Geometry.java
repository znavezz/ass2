import java.util.Random;

public class Geometry {

    public static final double EPSILON = 0.00001;
    public static final Random RAND = new Random();

    /**
     * @param a
     * @param b
     * @return
     */
    public static boolean doubleEquals(double a, double b) {
        return Math.abs(a - b) < EPSILON;
    }
    public static double getRandomAngle() {
        double angle;
        do {
            angle = RAND.nextDouble() * 360;
        } while (angle % 90 == 0);
        return angle;
    }
}
