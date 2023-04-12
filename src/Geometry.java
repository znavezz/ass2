public class Geometry {

    static final double COMPARISON_TRESHOLD = 0.00001;

    /**
     * @param a
     * @param b
     * @return
     */
    public static boolean doubleEquals(double a, double b) {
        return Math.abs(a - b) < GeometryTester.Comparison_threshold;
    }
}
