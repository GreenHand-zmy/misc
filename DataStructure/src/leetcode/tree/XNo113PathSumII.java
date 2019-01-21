package leetcode.tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class XNo113PathSumII {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        pathSum(root, res, path, sum, 0);

        return res;
    }

    private void pathSum(TreeNode node, List<List<Integer>> list, List<Integer> currentPath, int targetSum, int sum) {
        if (node == null) {
            return;
        }

        sum += node.val;
        currentPath.add(node.val);

        if (node.left == null && node.right == null) {
            if (sum == targetSum) {
                list.add(new ArrayList<>(currentPath));
                currentPath.remove(currentPath.size() - 1);
                return;
            }
        }

        pathSum(node.left, list, currentPath, targetSum, sum);
        pathSum(node.right, list, currentPath, targetSum, sum);

        currentPath.remove(currentPath.size() - 1);
    }

    /**
     * 给定如下二叉树，以及目标和 sum = 22，
     * 5
     * / \
     * 4   8
     * /   / \
     * 11  13  4
     * /  \    / \
     * 7    2  5   1
     * 返回:
     * [
     * [5,4,11,2],
     * [5,8,4,5]
     * ]
     */
    @Test
    public void testCase1() {
        TreeNode root = new TreeNode(5,
                new TreeNode(4,
                        new TreeNode(11,
                                new TreeNode(7),
                                new TreeNode(2)), null),
                new TreeNode(8, new TreeNode(13), new TreeNode(4, new TreeNode(5), new TreeNode(1))));

        List<List<Integer>> result = pathSum(root, 22);
    }
}
