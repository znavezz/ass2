// Import necessary packages

import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.util.Random;
import java.awt.Color;

public class BallsTest1 {
    static final int WIDTH = 800;
    static final int HEIGHT = 300;

    public static void main(String[] args) {
        Point newPoint = Point.generateRandomPoint(0, 0, WIDTH, HEIGHT);
        BallsTest1.drawAnimation(Point.generateRandomPoint(0, 0, WIDTH, HEIGHT), 5, 5);
    }


    public static void drawAnimation(Point start, double dx, double dy) {
        GUI gui = new GUI("BouncingBallAnimation", WIDTH, HEIGHT);
        Sleeper sleeper = new Sleeper();
        Ball ball = new Ball(new Point(10, 10), 30, java.awt.Color.BLACK, 0, 0, WIDTH, HEIGHT);
        Ball ball2 = new Ball(Point.generateRandomPoint(0, 0, WIDTH, HEIGHT));
        Ball ball3 = new Ball(Point.generateRandomPoint(0, 0, WIDTH, HEIGHT));
        while (true) {
            Random rand = new Random();
            ball.moveOneStep();
            ball.setColor(new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
            ball2.moveOneStep();
            ball3.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            ball2.drawOn(d);
            ball3.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }

//    static public void drawAnimation(Point start, double dx, double dy) {
//        GUI gui = new GUI("title",WIDTH,HEIGHT);
//        Sleeper sleeper = new Sleeper();
//        Ball ball = new Ball(start.getX(), start.getY(), 30, java.awt.Color.BLACK);
//        ball.setVelocity(dx, dy);
//        while (true) {
//            ball.moveOneStep();
//            DrawSurface d = gui.getDrawSurface();
//            ball.drawOn(d);
//            gui.show(d);
//            sleeper.sleepFor(50);  // wait for 50 milliseconds.
//        }
//    }


}