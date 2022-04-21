package graph_traversal;

public class Pair {
    String word;
    Node node;

    public Pair(String word, Node node) {
        this.word = word;
        this.node = node;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }
}
