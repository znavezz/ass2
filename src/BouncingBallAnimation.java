import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.util.Random;
/**
 * @author Nave Zehoray 206388746 < znavez@gmail.com >
 * The BouncingBallAnimation class is responsible for creating and running the animation
 * of a ball bouncing within the borders of a window.
 */

public class BouncingBallAnimation {
    private static Random rand = new Random();
    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    private static Borders borders = new Borders(WIDTH, HEIGHT);
    /**
     * The main method runs the BouncingBallAnimation.
     * @param args optional command-line arguments for start point and velocity.
     */
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
            dx = 0;
            dy = 0;
            System.out.println("The input doesn't match the required type (int).");
            System.out.println("Velocity 0 has been generated.");
        }
        drawAnimation(start, dx, dy);
    }
    /**
     * Draws and updates the animation of the bouncing ball.
     * @param start the initial position of the ball.
     * @param dx the x component of the ball's velocity.
     * @param dy the y component of the ball's velocity.
     */
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
            sleeper.sleepFor(30);  // wait for 50 milliseconds.
        }
    }
}
