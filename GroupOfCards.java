import java.util.ArrayList;
import java.util.Collections;

public class GroupOfCards {

    // The group of cards, stored in an ArrayList
    protected ArrayList<Card> cards; // Changed to protected
    private int size; // The size of the grouping

    public GroupOfCards(int size) {
        this.size = size;
        this.cards = new ArrayList<>(); // Initialize the cards list
    }

    /**
     * A method that will get the group of cards as an ArrayList
     *
     * @return the group of cards.
     */
    public ArrayList<Card> getCards() {
        return cards;
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * @return the size of the group of cards
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size the max size for the group of cards
     */
    public void setSize(int size) {
        this.size = size;
    }

} // End class
