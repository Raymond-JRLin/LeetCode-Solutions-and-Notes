// 669. Trim a Binary Search Tree
// DescriptionHintsSubmissionsDiscussSolution
//
// Given a binary search tree and the lowest and highest boundaries as L and R, trim the tree so that all its elements lies in [L, R] (R >= L). You might need to change the root of the tree, so the result should return the new root of the trimmed binary search tree.
//
// Example 1:
//
// Input:
//     1
//    / \
//   0   2
//
//   L = 1
//   R = 2
//
// Output:
//     1
//       \
//        2
//
// Example 2:
//
// Input:
//     3
//    / \
//   0   4
//    \
//     2
//    /
//   1
//
//   L = 1
//   R = 3
//
// Output:
//       3
//      /
//    2
//   /
//  1
//
// Seen this question in a real interview before?
//
//     Difficulty:Easy


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
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null) {
            return root;
        }

        // return method1(root, L, R);

        return method2(root, L, R);
    }

    private TreeNode method2(TreeNode root, int L, int R) {
        if (root == null) {
            return root;
        }
        // find the valid root to return
        while (root != null && (root.val < L || root.val > R)) {
            if (root.val < L) {
                root = root.right;
            } else if (root.val > R) {
                root = root.left;
            }
        }
        // root 上面的都不需要关心了， 只需要看此时新找到的该返回的 root 的 left/right 是否 valid/需要删除
        TreeNode curr = root;
        while (curr != null) {
            while (curr.left != null && curr.left.val < L) {
                curr.left = curr.left.right;
            }
            curr = curr.left;
        }
        curr = root;
        while (curr != null) {
            while (curr.right != null && curr.right.val > R) {
                curr.right = curr.right.left;
            }
            curr = curr.right;
        }
        return root;
    }

    private TreeNode method1(TreeNode root, int L, int R) {
        if (root == null) {
            return root;
        }
        if (root.val < L) {
            return method1(root.right, L, R);
        }
        if (root.val > R) {
            return method1(root.left, L, R);
        }
        root.left = method1(root.left, L, R);
        root.right = method1(root.right, L, R);
        return root;
    }
}
