public class Borders {
    private double left = 0;
    private double up = 0;
    private double right;
    private double down;
    public Borders() {
        right = 800;
        down = 600;
    }
    public Borders(double right, double down) {
        this.right = right;
        this.down = down;
    }
    public Borders(double left, double up, double right, double down) {
        this.left = left;
        this.up = up;
        this.right = right;
        this.down = down;
    }

    public double getLeft() {
        return left;
    }
    public double getUp() {
        return up;
    }
    public double getRight() {
        return right;
    }
    public double getDown() {
        return down;
    }

    public void setLeft(double left) {
        this.left = left;
    }
    public void setUp(double up) {
        this.up = up;
    }
    public void setRight(double right) {
        this.right = right;
    }
    public void setDown(double down) {
        this.down = down;
    }
    public String toString() {
        return "Left border = " + left + " Upper border = " + up + " Right border = "
                + right + " Down border = " + down;
    }

    public Point generateRandomPoint() {
        return new Point(Geometry.RAND.nextDouble(right + left),
                Geometry.RAND.nextDouble(down + up));
    }
}
