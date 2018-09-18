// 144. Binary Tree Preorder Traversal
// DescriptionHintsSubmissionsDiscussSolution
// Given a binary tree, return the preorder traversal of its nodes' values.
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
// Output: [1,2,3]
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
    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<Integer>();
        }

        // return method1_recursion(root);

        // return method2_iteration(root);

        return method3(root);
    }
    private List<Integer> method1_recursion(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        recursion(result, root);
        return result;
    }
    private void recursion(List<Integer> result, TreeNode root) {
        if (root == null) {
            return;
        }
        result.add(root.val);
        recursion(result, root.left);
        recursion(result, root.right);
    }

    private List<Integer> method2_iteration(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            result.add(curr.val);
            if (curr.right != null) {
                stack.push(curr.right);
            }
            if (curr.left != null) {
                stack.push(curr.left);
            }
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
                dq.addFirst(new Guide(0, curr.node.left));
                dq.addFirst(new Guide(1, curr.node));
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
