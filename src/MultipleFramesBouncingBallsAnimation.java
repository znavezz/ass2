import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.ArrayList;

public class MultipleFramesBouncingBallsAnimation {
    public static void main(String[] args) {
        GUI gui = new GUI("title", 800, 600);
        Sleeper sleeper = new Sleeper();
        ArrayList<Ball> inGreyBalls = new ArrayList<>();
        ArrayList<Ball> inYellowBalls = new ArrayList<>();
        for (int i = 0; i < (args.length / 2); i++) {
            inGreyBalls.add(new Ball(Point.generateRandomPoint(50, 50, 500, 500),
                    Integer.parseInt(args[i]), 50, 50, 500, 500));
        }
        for (int i = (args.length / 2); i < args.length; i++) {
            inYellowBalls.add(new Ball(Point.generateRandomPoint(450, 450, 600, 600),
                    Integer.parseInt(args[i]), 450, 450, 600, 600));
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
            sleeper.sleepFor(500);
        }


    }
}
