// 866. Smallest Subtree with all the Deepest Nodes
// User Accepted: 1224
// User Tried: 1488
// Total Accepted: 1248
// Total Submissions: 3163
// Difficulty: Medium
// Given a binary tree rooted at root, the depth of each node is the shortest distance to the root.
//
// A node is deepest if it has the largest depth possible among any node in the entire tree.
//
// The subtree of a node is that node, plus the set of all descendants of that node.
//
// Return the node with the largest depth such that it contains all the deepest nodes in it's subtree.
//
//
//
// Example 1:
//
// Input: [3,5,1,6,2,0,8,null,null,7,4]
// Output: [2,7,4]
// Explanation:
//
//
//
// We return the node with value 2, colored in yellow in the diagram.
// The nodes colored in blue are the deepest nodes of the tree.
// The input "[3, 5, 1, 6, 2, 0, 8, null, null, 7, 4]" is a serialization of the given tree.
// The output "[2, 7, 4]" is a serialization of the subtree rooted at the node with value 2.
// Both the input and output have TreeNode type.
//
//
// Note:
//
// The number of nodes in the tree will be between 1 and 500.
// The values of each node are unique.


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
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        if (root == null) {
            return root;
        }

        // return method1(root);

        return method2(root);
    }

    private TreeNode method2(TreeNode root) {
        // similar we can use a new Pair class to record node and level info
        // ref: https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/discuss/146808/One-pass
        return dfs(root).node;
    }
    private Pair dfs(TreeNode root) {
        if (root == null) {
            // leaf node has level of 0
            return new Pair(null, 0);
        }
        Pair left = dfs(root.left);
        Pair right = dfs(root.right);
        // when get back to upper level, the level should be added by 1 (1 more height)
        if (left.h > right.h) {
            return new Pair(left.node, left.h + 1);
        } else if (left.h < right.h) {
            return new Pair(right.node, right.h + 1);
        } else {
            return new Pair(root, left.h + 1);
        }
    }
    private class Pair {
        private TreeNode node;
        private int h;
        public Pair(TreeNode node, int h) {
            this.node = node;
            this.h = h;
        }
    }

    private TreeNode method1(TreeNode root) {
        // O(N) time and space
        Map<TreeNode, Integer> map = new HashMap<>(); // <node, level>
        return recursion(root, map, 1);
    }
    private TreeNode recursion(TreeNode root, Map<TreeNode, Integer> map, int h) {
        if (root.left == null && root.right == null) {
            map.put(root, h);
            return root;
        }
        if (root.left == null) {
            return recursion(root.right, map, h + 1);
        }
        if (root.right == null) {
            return recursion(root.left, map, h + 1);
        }
        TreeNode left = recursion(root.left, map, h + 1);
        TreeNode right = recursion(root.right, map, h + 1);
        if (map.get(left) == map.get(right)) {
            map.put(root, map.get(left));
            return root;
        }
        return map.get(left) > map.get(right) ? left : right;
    }
}
