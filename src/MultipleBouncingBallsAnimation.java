// Import necessary packages

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;


import java.awt.*;
import java.util.ArrayList;

//public class MultipleBouncingBallsAnimation {
//    static final int WIDTH = 800;
//    static final int HEIGHT = 600;
//
//    public static void main(String[] args) {
//        GUI gui = new GUI("MultipleBouncingBallsAnimation", WIDTH, HEIGHT);
//        Sleeper sleeper = new Sleeper();
//        ArrayList<Ball> balls = new ArrayList<>();
//        for (String string : args) {
//            Ball newBall = new Ball(Integer.parseInt(string), 0, 0, WIDTH, HEIGHT);
//            newBall.setCenter(newBall.generateRandomPoint());
//            balls.add(newBall);
//        }
//        while (true) {
//            DrawSurface d = gui.getDrawSurface();
//            for (Ball ball : balls) {
//                ball.moveOneStep();
//                ball.drawOn(d);
////                ball.getInfo();
//            }
//            gui.show(d);
//            sleeper.sleepFor(50);
//        }
//    }
//}
public class MultipleBouncingBallsAnimation {
    static final int WIDTH = 800;
    static final int HEIGHT = 600;

    public static void main(String[] args) {
        GUI gui = new GUI("MultipleBouncingBallsAnimation", WIDTH, HEIGHT);
        Sleeper sleeper = new Sleeper();
        ArrayList<Ball> balls = new ArrayList<>();
        Borders borders = new Borders(WIDTH, HEIGHT);
        for (String string : args) {
            int ballSize = Integer.parseInt(string);
            balls.add(new Ball(ballSize, borders));
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
