import java.util.ArrayList;

public class Player extends Person {
    private double chips;
    private boolean playing;
    private ArrayList<Card> hand;
    private double bet; // Add a variable to store the player's bet

    public Player(String fullName, double chips) {
        super(fullName); // Pass fullName to the Person constructor
        this.chips = chips;
        this.playing = true;
        this.hand = new ArrayList<>();
        this.bet = 0; // Initialize bet to 0
    }

    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    public void hit(Card card) {
        hand.add(card);
    }

    public void stand() {
        this.playing = false;
    }

    public void placeBet(double amount) {
        if (amount <= chips) {
            chips -= amount;
            bet = amount; // Set the player's bet when they place it
        }
    }

    public double getChips() {
        return chips;
    }

    public void setChips(double chips) {
        this.chips = chips;
    }

    public ArrayList<Card> getHand() {
        return hand;
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

    public double getBet() {
        return bet; // Return the bet amount
    }
}
