// 98. Validate Binary Search Tree

// Given a binary tree, determine if it is a valid binary search tree (BST).
//
// Assume a BST is defined as follows:
//
// The left subtree of a node contains only nodes with keys less than the node's key.
// The right subtree of a node contains only nodes with keys greater than the node's key.
// Both the left and right subtrees must also be binary search trees.
// Example 1:
//
// Input:
//     2
//    / \
//   1   3
// Output: true
// Example 2:
//
//     5
//    / \
//   1   4
//      / \
//     3   6
// Output: false
// Explanation: The input is: [5,1,4,null,null,3,6]. The root node's value is 5 but its right child's value is 4.


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
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }

        return mytry(root, Long.MIN_VALUE, Long.MAX_VALUE);

        // return method2(root);
    }

    private boolean method2(TreeNode root) {
        // iteration: 用 in-order， BST inorder 一定是从小到大的， 所以可以用 inorder 的 iteration 来查看
        Stack<TreeNode> stack = new Stack<>();
        TreeNode prev = null;
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (prev != null && root.val <= prev.val) {
                return false;
            }
            prev = root;
            root = root.right;
        }
        return true;
    }

    private boolean mytry(TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        }
        if (root.val <= min || root.val >= max) {
            return false;
        }
        return mytry(root.left, min, root.val) && mytry(root.right, root.val, max);
    }

}
