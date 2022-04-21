package cards;

import java.util.Locale;
import java.util.Scanner;

public class Game {

    int cumsum = 0;
    RandomCard deck;
    int tries = 0;

    public Game() {
        deck = new RandomCard();
        cumsum += deck.pickCards(3).stream()
                .map(x -> x.type)
                .reduce(0, Integer::sum);
    }

    public boolean checkWin() {
        if (isPrime(cumsum)) {
            return true;
        }
        tries++;
        cumsum += deck.pickCards(2).stream()
                .map(x -> x.type)
                .reduce(0, Integer::sum);
        return false;
    }

    private boolean isPrime(int n) {
        for (int i = 2; i < n/2; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Game game = new Game();
        boolean hasWon = false;
        Scanner scanner = new Scanner(System.in);

        while (game.deck.cards.size() != 0) {
            System.out.printf("Your score right now is %d\n", game.cumsum);
            if (game.checkWin()) {
                hasWon = true;
                break;
            } else {
                System.out.println("oh no... better luck on the next draw? perhaps you want to shuffle your cards?");
                System.out.println("y/n > ");
                String input = scanner.nextLine();
                if (input.toLowerCase(Locale.ROOT).equals("y")) {
                    game.deck.shuffle();
                    System.out.println("Shuffled!");
                }
            }
        }
        if (hasWon) {
            System.out.println("Great, you got a prime number!");
            System.out.printf("It only took you %d tries...%n", game.tries);
        } else {
            System.out.println("Somehow, you lost... maybe probability will serve you better next time :^)");
        }
    }
}
