import java.util.ArrayList;

public class MultipleBalls {
    private ArrayList<Ball> balls;
    private Borders borders = new Borders(Ball.getDefaultWidth(), Ball.getDefaultHeight());

    public MultipleBalls() {
        balls = new ArrayList<>();
        balls.add(new Ball(borders));
        balls.add(new Ball(borders));
    }


    public MultipleBalls(Borders borders) {
        balls = new ArrayList<>();
        balls.add(new Ball(borders));
        balls.add(new Ball());
    }
}


