//import biuoop.DrawSurface;
//import biuoop.GUI;
//import biuoop.Sleeper;
//
//import java.awt.*;
//
//public class TestGameEnvironment {
//    public static void main(String[] args) {
//        GUI gui = new GUI("Game Environment Test", GameEnvironment.,  GameEnvironment.DEFAULT_HEIGHT);
//        Sleeper sleeper = new Sleeper();
//        GameEnvironment gameEnvironment = new GameEnvironment();
//        int blockWidth = 75;
//        int blockHeight = 25;
//        int widthSpace = 50;
//        int heightSpace = 25;
//        for (int i = blockWidth; i < gameEnvironment.DEFAULT_HEIGHT / 2; i += blockHeight + heightSpace) {
//            Color blockColor = new Color(Geometry.RAND.nextInt(255), Geometry.RAND.nextInt(255), Geometry.RAND.nextInt(255));
//            for (int j = blockWidth; j + blockWidth + widthSpace < gameEnvironment.DEFAULT_WIDTH; j += blockWidth + widthSpace) {
//                gameEnvironment.addCollidable(new Block(new Rectangle(new Point(j, i), blockWidth, blockHeight), blockColor));
//            }
//        }
//        Ball ball = new Ball(gameEnvironment, new Point(300, 550));
//        while (true) {
//            DrawSurface d = gui.getDrawSurface();
//            for (Collidable collidable : gameEnvironment.getCollidables()) {
//                collidable.drawOn(d);
//            }
//            ball.moveOneStep();
//            ball.drawOn(d);
//            gui.show(d);
//            sleeper.sleepFor(60);
//        }
//    }
//}
