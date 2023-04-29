import biuoop.GUI;
import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;

/**
 * @author Nave Zehoray 206388746 < znavez@gmail.com >
 * This class creates an abstract art drawing consisting of randomly generated lines and the intersections between them.
 */
public class AbstractArtDrawing {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;
    /**
     * This is the main method that creates a new instance of AbstractArtDrawing
     * and calls its drawRandomLinesAndIntersection() method.
     * @param args an array of command-line arguments for the application, which are not used in this program.
     */
    public static void main(String[] args) {
        AbstractArtDrawing newArt = new AbstractArtDrawing();
        newArt.drawRandomLinesAndIntersection();
    }
    /**
     * This method creates a GUI window and draws 10 random lines on it.
     * For each line, a blue circle is drawn at its middle point.
     * If two lines intersect, a red circle is drawn at the intersection point.
     */
    public void drawRandomLinesAndIntersection() {
        Random rand = new Random();
        GUI gui = new GUI("Abstract Art Drawing", WIDTH, HEIGHT);
        DrawSurface d = gui.getDrawSurface();
        ArrayList<Line> lines = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
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
    public static void drawIntersectionPoints(ArrayList<Line> lines, DrawSurface d) {
        int k = 0;
        while (k < lines.size()) {
            d.setColor(Color.BLACK);
            d.drawLine((int) Math.round(lines.get(k).start().getX()), (int) Math.round(lines.get(k).start().getY()),
                    (int) Math.round(lines.get(k).end().getX()), (int) Math.round(lines.get(k).end().getY()));
            d.setColor(Color.BLUE);
            d.fillCircle((int) Math.round(lines.get(k).middle().getX()),
                    (int) Math.round(lines.get(k).middle().getY()), 3);
            k++;
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
    }


}