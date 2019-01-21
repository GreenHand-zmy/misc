package leetcode.tree;

import org.junit.Test;

/**
 * 给定一个二叉树，原地将它展开为链表。
 *
 * 例如，给定二叉树
 *
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 *
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 */
public class XNo114FlattenBinaryTreeToLinkedList {

    private TreeNode lastVisited = null;

    public void flatten(TreeNode root) {
        if (root == null)
            return;

        TreeNode savedRight = root.right;
        if (lastVisited != null) {
            lastVisited.left = null;
            lastVisited.right = root;
        }
        lastVisited = root;

        flatten(root.left);
        flatten(savedRight);
    }
    /**
     *  *     1
     *  *    / \
     *  *   2   5
     *  *  / \   \
     *  * 3   4   6
     */
    @Test
    public void testCase1() {
        TreeNode root = new TreeNode(1);

        TreeNode left = new TreeNode(2);
        left.left = new TreeNode(3);
        left.right = new TreeNode(4);

        TreeNode right = new TreeNode(5);
        right.right = new TreeNode(6);

        root.left = left;
        root.right = right;

        flatten(root);
    }
}
