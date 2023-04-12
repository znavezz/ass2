// Import necessary packages
import biuoop.GUI;
import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;

public class SimpleGuiExample {
    static final int WIDTH = 800;
    static final int HEIGHT = 300;

    public void drawRandomCircles() {
        Random rand = new Random(); // create a random-number generator
        // Create a window with the title "Random Circles Example"
        // which is 400 pixels wide and 300 pixels high.
        GUI gui = new GUI("Random Example", WIDTH, HEIGHT);
        DrawSurface d = gui.getDrawSurface();
        ArrayList<Line> lines = new ArrayList<Line>();
        Line line1 = new Line(150, 50, 150, 250);
        Line line2 = new Line(100, 100, 200, 100);
        lines.add(line1);
        lines.add(line2);
        d.setColor(Color.BLACK);
        d.drawLine(150, 50, 150, 250);
        d.drawLine(100, 100, 200, 100);
        for (int i = 0; i < 25; ++i) {
            int x = rand.nextInt(WIDTH) + 1; // get integer in range 1-WIDTH
            int y = rand.nextInt(HEIGHT) + 1; // get integer in range 1-HEIGHT
            int z = rand.nextInt(WIDTH) + 1; // get integer in range 1-WIDTH
            int w = rand.nextInt(HEIGHT) + 1; // get integer in range 1-HEIGHT
            Line line = new Line(x, y, z, w);
            lines.add(line);
            d.setColor(Color.CYAN);
            d.drawLine(x, y, z, w);
            d.setColor(Color.BLUE);
            d.fillCircle((int) Math.round(line.middle().getX()), (int) Math.round(line.middle().getY()), 3);
        }
        if (line1.isIntersecting(line2)) {
            Point intersect = line1.intersectionWith(line2);
            System.out.println(intersect.toString());
            d.setColor(Color.RED);
          d.fillCircle((int) Math.round(intersect.getX()), (int) Math.round(intersect.getY()), 3);
        }
        for (int i = 0; i < lines.size() - 1; i++) {
            for (int j = i + 1; j < lines.size(); j++) {
                if (lines.get(i).isIntersecting(lines.get(j))) {
                    Point intersect = lines.get(i).intersectionWith(lines.get(j));
                    System.out.println(intersect.toString());
                    d.setColor(Color.RED);
                    d.fillCircle((int) Math.round(intersect.getX()), (int) Math.round(intersect.getY()), 3);
                }
            }
        }
        gui.show(d);
    }

    public static void main(String[] args) {
        SimpleGuiExample example = new SimpleGuiExample();
        example.drawRandomCircles();
    }
}