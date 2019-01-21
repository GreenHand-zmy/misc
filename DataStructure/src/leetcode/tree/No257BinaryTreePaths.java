package leetcode.tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 * 说明: 叶子节点是指没有子节点的节点。
 */
public class No257BinaryTreePaths {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();

        dfs(root, "", res);

        return res;
    }

    private void dfs(TreeNode node, String path, List<String> list) {
        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            path = path + node.val;
            list.add(path);
            return;
        }

        path = path + node.val + "->";
        dfs(node.left, path, list);
        dfs(node.right, path, list);
    }

    /**
     * 输入:
     * <p>
     * 1
     * /   \
     * 2     3
     * \
     * 5
     * <p>
     * 输出: ["1->2->5", "1->3"]
     * <p>
     * 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
     */
    @Test
    public void testCase1() {
        TreeNode root = new TreeNode(1,
                new TreeNode(2, null, new TreeNode(5)),
                new TreeNode(3));

        List<String> result = binaryTreePaths(root);
    }
}
