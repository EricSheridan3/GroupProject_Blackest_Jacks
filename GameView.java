import java.util.ArrayList;

public class GameView {
    public void displayTurn(Player player) {
        System.out.println(player.getFullName() + "'s turn. Current hand: " + player.getHand());
    }

    public void revealCard(Card card) {
        System.out.println("Revealing card: " + card);
    }

    public void displayResult(Player player, boolean isWinner) {
        if (isWinner) {
            System.out.println(player.getFullName() + " wins with hand value: " + player.calculateHandValue());
        } else {
            System.out.println(player.getFullName() + " loses with hand value: " + player.calculateHandValue());
        }
    }

    public void displayStatistics(ArrayList<Player> players) {
        System.out.println("Game Statistics:");
        for (Player player : players) {
            System.out.println(player.getFullName() + " - Chips: " + player.getChips());
        }
    }

    public void displayDealerHand(ArrayList<Card> hand, int handValue) {
        System.out.println("Dealer's hand: " + hand);
        System.out.println("Dealer's hand value: " + handValue);
    }
}
