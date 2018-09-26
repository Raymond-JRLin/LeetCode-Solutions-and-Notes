// 114. Flatten Binary Tree to Linked List
// DescriptionHintsSubmissionsDiscussSolution
// Given a binary tree, flatten it to a linked list in-place.
//
// For example, given the following tree:
//
//     1
//    / \
//   2   5
//  / \   \
// 3   4   6
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
        // 这个例子的数字刚好是这样， 并不是说要按照大小关系， 而是看左右关系， 先左再右， 有点儿像 pre-order

        // method1(root);

        // method2(root);

        method3(root);
    }

    private TreeNode prev = null; // global variable
    private void method3(TreeNode root) {
        // recursion like post-order (or reverse of pre-order)
        if (root == null) {
            return;
        }
        method3(root.right);
        method3(root.left);
        root.right = prev;
        root.left = null;
        prev = root;
    }

    private void method2(TreeNode root) {
        // iteration without stack: everytime we connect root to the rightmost in left subtree, and connect rightmost to root right subtree
        // ref: https://leetcode.com/problems/flatten-binary-tree-to-linked-list/discuss/37010/Share-my-simple-NON-recursive-solution-O(1)-space-complexity!
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left != null) {
                // has left subtree
                TreeNode prev = curr.left;
                // find the rightmost in left subtree
                while (prev.right != null) {
                    prev = prev.right;
                }
                // connect rightmost in left subtree and curr's right node
                prev.right = curr.right;
                // move whole left subtree to right
                curr.right = curr.left;
                curr.left = null; // set left node as null
            }
            curr = curr.right; // move to next right node to operate
        }
    }

    private void method1(TreeNode root) {
        // iteration with a stack
        // ref: https://leetcode.com/problems/flatten-binary-tree-to-linked-list/discuss/36987/Straightforward-Java-Solution
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            // push as pre-order: right first and left after
            if (curr.right != null) {
                stack.push(curr.right);
            }
            if (curr.left != null) {
                stack.push(curr.left);
            }
            // connect: if there's node in stack we should put it on the right of current root
            if (!stack.isEmpty()) {
                curr.right = stack.peek();
            }
            curr.left = null; // set left child as null
        }
    }
}
