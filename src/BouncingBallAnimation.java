import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.util.Random;

public class BouncingBallAnimation {
    private static Random rand = new Random();
    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    private static Borders borders = new Borders(WIDTH, HEIGHT);
    public static void main(String[] args) {
        Point start;
        double dx;
        double dy;
        try {
            start = new Point(Double.parseDouble(args[0]), Double.parseDouble(args[1]));
        } catch (Exception e) {
            start = borders.generateRandomPoint();
            System.out.println("The input doesn't match the required type (int).");
            System.out.println("Random point has been generated.");
        }
        try {
            dx = Double.parseDouble(args[2]);
            dy = Double.parseDouble(args[3]);
        } catch (Exception e) {
            dx = rand.nextDouble() * (borders.getRight() - borders.getLeft()) / 4;
            dy = rand.nextDouble() * (borders.getDown() - borders.getUp()) / 4;
            System.out.println("The input doesn't match the required type (int).");
            System.out.println("Random velocity has been generated.");
        }
        drawAnimation(start, dx, dy);
    }

    public static void drawAnimation(Point start, double dx, double dy) {
        GUI gui = new GUI("BouncingBallAnimation", (int) (borders.getRight() - borders.getLeft()),
                (int) (borders.getDown() - borders.getUp()));
        Sleeper sleeper = new Sleeper();
        Ball ball = new Ball(start, borders, dx, dy);
        while (true) {
            ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(25);  // wait for 50 milliseconds.
        }
    }
}
