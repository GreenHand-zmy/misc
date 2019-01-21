package leetcode.tree;

import java.util.*;

/**
 * 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 * <p>
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 * <p>
 *       3
 *      / \
 *     9  20
 *    /    \
 *   15     7
 * 返回其自底向上的层次遍历为：
 * <p>
 * [
 * [15,7],
 * [9,20],
 * [3]
 * ]
 */
public class No107BinaryTreeLevelOrderTraversalII {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();

        Queue<TreeNode> queue = new LinkedList<>();

        if (root == null) {
            return result;
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
            result.add(0, oneLevelElements);
            queue = temp;
        }

        return result;
    }
}
