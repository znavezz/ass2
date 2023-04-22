public class Geometry {

    static final double EPSILON = 0.00001;

    /**
     * @param a
     * @param b
     * @return
     */
    public static boolean doubleEquals(double a, double b) {
        return Math.abs(a - b) < EPSILON;
    }
}
