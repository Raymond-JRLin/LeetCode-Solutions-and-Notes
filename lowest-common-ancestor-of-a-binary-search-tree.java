// 235. Lowest Common Ancestor of a Binary Search Tree
// DescriptionHintsSubmissionsDiscussSolution
// Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
//
// According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
//
// Given binary search tree:  root = [6,2,8,0,4,7,9,null,null,3,5]
//
//         _______6______
//        /              \
//     ___2__          ___8__
//    /      \        /      \
//    0      _4       7       9
//          /  \
//          3   5
// Example 1:
//
// Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
// Output: 6
// Explanation: The LCA of nodes 2 and 8 is 6.
// Example 2:
//
// Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
// Output: 2
// Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself
//              according to the LCA definition.
// Note:
//
// All of the nodes' values will be unique.
// p and q are different and both values will exist in the BST.


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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        // return mytry(root, p, q);

        return method2(root, p, q);
    }

    private TreeNode method2(TreeNode root, TreeNode p, TreeNode q) {
        // it's a BST, so p q 在同一边， 那么当前 root 有可能是， 如果在两边， 当前 root 就是 LCA
        if (root == null || root == p || root == q) {
            return root;
        }
        if (root.val > p.val && root.val > q.val) {
            // both p and q are in left subtree
            return method2(root.left, p, q);
        } else if (root.val < p.val && root.val < q.val) {
            // both p and q are in right subtree
            return method2(root.right, p, q);
        } else {
            return root;
        }
    }

    private TreeNode mytry(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = mytry(root.left, p, q);
        TreeNode right = mytry(root.right, p, q);
        if (left != null && right != null) {
            return root;
        } else if (left != null) {
            return left;
        } else {
            return right;
        }
    }
}
