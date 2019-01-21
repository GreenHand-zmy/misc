package leetcode.tree;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class No559MaximumDepthOfNAryTree {
    public int maxDepth(Node root) {
        return maxDepth1(root);
    }

    private int maxDepth1(Node root) {
        if (root == null) {
            return 0;
        }

        Queue<Node> queue = new LinkedList<>();
        int depth = 0;

        queue.add(root);

        while (!queue.isEmpty()) {
            Queue<Node> temp = new LinkedList<>();
            while (!queue.isEmpty()) {
                Node current = queue.remove();
                if (current.children != null) {
                    temp.addAll(current.children);
                }
            }
            queue = temp;
            depth++;
        }
        return depth;
    }

    private int maxDepth2(Node root) {
        if (root == null) {
            return 0;
        }

        int max = 0;
        if (root.children != null) {
            for (Node child : root.children) { //replace left&right to for loop
                int value = maxDepth(child);

                if (value > max) {
                    max = value;
                }
            }
        }

        return max + 1;
    }


    @Test
    public void testCase1() {
        Node root = new Node(1, null);
        root.children = List.of(
                new Node(3, List.of(new Node(5, null)
                        , new Node(6, null))),
                new Node(2, null), new Node(4, null));

        int exceptDepth = 3;

        int resultDepth = maxDepth(root);

        Assert.assertEquals(exceptDepth, resultDepth);
    }
}
