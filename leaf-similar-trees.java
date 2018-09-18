// 872. Leaf-Similar Trees
// User Accepted: 1920
// User Tried: 1998
// Total Accepted: 1958
// Total Submissions: 2958
// Difficulty: Easy
// Consider all the leaves of a binary tree.  From left to right order, the values of those leaves form a leaf value sequence.
//
//
//
// For example, in the given tree above, the leaf value sequence is (6, 7, 4, 9, 8).
//
// Two binary trees are considered leaf-similar if their leaf value sequence is the same.
//
// Return true if and only if the two given trees with head nodes root1 and root2 are leaf-similar.
//
//
//
// Note:
//
// Both of the given trees will have between 1 and 100 nodes.


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
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null) {
            return false;
        }

        // return mytry(root1, root2);

        return method2(root1, root2);
    }

    private boolean method2(TreeNode root1, TreeNode root2) {
        // 边查找 Leaf node， 边比较； 那么就不能用递归的方式找 Leaf node， 而是要用 stack 才能得到中间状态的 node 值
        // O(N) time, O(logN) space
        // ref: https://leetcode.com/problems/leaf-similar-trees/discuss/152329/C++JavaPython-O(logN)-Space
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(root1);
        stack2.push(root2);
        while (!stack2.isEmpty() && !stack2.isEmpty()) {
            if (recursion(stack1) != recursion(stack2)) {
                return false;
            }
        }
        // cannot return true directly, we should compare if they have same number of nodes like comparing list size in mytry
        return stack1.isEmpty() && stack2.isEmpty();
    }
    private int recursion(Stack<TreeNode> stack) {
        while (true) {
            TreeNode curr = stack.pop();
            if (curr.right != null) {
                stack.push(curr.right);
            }
            if (curr.left != null) {
                stack.push(curr.left);
            }
            if (curr.left == null && curr.right == null) {
                // return leaf node
                return curr.val;
            }
        }
    }

    private boolean mytry(TreeNode root1, TreeNode root2) {
        // O(N) time, O(# leaf node) space
        List<Integer> leaf1 = new ArrayList<>(); // or we can use a string to record
        List<Integer> leaf2 = new ArrayList<>();
        dfs(root1, leaf1);
        dfs(root2, leaf2);
        if (leaf1.size() != leaf2.size()) {
            return false;
        }
        for (int i = 0; i < leaf1.size(); i++) {
            if (leaf1.get(i) != leaf2.get(i)) {
                return false;
            }
        }
        return true;
    }
    private void dfs(TreeNode root, List<Integer> result) {
        if (root.left == null && root.right == null) {
            result.add(root.val);
            return;
        }
        if (root.left != null) {
            dfs(root.left, result);
        }
        if (root.right != null) {
            dfs(root.right, result);
        }
    }
}
