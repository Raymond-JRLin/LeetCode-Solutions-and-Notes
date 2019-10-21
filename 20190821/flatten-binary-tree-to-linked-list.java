// 114. Flatten Binary Tree to Linked List
// DescriptionHintsSubmissionsDiscussSolution
//
// Given a binary tree, flatten it to a linked list in-place.
//
// For example, given the following tree:
//
//     1
//    / \
//   2   5
//  / \   \
// 3   4   6
//
// The flattened tree should look like:
//
// 1
//  \
//   2
//    \
//     3
//      \
//       4
//        \
//         5
//          \
//           6
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


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
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }

        // method1(root);

        method2(root);
    }

    private void method2(TreeNode root) {
        // recursion
        helper(root, null);
    }
    private TreeNode helper(TreeNode root, TreeNode prev) {
        if (root == null) {
            return prev;
        }

        prev = helper(root.right, prev);
        prev = helper(root.left, prev);
        root.right = prev;
        root.left = null;
        return root;
    }

    private void method1(TreeNode root) {
        TreeNode head = root;
        // 把 left subtree 接到当前 root 的右边， 把 right subtree 接到 left subtree 的最右 leaf node
        while (head != null) {
            TreeNode left = head.left;
            TreeNode right = head.right;
            // 左边有的时候才需要
            if (left != null) {
                // 找到 right-most leaf node in left subtree
                TreeNode leftLeaf = left;
                while (leftLeaf.right != null) {
                    leftLeaf = leftLeaf.right;
                }

                head.right = left;
                head.left = null;
                leftLeaf.right = right;
            }

            head = head.right;
        }
    }
}
