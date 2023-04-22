import java.util.ArrayList;

public class MultipleBalls {
    private ArrayList<Ball> balls;

    public MultipleBalls() {
        balls = new ArrayList<>();
        balls.add(new Ball());
        balls.add(new Ball());
    }

    public MultipleBalls(Borders borders) {
        balls = new ArrayList<>();
        balls.add(new Ball(borders));
        balls.add(new Ball());
    }
}


