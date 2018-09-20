// 111. Minimum Depth of Binary Tree
// DescriptionHintsSubmissionsDiscussSolution
// Given a binary tree, find its minimum depth.
//
// The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
//
// Note: A leaf is a node with no children.
//
// Example:
//
// Given binary tree [3,9,20,null,null,15,7],
//
//     3
//    / \
//   9  20
//     /  \
//    15   7
// return its minimum depth = 2.


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
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // return mytry(root);

        // return method2(root);

        return method3(root);
    }

    private int method3(TreeNode root) {
        // another similar recursion
        // 我觉得这里返回 0 更好理解， method2 当中是因为直接比较 min， 所以返回一个 MAX 来跳过， 这里在下面做了一个 split
        if (root == null) {
            return 0;
        }
        // 递归找到左右子树的 min depth
        int left = method3(root.left);
        int right = method3(root.right);
        // 注意 min depth 的定义是要从 root 走到 leaf 才可以， 只有 root 是不能算的, 所以其中一边为 0 的话， 只用另一边加 1， 不然就是他们的 min + 1
        return (left == 0 || right == 0 ? left + right + 1 : Math.min(left, right) + 1);
    }

    private int method2(TreeNode root) {
        // recursion
        return recursion(root);

    }
    private int recursion(TreeNode root) {
        if (root == null) {
            return Integer.MAX_VALUE; // so we would get this value when we get min
        }
        if (root.left == null && root.right == null) {
            return 1; // including this level
        }
        int left = recursion(root.left);
        int right = recursion(root.right);
        return Math.min(left, right) + 1; // always plus 1
    }

    private int mytry(TreeNode root) {
        // BFS level by level
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int result = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            result++;
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                // Attention: as problem description, the path should reach a leaf node
                if (curr.left == null && curr.right == null) {
                    return result;
                }
                if (curr.left != null) {
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                }
            }

        }
        return result;
    }
}
