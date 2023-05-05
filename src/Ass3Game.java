public class Ass3Game {
    public static void main(String[] args) {
        Game game = new Game();
        Ball ball = new Ball(game.getEnvironment(), new Point(400, 550));
        ball.addToGame(game);
        game.initialize();
        game.run();
    }
}
