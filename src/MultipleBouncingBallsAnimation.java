// Import necessary packages

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.util.ArrayList;
import java.util.Random;

public class MultipleBouncingBallsAnimation {
    private static Random rand = new Random();
    static final int WIDTH = 800;
    static final int HEIGHT = 600;

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
            }
        }
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            for (Ball ball : balls) {
                ball.moveOneStep();
                ball.drawOn(d);
            }
            gui.show(d);
            sleeper.sleepFor(50);
        }
    }
}
