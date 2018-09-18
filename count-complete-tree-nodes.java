// 222. Count Complete Tree Nodes

// Given a complete binary tree, count the number of nodes.
//
// Note:
//
// Definition of a complete binary tree from Wikipedia:
// In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
//
// Example:
//
// Input:
//     1
//    / \
//   2   3
//  / \  /
// 4  5 6
//
// Output: 6


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
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // return mytry(root);

        return method1(root);
    }

    private int method1(TreeNode root) {
        // brute force would be TLE obviously, so we should do based on special structure of complete tree.
        // O((logN) ^ 2) time, count height is O(logN), recursion step is O(logN)
        // ref: https://leetcode.com/problems/count-complete-tree-nodes/discuss/61958/Concise-Java-solutions-O(log(n)2)
        // If a tree is totally completed, then the relation between nodes and height is: # nodes = 2 ^ (h + 1) - 1, h = 0, 1, ... derivation: # = 2 ^ 0 + 2 ^ 1 + 2 ^ 2 + ... + 2 ^ h, which is a geometric progression, # = (a1 + a1 * q ^ (h + 1)) / (1 - q) = (1 + 1 * 2 ^ (h + 1)) / ( 1 - 2) = 2 ^ (h + 1) - 1
        // then the point is to find the last node in the last level
        int h = getHeight(root);
        if (h == -1) {
            return 0;
        }
        if (getHeight(root.right) == h - 1) {
            // 右子树的 h 正好是整棵树的 h - 1， 意味着 root.left 的 subtree 是 totally completed, 那就算出它的个数， 然后递归求右子树的个数
            // 此时完全可以计算的个数是： 左子树的所有 nodes 加上当前的 root = 2 ^ (h - 1 + 1) - 1 + 1, 此时 height = h - 1， 最后记得加上 root 这个
            return (1 << h) + method1(root.right);
        } else {
            // 右子树的 h 并不正好是整棵树的 h - 1， 说明最后一层的最后一个 node 在左子树的下面， 意味着右子树除了最后一层以外是 totally completed, 那就算出它的个数， 然后递归求左子树的个数
            // 此时完全可以计算的个数是： 右子树的所有 nodes 加上当前的 root = 2 ^ (h - 2 + 1) - 1 + 1, 此时 height = h - 2， 最后记得加上 root 这个
            return (1 << (h - 1)) + method1(root.left);
        }
    }
    private int getHeight(TreeNode root) {
        if (root == null) {
            return -1;
        } else {
            return getHeight(root.left) + 1;
        }
    }

    private int mytry(TreeNode root) {
        // brute force: TLE, O(N)
        if (root == null) {
            return 0;
        }
        return mytry(root.left) + mytry(root.right) + 1;
    }
}
