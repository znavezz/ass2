import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Test {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static void main(String[] args) {
        Random rand = new Random();
        GUI gui = new GUI("Rectangle test", WIDTH, HEIGHT);
        DrawSurface d = gui.getDrawSurface();
        ArrayList<Line> recBorder = new ArrayList<>();
        ArrayList<Line> lines = new ArrayList<>();
        Rectangle rec1 = new Rectangle(new Point(50, 50), 200, 200);
        Rectangle rec2 = new Rectangle(new Point(200, 200), 200, 200);
        recBorder.add(rec1.getLeftSide());
        recBorder.add(rec1.getUpSide());
        recBorder.add(rec1.getRightSide());
        recBorder.add(rec1.getDownSide());
        recBorder.add(rec2.getLeftSide());
        recBorder.add(rec2.getUpSide());
        recBorder.add(rec2.getRightSide());
        recBorder.add(rec2.getDownSide());
        for (Line border : recBorder) {
            d.setColor(Color.BLACK);
            d.drawLine((int) Math.round(border.start().getX()), (int) Math.round(border.start().getY()),
                    (int) Math.round(border.end().getX()), (int) Math.round(border.end().getY()));
        }
        Line line1 = new Line(100, 50, 150, 250);
        Line line2 = new Line(50, 50, 250, 250);
        Line line3 = new Line(100, 50, 350, 50);
        Line line4 = new Line(250, 100, 250, 350);
        lines.add(line1);
        lines.add(line2);
        lines.add(line3);
        lines.add(line4);
        for (int i = 0; i < 25; i++) {
            int x = rand.nextInt(WIDTH) + 1; // get integer in range 1-WIDTH
            int y = rand.nextInt(HEIGHT) + 1; // get integer in range 1-HEIGHT
            int z = rand.nextInt(WIDTH) + 1; // get integer in range 1-WIDTH
            int w = rand.nextInt(HEIGHT) + 1; // get integer in range 1-HEIGHT
            Line line = new Line(x, y, z, w);
            lines.add(line);
        }
        for (Line line : lines) {
            d.setColor(Color.BLACK);
            d.drawLine((int) Math.round(line.start().getX()), (int) Math.round(line.start().getY()),
                    (int) Math.round(line.end().getX()), (int) Math.round(line.end().getY()));
            d.setColor(Color.BLUE);
            d.fillCircle((int) Math.round(line.middle().getX()),
                    (int) Math.round(line.middle().getY()), 3);
            System.out.println("---------Rectangle 1----------");
            for (Point point : rec1.intersectionPoints(line)) {
                System.out.println("distance: " + point.distance(line.start()));
                d.setColor(Color.RED);
                d.fillCircle((int) Math.round(point.getX()), (int) Math.round(point.getY()), 3);
            }
            System.out.println("---------Rectangle 2----------");
            for (Point point : rec2.intersectionPoints(line)) {
                System.out.println("distance: " + point.distance(line.start()));
                d.setColor(Color.RED);
                d.fillCircle((int) Math.round(point.getX()), (int) Math.round(point.getY()), 3);
            }
        }
        System.out.println("The closest intersection point to the r");
//        AbstractArtDrawing.drawIntersectionPoints(lines, d);
        gui.show(d);
    }
}
