import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class TestRectangle {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static void main(String[] args) {
        Random rand = new Random();
        GUI gui = new GUI("Rectangle test", WIDTH, HEIGHT);
        DrawSurface d = gui.getDrawSurface();
        ArrayList<Line> recBorder = new ArrayList<>();
        ArrayList<Line> lines = new ArrayList<>();
        ArrayList<Rectangle> rectangles = new ArrayList<>();
        rectangles.add(new Rectangle(new Point(50, 50), 200, 200));
        rectangles.add(new Rectangle(new Point(200, 200), 200, 200));
        rectangles.add(new Rectangle(new Point(-100, 350), 300, 200));
        for (Rectangle rectangle : rectangles) {
            recBorder.add(rectangle.getLeftSide());
            recBorder.add(rectangle.getUpSide());
            recBorder.add(rectangle.getRightSide());
            recBorder.add(rectangle.getDownSide());
        }
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
            int i = 0;
            while (i < rectangles.size()) {
                System.out.println("---------Rectangle " + (i + 1) + "----------");
                for (Point point : rectangles.get(i).intersectionPoints(line)) {
                    System.out.println("distance: " + point.distance(line.start()));
                    d.setColor(Color.RED);
                    d.fillCircle((int) Math.round(point.getX()), (int) Math.round(point.getY()), 3);
                }
                i++;
            }
        }
        System.out.println("The closest intersection point to the r");
//        AbstractArtDrawing.drawIntersectionPoints(lines, d);
        gui.show(d);
    }
}
