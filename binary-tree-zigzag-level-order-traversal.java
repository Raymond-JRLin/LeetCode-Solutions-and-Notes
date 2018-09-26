// 103. Binary Tree Zigzag Level Order Traversal
// DescriptionHintsSubmissionsDiscussSolution
// Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).
//
// For example:
// Given binary tree [3,9,20,null,null,15,7],
//     3
//    / \
//   9  20
//     /  \
//    15   7
// return its zigzag level order traversal as:
// [
//   [3],
//   [20,9],
//   [15,7]
// ]


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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }

        // return mytry(root);

        // return method2(root);

        return method3(root);
    }

    private List<List<Integer>> method3(TreeNode root) {
        // recursion
        // ref: https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/discuss/33815/My-accepted-JAVA-solution
        List<List<Integer>> result = new ArrayList<>();
        recursion(root, result, 0);
        return result;
    }
    private void recursion(TreeNode root, List<List<Integer>> result, int level) {
        if (root == null) {
            return;
        }
        if (result.size() <= level) {
            // 一开始就是 0， 而树 level 为 0 的时候就已经有 node 了
            List<Integer> nextLayer = new LinkedList<>();
            result.add(nextLayer);
        }
        List<Integer> layer = result.get(level);
        if (level % 2 == 0) {
            // normal order: left to right
            layer.add(root.val);
        } else {
            // right to left
            layer.add(0, root.val);
        }
        recursion(root.left, result, level + 1);
        recursion(root.right, result, level + 1);
    }

    private List<List<Integer>> method2(TreeNode root) {
        // don't use another list and still use queue to do BFS
        List<List<Integer>> result = new ArrayList<>();
        boolean zigzag = false;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> list = new LinkedList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                if (zigzag) {
                    // reverse add into list
                    list.add(0, curr.val);
                } else {
                    list.add(curr.val);
                }
                if (curr.left != null) {
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                }
            }
            result.add(list);
            zigzag = !zigzag;
        }
        return result;
    }

    private List<List<Integer>> mytry(TreeNode root) {
        // use stack and a flag to simulate zigzag process
        List<List<Integer>> result = new ArrayList<>();
        boolean left = true;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            int size = stack.size();
            List<TreeNode> nodes = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                nodes.add(stack.pop());
            }
            List<Integer> list = new ArrayList<>();
            for (TreeNode curr : nodes) {
                list.add(curr.val);
                if (left) {
                    if (curr.left != null) {
                        stack.push(curr.left);
                    }
                    if (curr.right != null) {
                        stack.push(curr.right);
                    }
                } else {
                    if (curr.right != null) {
                        stack.push(curr.right);
                    }
                    if (curr.left != null) {
                        stack.push(curr.left);
                    }
                }
            }
            left = !left;
            result.add(list);
        }
        return result;
    }
}
