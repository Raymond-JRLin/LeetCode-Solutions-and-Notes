// 450. Delete Node in a BST
// DescriptionHintsSubmissionsDiscussSolution
//
// Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.
//
// Basically, the deletion can be divided into two stages:
//
//     Search for a node to remove.
//     If the node is found, delete the node.
//
// Note: Time complexity should be O(height of tree).
//
// Example:
//
// root = [5,3,6,2,4,null,7]
// key = 3
//
//     5
//    / \
//   3   6
//  / \   \
// 2   4   7
//
// Given key to delete is 3. So we find the node with value 3 and delete it.
//
// One valid answer is [5,4,6,2,null,null,7], shown in the following BST.
//
//     5
//    / \
//   4   6
//  /     \
// 2       7
//
// Another valid answer is [5,2,6,null,4,null,7].
//
//     5
//    / \
//   2   6
//    \   \
//     4   7
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
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return root;
        }

        return method1(root, key);
    }

    // BST 删掉这个就看下面有没有 children， 有的话就要重新连一下， 我们可以简单的把 left 放到 right 的 leftmost 下面去

    private TreeNode method1(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val > key) {
            root.left = method1(root.left, key);
            return root;
        } else if (root.val < key) {
            root.right = method1(root.right, key);
            return root;
        } else {
            // root is the target node
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            } else {
                // left != null && right != null
                TreeNode right = root.right;
                while (right.left != null) {
                    right = right.left;
                }
                right.left = root.left;
                return root.right;
            }
        }
    }
}
