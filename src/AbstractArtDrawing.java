import biuoop.GUI;
import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;

public class AbstractArtDrawing {
    static final int WIDTH = 400;
    static final int HEIGHT = 300;

    public void drawRandomLinesAndIntersection() {
        Random rand = new Random(); // create a random-number generator
        // Create a window with the title "Random Circles Example"
        // which is 400 pixels wide and 300 pixels high.
        GUI gui = new GUI("Abstract Art Drawing", WIDTH, HEIGHT);
        DrawSurface d = gui.getDrawSurface();
        ArrayList<Line> lines = new ArrayList<Line>();
        for (int i = 0; i < 10; ++i) {
            int x = rand.nextInt(WIDTH) + 1; // get integer in range 1-WIDTH
            int y = rand.nextInt(HEIGHT) + 1; // get integer in range 1-HEIGHT
            int z = rand.nextInt(WIDTH) + 1; // get integer in range 1-WIDTH
            int w = rand.nextInt(HEIGHT) + 1; // get integer in range 1-HEIGHT
            Line line = new Line(x, y, z, w);
            lines.add(line);
            d.setColor(Color.BLACK);
            d.drawLine(x, y, z, w);
            d.setColor(Color.BLUE);
            d.fillCircle((int) Math.round(line.middle().getX()), (int) Math.round(line.middle().getY()), 3);
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
        AbstractArtDrawing newArt = new AbstractArtDrawing();
        newArt.drawRandomLinesAndIntersection();
    }
}