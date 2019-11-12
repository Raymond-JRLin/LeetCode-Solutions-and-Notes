// 958. Check Completeness of a Binary Tree
// DescriptionHintsSubmissionsDiscussSolution
//
// Given a binary tree, determine if it is a complete binary tree.
//
// Definition of a complete binary tree from Wikipedia:
// In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
//
//
//
// Example 1:
//
// Input: [1,2,3,4,5,6]
// Output: true
// Explanation: Every level before the last is full (ie. levels with node-values {1} and {2, 3}), and all nodes in the last level ({4, 5, 6}) are as far left as possible.
//
// Example 2:
//
// Input: [1,2,3,4,5,null,7]
// Output: false
// Explanation: The node with value 7 isn't as far left as possible.
//
//
//
// Note:
//
//     The tree will have between 1 and 100 nodes.
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
    public boolean isCompleteTree(TreeNode root) {
        if (root == null) {
            return true;
        }

        // return method1(root);

        return method2(root);
    }

    private boolean method2(TreeNode root) {
        // 如果要 recursion 的话， 也类似采取 level traversal 的方式
        // 放到 array 中， 我们知道 root 的 left/right 的 index 分别是 index(root) * 2 + 1 和 index(root) * 2 + 1
        // 但是如果左边有空缺， 而右边有的话， 按照这个规律走下去的 index 会大于它本身的值， 也就是走到最后， 会大于整棵树 node 的个数
        int n = countNode(root);
        return check(root, n, 0);
    }
    private boolean check(TreeNode root, int n, int index) {
        if (root == null) {
            return true;
        }
        if (index >= n) {
            return false;
        }
        return check(root.left, n, index * 2 + 1) && check(root.right, n, index * 2 + 2);
    }
    private int countNode(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + countNode(root.left) + countNode(root.right);
    }

    private boolean method1(TreeNode root) {
        // BFS to get the last layer
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr == null) {
                break;
            }
            queue.offer(curr.left);
            queue.offer(curr.right);
        }
        // 我们是按照 left -> right 的顺序放入， 所以如果遇到了 null， 之后还有的话， 那么就是不完整的
        while (!queue.isEmpty() && queue.peek() == null) {
            queue.poll();
        }
        return queue.isEmpty();
    }
}
