import java.util.ArrayList;

public class Dealer {
    private ArrayList<Card> hand;

    public Dealer() {
        hand = new ArrayList<>();
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void clearHand() {
        hand.clear(); // Clear the dealer's hand after each round
    }

    public void hit(Card card) {
        hand.add(card);
    }

    public int calculateHandValue() {
        int value = 0;
        int aces = 0;
        for (Card card : hand) {
            if (card.getRank() > 10) {
                value += 10;
            } else if (card.getRank() == 1) {
                aces++;
                value += 11;
            } else {
                value += card.getRank();
            }
        }
        while (value > 21 && aces > 0) {
            value -= 10;
            aces--;
        }
        return value;
    }

    public void dealInitialCards(Deck deck) {
        // Deal 2 cards to the dealer at the start of the game
        hand.clear(); // Clear previous hand
        hit(deck.drawCard());
        hit(deck.drawCard());
    }

    public void playTurn(Deck deck) {
        // The dealer hits if their hand value is 16 or less, and stands if it's 17 or higher
        while (calculateHandValue() < 17) {
            hit(deck.drawCard());
        }
    }
}
