package leetcode.tree;

public class XNo814BinaryTreePruning {
    /**
     * 后序遍历
     *
     * @param root
     * @return
     */
    public TreeNode pruneTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);

        if (root.val == 0 && root.left == null && root.right == null) {
            return null;
        }

        return root;
    }
}
