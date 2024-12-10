public class GameController {
    private Game game;
    private GameView gameView;

    public GameController(Game game, GameView gameView) {
        this.game = game;
        this.gameView = gameView;
    }

    public void updateChips(Player player, double amount) {
        player.placeBet(amount);
    }

    public void deletePlayer(Player player) {
        game.getPlayers().remove(player);
    }
}
