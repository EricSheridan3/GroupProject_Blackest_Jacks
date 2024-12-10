import java.util.Scanner;

public class Blackjack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game game = new Game();

        System.out.println("Welcome to Blackjack!");
        System.out.print("Enter the number of players: ");
        int numPlayers = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (int i = 1; i <= numPlayers; i++) {
            System.out.print("Enter name for Player " + i + ": ");
            String name = scanner.nextLine();
            game.addPlayer(name, 100.0); // Each player starts with 100 chips
        }

        game.startGame();
        scanner.close();
    }
}
//commernt
