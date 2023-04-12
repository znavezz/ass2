


public class BouncingBallAnimation {
    public static void main(String[] args) {
        Point start = new Point(Double.parseDouble(args[0]), Double.parseDouble(args[1]));
        double dx = Double.parseDouble(args[2]);
        double dy = Double.parseDouble(args[3]);
        BallsTest1.drawAnimation(start, dx, dy);
    }
}
