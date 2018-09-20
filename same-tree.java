// 100. Same Tree
// DescriptionHintsSubmissionsDiscussSolution
// Given two binary trees, write a function to check if they are the same or not.
//
// Two binary trees are considered the same if they are structurally identical and the nodes have the same value.
//
// Example 1:
//
// Input:     1         1
//           / \       / \
//          2   3     2   3
//
//         [1,2,3],   [1,2,3]
//
// Output: true
// Example 2:
//
// Input:     1         1
//           /           \
//          2             2
//
//         [1,2],     [1,null,2]
//
// Output: false
// Example 3:
//
// Input:     1         1
//           / \       / \
//          2   1     1   2
//
//         [1,2,1],   [1,1,2]
//
// Output: false



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
    public boolean isSameTree(TreeNode p, TreeNode q) {

        // return mytry(p, q);

        return method2(p, q);
    }

    private boolean method2(TreeNode p, TreeNode q) {
        // iteration
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(p);
        queue.offer(q);
        while (!queue.isEmpty()) {
            TreeNode a = queue.poll();
            TreeNode b = queue.poll();
            if (a == null && b == null) {
                continue;
            }
            if (a == null || b == null) {
                return false;
            }
            if (a.val != b.val) {
                return false;
            }
            queue.offer(a.left);
            queue.offer(b.left);
            queue.offer(a.right);
            queue.offer(b.right);
        }
        return true;
    }

    private boolean mytry(TreeNode p, TreeNode q) {
        // recursion
        if (p == null && q == null) {
            // both are null
            return true;
        }
        if (p == null || q == null) {
            // either is null
            return false;
        }
        // both are not null
        if (p.val != q.val) {
            return false;
        }
        // check left and right subtrees
        return mytry(p.left, q.left) && mytry(p.right, q.right);
    }
}
