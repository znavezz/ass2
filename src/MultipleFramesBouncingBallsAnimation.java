import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
/**
 * The MultipleFramesBouncingBallsAnimation class creates an animation of multiple bouncing balls
 * within two rectangular areas (frames) with different background colors. It takes an array of integers
 * as input, where each integer represents the radius of a ball. If the input is not an integer or there
 * are not enough balls, random balls are generated.
 */
public class MultipleFramesBouncingBallsAnimation {
    private static Random rand = new Random();
    /**
     * The main method runs the multiple bouncing balls animation within two frames.
     * @param args an array of strings, where each string represents the radius of a ball.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("MultipleFramesBouncingBallsAnimation", 800, 600);
        Sleeper sleeper = new Sleeper();
        Borders greyFrame = new Borders(50, 50, 500, 500);
        Borders yellowFrame = new Borders(450, 450, 600, 600);
        ArrayList<Ball> inGreyBalls = new ArrayList<>();
        ArrayList<Ball> inYellowBalls = new ArrayList<>();
        for (int i = 0; i < (args.length / 2); i++) {
            try {
                inGreyBalls.add(new Ball(Integer.parseInt(args[i]), greyFrame));
            } catch (Exception e) {
                inGreyBalls.add(new Ball(greyFrame));
                System.out.println("The input doesn't match the required type (int).");
                System.out.println("Random Ball in grey frame has been generated.");
            }
        }
        for (int i = (args.length / 2); i < args.length; i++) {
            try {
                inYellowBalls.add(new Ball(Integer.parseInt(args[i]), yellowFrame));
            } catch (Exception e) {
                inYellowBalls.add(new Ball(yellowFrame));
                System.out.println("The input doesn't match the required type (int).");
                System.out.println("Random Ball in yellow frame has been generated.");
            }
        }
        if (inGreyBalls.size() < 3) {
            inGreyBalls.add(new Ball(greyFrame));
            inGreyBalls.add(new Ball(greyFrame));
            inGreyBalls.add(new Ball(greyFrame));
            System.out.println("There want enough Balls");
            System.out.println("Random Balls in grey frame has been generated.");
        }
        if (inYellowBalls.size() < 3) {
            inYellowBalls.add(new Ball(yellowFrame));
            inYellowBalls.add(new Ball(yellowFrame));
            inYellowBalls.add(new Ball(yellowFrame));
            System.out.println("There want enough Balls");
            System.out.println("Random Balls in grey frame has been generated.");
        }
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            d.setColor(Color.gray);
            d.fillRectangle(50, 50, 450, 450);
            for (Ball ball : inGreyBalls) {
                ball.moveOneStep();
                ball.drawOn(d);
            }
            d.setColor(Color.YELLOW);
            d.fillRectangle(450, 450, 150, 150);
            for (Ball ball : inYellowBalls) {
                ball.moveOneStep();
                ball.drawOn(d);
            }
            gui.show(d);
            sleeper.sleepFor(30);
        }


    }
}
