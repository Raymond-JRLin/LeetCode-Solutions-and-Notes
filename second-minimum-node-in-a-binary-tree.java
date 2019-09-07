// 671. Second Minimum Node In a Binary Tree
// DescriptionHintsSubmissionsDiscussSolution
// Given a non-empty special binary tree consisting of nodes with the non-negative value, where each node in this tree has exactly two or zero sub-node. If the node has two sub-nodes, then this node's value is the smaller value among its two sub-nodes. More formally, the property root.val = min(root.left.val, root.right.val) always holds.
//
// Given such a binary tree, you need to output the second minimum value in the set made of all the nodes' value in the whole tree.
//
// If no such second minimum value exists, output -1 instead.
//
// Example 1:
//
// Input:
//     2
//    / \
//   2   5
//      / \
//     5   7
//
// Output: 5
// Explanation: The smallest value is 2, the second smallest value is 5.
//
//
// Example 2:
//
// Input:
//     2
//    / \
//   2   2
//
// Output: -1
// Explanation: The smallest value is 2, but there isn't any second smallest value.
//
//
// Seen this question in a real interview before?
// Difficulty:Easy


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
    public int findSecondMinimumValue(TreeNode root) {

        // return mytry(root);

        return method2(root);
    }

    private int method2(TreeNode root) {
        // 因为树的特性， 所以应该可以想到利用这个特性而省去 O(N) space. Since root's value is the smaller between its children, so the child with different value of root can be a candidate, and we should keep searching down in another child with same value of root
        // ref: https://leetcode.com/problems/second-minimum-node-in-a-binary-tree/discuss/107158/Java-divide-and-conquer-solution
        // O(N) time and O(1) space
        if (root == null) {
            return -1;
        }
        if (root.left == null && root.right == null) {
            return -1;
        }

        int left = root.left.val;
        int right = root.right.val;
        // keep going down to the node with same value of root
        if (left == root.val) {
            left = method2(root.left);
        }
        if (right == root.val) {
            right = method2(root.right);
        }

        if (left != -1 && right != -1) {
            // returned valus are both larger than current root
            return Math.min(left, right);
        } else if (left != -1) {
            return left;
        } else {
            return right;
        }
    }

    private int mytry(TreeNode root) {
        // O(N) time and space
        Set<Integer> set = new HashSet<>();
        dfs(root, set);
        if (set.size() < 2) {
            return -1;
        }
        int min = Integer.MAX_VALUE;
        int second = min;
        for (int num : set) {
            if (num < min) {
                second = min;
                min = num;
            } else if (num < second) {
                second = num;
            }
        }
        return second;
    }
    private void dfs(TreeNode root, Set<Integer> set) {
        set.add(root.val);
        if (root.left != null) {
            dfs(root.left, set);
            dfs(root.right, set);
        }
    }
}
