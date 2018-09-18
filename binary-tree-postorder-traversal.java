// Store
// New Playground
// RaymondLin
//
// 145. Binary Tree Postorder Traversal
// DescriptionHintsSubmissionsDiscussSolution
// Given a binary tree, return the postorder traversal of its nodes' values.
//
// Example:
//
// Input: [1,null,2,3]
//   1
//    \
//     2
//    /
//   3
//
// Output: [3,2,1]
// Follow up: Recursive solution is trivial, could you do it iteratively?


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
    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<Integer>();
        }

        // return mytry(root);

        return method3(root);
    }

    private List<Integer> mytry(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        result.addAll(mytry(root.left));
        result.addAll(mytry(root.right));
        result.add(root.val);
        return result;
    }

    private List<Integer> method3(TreeNode root) {
        // BitTiger Qinyuan, I modified with staack
        List<Integer> result = new ArrayList<>();
        Stack<Guide> stack = new Stack<>();
        stack.push(new Guide(0, root));
        while (!stack.isEmpty()) {
            Guide curr = stack.pop();
            if (curr.node == null) {
                continue;
            }
            if (curr.ope == 1) {
                // print
                result.add(curr.node.val);
            } else {
                // visit
                stack.push(new Guide(1, curr.node));
                stack.push(new Guide(0, curr.node.right));
                stack.push(new Guide(0, curr.node.left));
            }
        }
        return result;
    }
    private class Guide {
        int ope; // 0: visit; 1: print
        TreeNode node;
        public Guide(int ope, TreeNode node) {
            this.ope = ope;
            this.node = node;
        }
    }
}
