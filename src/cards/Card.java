package cards;

public class Card {
    String suite;
    int type;

    public Card(String suite, int type) {
        this.suite = suite;
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("You got a %s of %d!", suite, type);
    }
}
