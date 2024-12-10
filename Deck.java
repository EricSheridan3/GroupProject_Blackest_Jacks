import java.util.ArrayList;
import java.util.Collections;

class Deck {
    private ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        String[] types = {"Hearts", "Diamonds", "Clubs", "Spades"};
        for (String type : types) {
            for (int rank = 1; rank <= 13; rank++) {
                cards.add(new Card(rank, type));
            }
        }
        shuffle();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        return cards.remove(cards.size() - 1);
    }
}