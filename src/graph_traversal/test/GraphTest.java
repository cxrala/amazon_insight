package graph_traversal.test;

import graph_traversal.GraphTraversal;
import graph_traversal.Node;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.List;

@RunWith(JUnit4.class)
public class GraphTest {

    @Test
    public void Test() {
        // ARRANGE
        Node catNode = new Node(true);
        Node catsNode = catNode.setChild('s', true);
        Node catcNode = catNode.setChild('c', false);
        Node catchNode = catcNode.setChild('h', true);
        Node cateNode = catNode.setChild('e', false);
        Node caterNode = cateNode.setChild('r', true);

        // ACT
        List<String> result = GraphTraversal.bfs("cat", catNode);

        // ASSERT
        Assert.assertEquals(result, Arrays.asList("cat", "cats", "catch", "cater"));

    }
}
