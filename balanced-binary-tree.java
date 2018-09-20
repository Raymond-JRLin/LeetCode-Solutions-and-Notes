// 110. Balanced Binary Tree
// DescriptionHintsSubmissionsDiscussSolution
// Given a binary tree, determine if it is height-balanced.
//
// For this problem, a height-balanced binary tree is defined as:
//
// a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
//
// Example 1:
//
// Given the following tree [3,9,20,null,null,15,7]:
//
//     3
//    / \
//   9  20
//     /  \
//    15   7
// Return true.
//
// Example 2:
//
// Given the following tree [1,2,2,3,3,null,null,4,4]:
//
//        1
//       / \
//      2   2
//     / \
//    3   3
//   / \
//  4   4
// Return false.


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
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        // return mytry(root);

        return method2(root);
    }

    private boolean method2(TreeNode root) {
        int result = getDepth(root);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    private int getDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getDepth(root.left);
        if (left == -1) {
            return -1;
        }
        int right = getDepth(root.right);
        if (right == -1) {
            return -1;
        }
        if (Math.abs(left - right) > 1) {
            return -1;
        }
        // if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
        //     return -1;
        // }
        // we can seperate above if to return earlier
        return Math.max(left, right) + 1;
    }

    private boolean mytry(TreeNode root) {
        if (root == null) {
            return true;
        }
        // recursion according to the definition
        // count the difference between the height of left-subtree and right-subtree
        int diff = getHeight(root.left) - getHeight(root.right);
        if (Math.abs(diff) > 1) {
            // larger than 1, return false directly
            return false;
        } else {
            // it's balance for this root node, keep doing for left and right subtree
            return mytry(root.left) && mytry(root.right);
        }
    }
    private int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }
}
