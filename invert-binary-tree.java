// 226. Invert Binary Tree

// Invert a binary tree.
//
// Example:
//
// Input:
//
//      4
//    /   \
//   2     7
//  / \   / \
// 1   3 6   9
// Output:
//
//      4
//    /   \
//   7     2
//  / \   / \
// 9   6 3   1


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
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }

        // return mytry(root);

        // return mytry2(root);

        return method3(root);
    }

    private TreeNode method3(TreeNode root) {
        // iteration by stack
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            TreeNode temp = curr.left;
            curr.left = curr.right;
            curr.right = temp;
            if (curr.left != null) {
                stack.push(curr.left);
            }
            if (curr.right != null) {
                stack.push(curr.right);
            }
        }
        return root;
    }

    private TreeNode mytry2(TreeNode root) {
        // recursive
        if (root == null) {
            return root;
        }
        // recursion: go sub-problem
        TreeNode left = mytry2(root.left); // left: inverted left-subtree
        TreeNode right = mytry2(root.right); // right: inverted right-subtree
        // invert: invert left and right subnode
        root.left = right;
        root.right = left;
        return root;
    }

    private TreeNode mytry(TreeNode root) {
        // BFS: invert left and right for each level
        // O(N) time and O(N) space
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                // invert
                TreeNode temp = curr.left;
                curr.left = curr.right;
                curr.right = temp;
                if (curr.left != null) {
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                }
            }
        }
        return root;
    }
}
