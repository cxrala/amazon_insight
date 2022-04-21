package graph_traversal;

import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;

public class GraphTraversal {
    public static List<String> collectWords(String currentWord, Node node) {
        List<String> response = new ArrayList<>();

        if (node.isCompleteWord()) {
            response.add(currentWord);
        }

        for (int i = 0; i < 26; i++) {
            if (node.getChildren()[i] != null) {
                response.addAll(collectWords(currentWord + (char) ('a' + i), node.getChildren()[i]));
            }
        }

        return response;
    }

    public static List<String> bfs(String currentWord, Node node) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(currentWord, node));
        List<String> response = new ArrayList<>();

        while (q.size() != 0) {
            Pair v = q.poll();
            if (v.getNode().isCompleteWord()) {
                response.add(v.word);
            }
            for (int i = 0; i < 26; i++) {
                if (v.getNode().getChildren()[i] != null) {
                    q.add(new Pair(v.word + (char) ('a' + i), v.getNode().getChildren()[i]));
                }
            }
        }

        return response;
    }
}
