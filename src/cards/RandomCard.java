package cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class RandomCard {

    Stack<Card> cards;
    public static String[] types = {"cups", "coins", "clubs", "swords"};
    public static int[] values = {1, 2, 3, 4, 5, 6, 7, 10, 11, 12};

    public RandomCard() {

        Stack<Card> cards = new Stack<>();
        for (String type:types) {
            for (int value:values) {
                cards.add(new Card(type, value));
            }
        }

        Collections.shuffle(cards);
        this.cards = cards;
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }


    public List<Card> pickCards(int n) {
        if (n > cards.size()) {
            List<Card> returnedCards = new ArrayList<>(cards);
            cards.clear();
            return returnedCards;
        }

        List<Card> returnedCards = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            returnedCards.add(cards.pop());
        }
        return returnedCards;
    }
}
