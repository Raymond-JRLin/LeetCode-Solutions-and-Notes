// 94. Binary Tree Inorder Traversal
// DescriptionHintsSubmissionsDiscussSolution
// Given a binary tree, return the inorder traversal of its nodes' values.
//
// Example:
//
// Input: [1,null,2,3]
//    1
//     \
//      2
//     /
//    3
//
// Output: [1,3,2]
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
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<Integer>();
        }

        List<Integer> result = new ArrayList<>();
        // method1_recursion(root, result);
        // return result;

        // return method2_iteration(root);

        return method4(root);
    }

    private void method1_recursion(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        // divide & conquer
        method1_recursion(root.left, result);
        result.add(root.val);
        method1_recursion(root.right, result);
    }

    private List<Integer> method2_iteration(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            result.add(curr.val);
            curr = curr.right;
        }
        return result;
    }

    private List<Integer> method3(TreeNode root) {
        // BitTiger Qinyuan
        List<Integer> result = new ArrayList<>();
        Deque<Guide> dq = new ArrayDeque<>();
        dq.addFirst(new Guide(0, root));
        while (!dq.isEmpty()) {
            Guide curr = dq.removeFirst();
            if (curr.node == null) {
                continue;
            }
            if (curr.ope == 1) {
                // print
                result.add(curr.node.val);
            } else {
                // visit
                dq.addFirst(new Guide(0, curr.node.right));
                dq.addFirst(new Guide(1, curr.node));
                dq.addFirst(new Guide(0, curr.node.left));
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

    private List<Integer> method4(TreeNode root) {
        // similar to method3 by Stack
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
                stack.push(new Guide(0, curr.node.right));
                stack.push(new Guide(1, curr.node));
                stack.push(new Guide(0, curr.node.left));
            }
        }
        return result;
    }

}
