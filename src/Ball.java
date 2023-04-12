
import biuoop.DrawSurface;

import java.util.Random;
import java.awt.Color;

public class Ball {
    //Frame
    private static final int SCALINGFACTOR = 100;
    //Fields
    private int size;
    private Color color;
    private Point center;
    private Velocity velocity;
    private double leftBorder = 0;
    private double rightBorder = 800;
    private double upperBorder = 0;
    private double downBorder = 600;
    //for convenience so i want create a rand everytime
    private Random rand = new Random();

    //constructors
    public Ball() {
            this.center = Point.generateRandomPoint(leftBorder, upperBorder, rightBorder, downBorder);
            this.size = (int) rand.nextDouble(Math.min(rightBorder - leftBorder, downBorder - upperBorder) / 6) + 5;
            Color randColor = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
            this.color = randColor;
            this.velocity = Velocity.fromAngleAndSpeed(rand.nextDouble() * 2 * Math.PI, this.sizeToSpeed());
            this.fixBall();
    }

    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.size = r;
        this.color = color;
        this.velocity = Velocity.fromAngleAndSpeed(rand.nextDouble() * 2 * Math.PI, this.sizeToSpeed());
        this.fixBall();
    }

    public Ball(double x, double y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.size = r;
        this.color = color;
        this.velocity = Velocity.fromAngleAndSpeed(rand.nextDouble() * 2 * Math.PI, this.sizeToSpeed());
        this.fixBall();
    }

    public Ball(Color color) {

            this.center = Point.generateRandomPoint(leftBorder, upperBorder, rightBorder, downBorder);
            this.size = (int) rand.nextDouble(Math.min(rightBorder - leftBorder, downBorder - upperBorder) / 6) + 1;
            this.color = color;
            this.velocity = Velocity.fromAngleAndSpeed(rand.nextDouble() * 2 * Math.PI, this.sizeToSpeed());
            this.fixBall();
    }

    public Ball(Point center) {
            this.center = center;
            this.size = (int) rand.nextDouble(Math.min(rightBorder - leftBorder, downBorder - upperBorder) / 6) + 1;
            Color randColor = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
            this.color = randColor;
            this.velocity = Velocity.fromAngleAndSpeed(rand.nextDouble() * 2 * Math.PI, this.sizeToSpeed());
            this.fixBall();
    }

    public Ball(double x, double y) {
            this.center.setX(x);
            this.center.setY(y);
            this.size = (int) rand.nextDouble(Math.min(rightBorder - leftBorder, downBorder - upperBorder) / 6) + 1;
            Color randColor = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
            this.color = randColor;
            this.velocity = Velocity.fromAngleAndSpeed(rand.nextDouble() * 2 * Math.PI, this.sizeToSpeed());
            this.fixBall();
    }

    public Ball(int size) {
            this.center = Point.generateRandomPoint(leftBorder, upperBorder, rightBorder, downBorder);
            this.size = size;
            Color randColor = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
            this.color = randColor;
            this.velocity = Velocity.fromAngleAndSpeed(rand.nextDouble() * 2 * Math.PI, this.sizeToSpeed());
            this.fixBall();
    }

    public Ball(Point center, int r) {
        this.center = center;
        this.size = r;
        Color randColor = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
        this.color = randColor;
        this.velocity = Velocity.fromAngleAndSpeed(rand.nextDouble() * 2 * Math.PI, this.sizeToSpeed());
        this.fixBall();
    }

    public Ball(Point center, int r, double leftBorder, double upperBorder, double rightBorder, double downBorder) {
        this.center = center;
        this.size = r;
        Color randColor = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
        this.color = randColor;
        this.velocity = Velocity.fromAngleAndSpeed(rand.nextDouble() * 2 * Math.PI, this.sizeToSpeed());
        this.upperBorder = upperBorder;
        this.leftBorder = leftBorder;
        this.downBorder = downBorder;
        this.rightBorder = rightBorder;
        this.fixBall();
    }

    public Ball(int r, double leftBorder, double upperBorder, double rightBorder, double downBorder) {
        this.size = r;
        Color randColor = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
        this.color = randColor;
        this.velocity = Velocity.fromAngleAndSpeed(rand.nextDouble() * 2 * Math.PI, this.sizeToSpeed());
        this.upperBorder = upperBorder;
        this.leftBorder = leftBorder;
        this.downBorder = downBorder;
        this.rightBorder = rightBorder;
        this.center = Point.generateRandomPoint(leftBorder, upperBorder, rightBorder, downBorder);
        this.fixBall();
    }

    public Ball(Point center, int size, Color color, double leftBorder, double upperBorder, double rightBorder, double downBorder) {
        this.center = center;
        this.size = size;
        Color randColor = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
        this.color = randColor;
        this.velocity = Velocity.fromAngleAndSpeed(rand.nextDouble() * 2 * Math.PI, this.sizeToSpeed());
        this.upperBorder = upperBorder;
        this.leftBorder = leftBorder;
        this.downBorder = downBorder;
        this.rightBorder = rightBorder;
        this.fixBall();
    }

    public Ball(Point center, int r, Velocity v, double lBorder, double uBorder, double rBorder, double dBorder) {
        this.center = center;
        this.size = r;
        Color randColor = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
        this.color = randColor;
        this.velocity = v;
        this.upperBorder = uBorder;
        this.leftBorder = lBorder;
        this.downBorder = dBorder;
        this.rightBorder = rBorder;
        this.fixBall();
    }



    // accessors
    public int getX() {
        return (int) Math.round(this.center.getX());
    }

    public int getY() {
        return (int) Math.round(this.center.getY());
    }

    public int getSize() {
        return this.size;
    }

    public java.awt.Color getColor() {
        return this.color;
    }

    public Point getCenter() {
        return this.center;
    }

    // setters
    public void setX(double x) {
        this.center.setX(x);
    }

    public void setY(double y) {
        this.center.setX(y);
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void getInfo() {
        System.out.println("Size = " + size);
        System.out.println("Color = " + color.toString());
        center.toString();
        System.out.println("Left border = " + leftBorder);
        System.out.println("Upper border = " + upperBorder);
        System.out.println("Right border = " + rightBorder);
        System.out.println("Down border = " + downBorder);
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    // draw the ball on the given DrawSurface
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) Math.round(this.center.getX()), (int) Math.round(this.center.getY()), this.size);
    }

    public boolean isOutOfTop() {
        return (this.center.getY() - this.upperBorder <= this.size);
    }

    public boolean isOutOfBottom() {
        return (this.center.getY() + this.size >= downBorder);
    }

    public boolean isOutOfRight() {
        return (this.center.getX() + this.size >= rightBorder);
    }

    public boolean isOutOfLeft() {
        return (this.center.getX() - this.leftBorder <= this.size);
    }

    public boolean isOutOfWidth() {
        return this.isOutOfRight() || this.isOutOfLeft();
    }

    public boolean isOutOfHeight() {
        return this.isOutOfBottom() || this.isOutOfTop();
    }

    public boolean isOutOfFrame() {
        return this.isOutOfWidth() || this.isOutOfHeight();
    }

    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    public void setVelocity(double dx, double dy) {
        this.velocity.setDx(dx);
        this.velocity.setDy(dy);
    }

    public Velocity getVelocity() {
        return this.velocity;
    }
    public void fixLeft() {
        if (isOutOfLeft()) {
            this.center.setX(this.size + 1);
        }
    }
    public void fixRight() {
        if (isOutOfRight()) {
            this.center.setX(rightBorder - this.size - 1);
        }
    }
    public void fixTop() {
        if (isOutOfTop()) {
            this.center.setY(this.size + 1);
        }
    }
    public void fixBottom() {
        if (isOutOfBottom()) {
            this.center.setY(downBorder - this.size - 1);
        }
    }

    public void fixBall() {
        // edges
        if (isOutOfTop()) {
            this.center.setY(this.size + upperBorder + 1);
        }
        if (isOutOfBottom()) {
            this.center.setY(downBorder - this.size - 1);
        }
        if (isOutOfLeft()) {
            this.center.setX(this.size + leftBorder + 1);
        }
        if (isOutOfRight()) {
            this.center.setX(rightBorder - this.size - 1);
        }
    }
    public void moveOneStep() {
//        if (this.isOutOfWidth() && this.isOutOfHeight()) {
//            this.fixBall();
//            this.setVelocity(-(this.getVelocity().getDx()), -(this.getVelocity().getDy()));
//            this.center = this.getVelocity().applyToPoint(this.center);
//        }
        Ball check = new Ball(this.center, this.size, this.velocity,
                this.leftBorder, this.upperBorder, this.rightBorder, this.downBorder);
        check.center = check.getVelocity().applyToPoint(check.center);

        if (check.isOutOfHeight()) {
//            this.fixBall();
            this.setVelocity(this.getVelocity().getDx(), -(this.getVelocity().getDy()));
            this.center = this.getVelocity().applyToPoint(this.center);
        }
        if (check.isOutOfWidth()) {
//            this.fixBall();
            this.setVelocity(-(this.getVelocity().getDx()), this.getVelocity().getDy());
            this.center = this.getVelocity().applyToPoint(this.center);
        }
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    private double sizeToSpeed() {
        double speed = SCALINGFACTOR / this.size;
        if (this.size > 50) {
            speed = SCALINGFACTOR / 50;
        }
        return speed;
    }

}