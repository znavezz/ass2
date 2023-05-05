import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;

public class Paddle implements Sprite, Collidable {
    private int paddleWidth = 90;
    private int paddleHeight = 40;
    private ArrayList<Ball> balls = new ArrayList<>();
    private GameEnvironment environment = new GameEnvironment();
    private Rectangle rectangle = new Rectangle(new Point(environment.getBorders().getLeft().getRectangle().getRightSide().start().getX()
            + (environment.getBorders().getRight().getRectangle().getLeftSide().start().getX()
            + environment.getBorders().getLeft().getRectangle().getRightSide().start().getX()) / 2,
            (environment.getBorders().getBottom().getRectangle().getTopSide().start().getY() - paddleHeight)), paddleWidth, paddleHeight);
    private double speed = 15;
    private Color color =  new Color(Geometry.RAND.nextInt(255), Geometry.RAND.nextInt(255), Geometry.RAND.nextInt(255));
    private KeyboardSensor keyboard;
    public Paddle(KeyboardSensor keyboard) {
        this.keyboard = keyboard;
    }

    public void moveLeft() {
        double nextStep = rectangle.getTopLeft().getX() - speed;
        if (environment.getBorders().getLeft().getRectangle().getRightSide().start().getX() > nextStep) {
            rectangle.setTopLeft(new Point(environment.getBorders().getLeft().getRectangle().getRightSide().start().getX(),
                    rectangle.getTopLeft().getY()));
        } else {
            rectangle.setTopLeft(new Point(nextStep, rectangle.getTopLeft().getY()));
        }
    }
    public void moveRight() {
        double nextStep = rectangle.getTopLeft().getX() + speed;
        if (environment.getBorders().getRight().getRectangle().getLeftSide().start().getX() - rectangle.getWidth() < nextStep) {
            rectangle.setTopLeft(new Point(environment.getBorders().getRight().getRectangle().getLeftSide().start().getX()
                    - rectangle.getWidth(), rectangle.getTopLeft().getY()));
        } else {
            rectangle.setTopLeft(new Point(rectangle.getTopLeft().getX() + speed, rectangle.getTopLeft().getY()));
        }
    }

    // Sprite
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawRectangle((int) rectangle.getTopLeft().getX(), (int) rectangle.getTopLeft().getY(),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());
        d.setColor(color);
        d.fillRectangle((int) rectangle.getTopLeft().getX(), (int) rectangle.getTopLeft().getY(),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());
    }

    // Collidable
    public Rectangle getCollisionRectangle() {
        return rectangle;
    }
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        ArrayList<Line> fiveRegion = getFiveRegions();
        if (collisionPoint.isOnLine(fiveRegion.get(0))) {
//            if (currentVelocity.getDy() < 0) {
//                return Velocity.fromAngleAndSpeed(240, currentVelocity.getMagnitude());
//            } else {
                return Velocity.fromAngleAndSpeed(300, currentVelocity.getMagnitude());
           // }
        } else if (collisionPoint.isOnLine(fiveRegion.get(1))) {
            return Velocity.fromAngleAndSpeed(330, currentVelocity.getMagnitude());
        } else if (collisionPoint.isOnLine(fiveRegion.get(2))) {
            return Velocity.fromAngleAndSpeed(0, currentVelocity.getMagnitude());
        } else if (collisionPoint.isOnLine(fiveRegion.get(3))) {
            return Velocity.fromAngleAndSpeed(30, currentVelocity.getMagnitude());
        } else if (collisionPoint.isOnLine(fiveRegion.get(4))) {
//            if (currentVelocity.getDy() < 0) {
//                return Velocity.fromAngleAndSpeed(120, currentVelocity.getMagnitude());
//            } else {
                return Velocity.fromAngleAndSpeed(60, currentVelocity.getMagnitude());
            //}
        } else if (collisionPoint.isOnLine(rectangle.getLeftSide())) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        } else {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
    }
    // Add this paddle to the game.
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    public int getPaddleWidth() {
        return paddleWidth;
    }

    public void setPaddleWidth(int paddleWidth) {
        this.paddleWidth = paddleWidth;
    }

    public int getPaddleHeight() {
        return paddleHeight;
    }

    public void setPaddleHeight(int paddleHeight) {
        this.paddleHeight = paddleHeight;
    }

    public GameEnvironment getEnvironment() {
        return environment;
    }

    public void setEnvironment(GameEnvironment environment) {
        this.environment = environment;
    }
    public ArrayList<Line> getFiveRegions() {
        double regionY = rectangle.getTopLeft().getY();
        ArrayList<Line> fiveRegions = new ArrayList<Line>();
        for (int i = 0; i < 5; i++) {
            fiveRegions.add(new Line(rectangle.getTopLeft().getX() + ((i / 5) * rectangle.getWidth()),
                    regionY, rectangle.getTopLeft().getX() + (((i + 1) / 5) * rectangle.getWidth()), regionY));
        }
        return fiveRegions;
    }
    public void setKeyboard(KeyboardSensor keyboard) {
        this.keyboard = keyboard;
    }
    public void addBall(Ball b) {
        balls.add(b);
    }
    public String toString() {
        return "Paddle: " + rectangle.toString() + ".     Color - " + color.toString();
    }
}