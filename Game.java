import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private Deck deck;
    private ArrayList<Player> players;
    private GameView gameView;
    private Dealer dealer;

    public Game() {
        deck = new Deck();
        players = new ArrayList<>();
        gameView = new GameView();
        dealer = new Dealer(); // Assuming you have a Dealer class for the dealer's hand
    }

    public void addPlayer(String name, double chips) {
        players.add(new Player(name, chips));
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void explainCardValues() {
        System.out.println("Welcome to Blackjack!");
        System.out.println("Card Values:");
        System.out.println("- Number cards are worth their face value (2-10).");
        System.out.println("- Jack, Queen, and King are each worth 10.");
        System.out.println("- Ace is worth 11, but it can be reduced to 1 if needed to avoid going over 21.");
        System.out.println();
    }

    public void startGame() {
        explainCardValues();
        System.out.println("Starting Blackjack game!");
        Scanner scanner = new Scanner(System.in);

        // Ask for bets
        for (Player player : players) {
            System.out.print(player.getFullName() + ", you have " + player.getChips() + " chips. Enter your bet: ");
            double bet = scanner.nextDouble();
            while (bet > player.getChips() || bet <= 0) {
                System.out.print("Invalid bet. Please enter a bet less than or equal to your available chips: ");
                bet = scanner.nextDouble();
            }
            player.placeBet(bet);
        }

        // Start the round
        playRounds();
    }

    private void playRounds() {
        Scanner scanner = new Scanner(System.in);
        
        // Reset hands and dealer's hand
        dealer.clearHand();
        for (Player player : players) {
            player.getHand().clear();  // Clear each player's hand at the start of the round
        }

        for (Player player : players) {
            // Deal initial cards to the player
            player.hit(deck.drawCard());
            player.hit(deck.drawCard());
        }

        // Play turns for each player
        for (Player player : players) {
            while (player.isPlaying() && player.calculateHandValue() < 21) {
                gameView.displayTurn(player);
                System.out.println("Your hand value is: " + player.calculateHandValue());
                System.out.println("Would you like to hit or stand? (h/s)");
                String decision = scanner.nextLine();

                if (decision.equalsIgnoreCase("h")) {
                    Card drawnCard = deck.drawCard(); // Draw a card
                    player.hit(drawnCard); // Add the card to the player's hand
                    gameView.revealCard(drawnCard); // Show the card to the player
                    System.out.println("You were dealt the " + drawnCard + "."); // Display the card
                } else if (decision.equalsIgnoreCase("s")) {
                    player.stand();
                } else {
                    System.out.println("Invalid input. Please enter 'h' or 's'.");
                }
            }

            if (player.calculateHandValue() > 21) {
                System.out.println(player.getFullName() + ", you busted with a hand value of " + player.calculateHandValue());
                player.setPlaying(false);
            }
        }

        // Dealer's turn (assuming you have a Dealer class handling the dealer's actions)
        dealer.dealInitialCards(deck);
        dealer.playTurn(deck);
        gameView.displayDealerHand(dealer.getHand(), dealer.calculateHandValue());

        // Determine the winners and update chips
        determineWinners();

        // Show the results of the round
        showRoundResults();

        // Ask if the player wants to keep playing or not
        askToContinue();
    }

    private void determineWinners() {
        System.out.println("Round results:");
    
        for (Player player : players) {
            int playerHandValue = player.calculateHandValue();
            int dealerHandValue = dealer.calculateHandValue();
    
            // Check if the player busted
            if (playerHandValue > 21) {
                System.out.println(player.getFullName() + " busted with a hand value of " + playerHandValue);
            }
            // If the player doesn't bust, compare their hand to the dealer's hand
            else {
                // If the dealer busts, the player wins
                if (dealerHandValue > 21) {
                    System.out.println(player.getFullName() + " wins with a hand value of " + playerHandValue);
                    player.setChips(player.getChips() + player.getBet() * 2); // Player wins bet + original bet
                }
                // If the player has a higher hand value, the player wins
                else if (playerHandValue > dealerHandValue) {
                    System.out.println(player.getFullName() + " wins with a hand value of " + playerHandValue);
                    player.setChips(player.getChips() + player.getBet() * 2); // Player wins bet + original bet
                }
                // If the hand values are the same, it's a tie
                else if (playerHandValue == dealerHandValue) {
                    System.out.println(player.getFullName() + " ties with the dealer with a hand value of " + playerHandValue);
                    player.setChips(player.getChips() + player.getBet()); // Return the bet amount in case of a tie
                }
                // If the dealer has a higher hand value, the player loses
                else {
                    System.out.println(player.getFullName() + " loses with a hand value of " + playerHandValue);
                }
            }
        }
    
        // Display the statistics for the game
        gameView.displayStatistics(players);
    }
    

    private void showRoundResults() {
        // Display results of the round (who won, who lost, etc.)
        System.out.println("Round results:");
        for (Player player : players) {
            System.out.println(player.getFullName() + " has a hand value of " + player.calculateHandValue());
            System.out.println(player.getFullName() + "'s cards: " + player.getHand());
        }
    
        // Show dealer's hand after the round
        int dealerHandValue = dealer.calculateHandValue();
        System.out.println("Dealer's hand value: " + dealerHandValue);
        System.out.println("Dealer's cards: " + dealer.getHand());
    }
    

    private void askToContinue() {
        Scanner scanner = new Scanner(System.in);
        boolean stillPlaying = false;
    
        // Ask each player if they want to keep playing
        for (Player player : players) {
            if (player.getChips() <= 0) {
                System.out.println(player.getFullName() + " has no chips left and is out of the game.");
                player.setPlaying(false); // Remove the player from the game
            } else {
                System.out.print(player.getFullName() + ", do you want to keep playing? (y/n): ");
                String decision = scanner.nextLine();
                if (decision.equalsIgnoreCase("y")) {
                    stillPlaying = true; // Player wants to continue playing
                    player.setPlaying(true);
                } else {
                    player.setPlaying(false); // Player chooses to stop
                }
            }
        }
    
        // If there are still players left, start a new round
        if (stillPlaying) {
            startGame(); // Restart the game for the next round
        } else {
            System.out.println("Game over! No players left to continue.");
        }
    }
    
}
