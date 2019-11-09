// 1038. Binary Search Tree to Greater Sum Tree
// DescriptionHintsSubmissionsDiscussSolution
//
// Given the root of a binary search tree with distinct values, modify it so that every node has a new value equal to the sum of the values of the original tree that are greater than or equal to node.val.
//
// As a reminder, a binary search tree is a tree that satisfies these constraints:
//
//     The left subtree of a node contains only nodes with keys less than the node's key.
//     The right subtree of a node contains only nodes with keys greater than the node's key.
//     Both the left and right subtrees must also be binary search trees.
//
//
//
// Example 1:
//
// Input: [4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
// Output: [30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
//
//
//
// Note:
//
//     The number of nodes in the tree is between 1 and 100.
//     Each node will have value between 0 and 100.
//     The given tree is a binary search tree.
//
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
    public TreeNode bstToGst(TreeNode root) {
        if (root == null) {
            return root;
        }

        // return method1(root);

        // return method2(root);

        return method3(root);
    }

    private TreeNode method3(TreeNode root) {
        // iteration， 倒过来的 inorder
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        int sum = 0;
        while (curr != null || !stack.isEmpty()) {
            // 先一直向右走
            while (curr != null) {
                stack.push(curr);
                curr = curr.right;
            }
            curr = stack.pop();
            sum += curr.val; // 更新 sum
            curr.val = sum; // 更新当前 root
            curr = curr.left; // 再往左走
        }
        return root;
    }

    private TreeNode method2(TreeNode root) {
        recursion2(root, new int[1]);
        return root;
    }
    private void recursion2(TreeNode root, int[] sum) {
        // 或是把一直累加的 sum 往下带
        if (root == null) {
            return;
        }
        // 把 inorder 倒过来
        recursion2(root.right, sum); // 先走右边
        sum[0] += root.val; // 加入 sum
        root.val = sum[0]; // 更新 root
        recursion2(root.left, sum); // 走左边
    }

    private TreeNode method1(TreeNode root) {
        prev = 0;
        recursion(root);
        return root;
    }
    private int prev; // 不断记录
    private void recursion(TreeNode root) {
        // 先走右边
        if (root.right != null) {
            recursion(root.right);
        }
        prev += root.val; // 从右边起不断累加 root.val
        root.val = prev; // 更新 root
        // 走左边， 此时 prev 已经累加带下去了
        if (root.left != null) {
            recursion(root.left);
        }
    }
}
