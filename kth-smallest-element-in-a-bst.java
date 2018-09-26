// 230. Kth Smallest Element in a BST
// DescriptionHintsSubmissionsDiscussSolution
// Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
//
// Note:
// You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
//
// Example 1:
//
// Input: root = [3,1,4,null,2], k = 1
//    3
//   / \
//  1   4
//   \
//    2
// Output: 1
// Example 2:
//
// Input: root = [5,3,6,2,4,null,null,1], k = 3
//        5
//       / \
//      3   6
//     / \
//    2   4
//   /
//  1
// Output: 3
// Follow up:
// What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?


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
    public int kthSmallest(TreeNode root, int k) {
        // because it is a BST, we can do in-order traversal to get the values as ascending order
        // return method1_inorder(root, k);

        // we can also do binary search to count the # nodes
        // return method2_bs(root, k);

        return method3(root, k);
    }

    private int method3(TreeNode root, int k) {
        // inorder iteration
        Stack<TreeNode> stack = new Stack<>();
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            k--;
            if (k == 0) {
                return curr.val;
            }
            TreeNode right = curr.right;
            while (right != null) {
                stack.push(right);
                right = right.left;
            }
        }
        return -1;
    }

    private int method1_inorder(TreeNode root, int k) {
        inorder(root, k);
        return result;
    }
    int count = 0;
    int result = 0;
    private void inorder(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        inorder(root.left, k);
        count++; // we can use k directly by doing k--, and return when k == 0
        if (count == k) {
            result = root.val;
            return;
        }
        inorder(root.right, k);
    }

    private int method2_bs(TreeNode root, int k) {
        int count_left = countNodes(root.left); // count left substree
        if (count_left >= k) {
            // # left substree's nodes is larger then k, which means k-th nodes is in the left subtree
            return method2_bs(root.left, k);
        } else if (count_left + 1 < k) {
            // k-th is in the right subtree
            return method2_bs(root.right, k - 1 - count_left); // - 1 - count_left: minus # left subtree and current root
        }
        return root.val; // count_left + 1  == k 那么当前 root 是第 k 个
    }
    private int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return countNodes(root.left) + countNodes(root.right) + 1; // plus current root
    }
}
