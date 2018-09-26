// 543. Diameter of Binary Tree
// DescriptionHintsSubmissionsDiscussSolution
// Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
//
// Example:
// Given a binary tree
//           1
//          / \
//         2   3
//        / \
//       4   5
// Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
//
// Note: The length of path between two nodes is represented by the number of edges between them.


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // return method1(root);

        return method2(root);
    }

    private int result;
    private int method2(TreeNode root) {
        // 类似的思路， 用找最大 height 的过程来找
        result = 0;
        maxHeight(root);
        return result;
    }
    private int maxHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxHeight(root.left);
        int right = maxHeight(root.right);
        // 在递归过程中 update result
        result = Math.max(result, left + right);
        return Math.max(left, right) + 1;
    }

    private int method1(TreeNode root) {
        // 注意 method 的定义： 找到以当前 root 为 root 的 tree 的 diameter
        if (root == null) {
            return 0;
        }
        // 对当前 root 去找左子树和右子树的最大深度， 题目是 “边” 的数量为 diameter
        int curr = getHeight(root.left) + getHeight(root.right);
        int left = method1(root.left); // left node 为 root 的 diameter
        int right = method1(root.right); // right node 为 root 的 diameter
        return Math.max(Math.max(left, right), curr);
    }
    private int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }
}
