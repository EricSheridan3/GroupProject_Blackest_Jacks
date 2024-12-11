class Deck extends GroupOfCards {

    public Deck() {
        super(52); // Initialize the size of the deck as 52 cards
        String[] types = {"Hearts", "Diamonds", "Clubs", "Spades"};
        for (String type : types) {
            for (int rank = 1; rank <= 13; rank++) {
                cards.add(new Card(rank, type)); // Use the cards field from GroupOfCards
            }
        }
        shuffle();
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("No cards left in the deck.");
        }
        return cards.remove(cards.size() - 1);
    }
}
