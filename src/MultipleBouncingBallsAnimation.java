// Import necessary packages

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.util.ArrayList;
import java.util.Random;
/**
 * The MultipleBouncingBallsAnimation class creates an animation of multiple bouncing balls
 * within a rectangular area. It takes an array of integers as input, where each integer
 * represents the radius of a ball. If the input is not an integer or there are not enough balls,
 * random balls are generated.
 */
public class MultipleBouncingBallsAnimation {
    private static Random rand = new Random();
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    /**
     * The main method runs the multiple bouncing balls animation.
     * @param args an array of strings, where each string represents the radius of a ball.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("MultipleBouncingBallsAnimation", WIDTH, HEIGHT);
        Sleeper sleeper = new Sleeper();
        ArrayList<Ball> balls = new ArrayList<>();
        Borders borders = new Borders(WIDTH, HEIGHT);
        for (String string : args) {
            try {
                balls.add(new Ball(Integer.parseInt(string), borders));
            } catch (Exception e) {
                balls.add(new Ball((int) rand.nextDouble(Math.min(borders.getRight() - borders.getLeft(),
                        borders.getDown() - borders.getUp()) / 6) + 10, borders));
                System.out.println("The input doesn't match the required type (int).");
                System.out.println("Random point has been generated.");
                if (balls.size() < 3) {
                    balls.add(new Ball((int) rand.nextDouble(Math.min(borders.getRight() - borders.getLeft(),
                            borders.getDown() - borders.getUp()) / 6) + 10, borders));
                    balls.add(new Ball((int) rand.nextDouble(Math.min(borders.getRight() - borders.getLeft(),
                            borders.getDown() - borders.getUp()) / 6) + 10, borders));
                    balls.add(new Ball((int) rand.nextDouble(Math.min(borders.getRight() - borders.getLeft(),
                            borders.getDown() - borders.getUp()) / 6) + 10, borders));
                    System.out.println("There want enough Balls");
                    System.out.println("Random Balls has been generated.");
                }
            }
        }
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            for (Ball ball : balls) {
                ball.moveOneStep();
                ball.drawOn(d);
            }
            gui.show(d);
            sleeper.sleepFor(30);
        }
    }
}
