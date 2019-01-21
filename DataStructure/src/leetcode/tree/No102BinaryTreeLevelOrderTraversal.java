package leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class No102BinaryTreeLevelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        return levelOrder2(root);
    }

    public List<List<Integer>> levelOrder1(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> allLevelElements = new ArrayList<>();

        if (root == null) {
            return allLevelElements;
        }

        queue.add(root);

        while (!queue.isEmpty()) {
            Queue<TreeNode> temp = new LinkedList<>();
            List<Integer> oneLevelElements = new ArrayList<>();

            while (!queue.isEmpty()) {
                TreeNode node = queue.remove();
                oneLevelElements.add(node.val);

                if (node.left != null) {
                    temp.add(node.left);
                }
                if (node.right != null) {
                    temp.add(node.right);
                }
            }
            allLevelElements.add(oneLevelElements);
            queue = temp;
        }
        return allLevelElements;
    }

    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        levelOrder2(result, root, 0);

        return result;
    }

    private void levelOrder2(List<List<Integer>> result, TreeNode node, int depth) {
        if (node == null) {
            return;
        }

        if (depth >= result.size()) {
            result.add(new ArrayList<>());
        }

        result.get(depth).add(node.val);
        levelOrder2(result, node.left, depth + 1);
        levelOrder2(result, node.right, depth + 1);
    }
}
