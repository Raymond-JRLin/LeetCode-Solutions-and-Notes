// 545. Boundary of Binary Tree
// DescriptionHintsSubmissionsDiscussSolution
//
// Given a binary tree, return the values of its boundary in anti-clockwise direction starting from root. Boundary includes left boundary, leaves, and right boundary in order without duplicate nodes.  (The values of the nodes may still be duplicates.)
//
// Left boundary is defined as the path from root to the left-most node. Right boundary is defined as the path from root to the right-most node. If the root doesn't have left subtree or right subtree, then the root itself is left boundary or right boundary. Note this definition only applies to the input binary tree, and not applies to any subtrees.
//
// The left-most node is defined as a leaf node you could reach when you always firstly travel to the left subtree if exists. If not, travel to the right subtree. Repeat until you reach a leaf node.
//
// The right-most node is also defined by the same way with left and right exchanged.
//
// Example 1
//
// Input:
//   1
//    \
//     2
//    / \
//   3   4
//
// Ouput:
// [1, 3, 4, 2]
//
// Explanation:
// The root doesn't have left subtree, so the root itself is left boundary.
// The leaves are node 3 and 4.
// The right boundary are node 1,2,4. Note the anti-clockwise direction means you should output reversed right boundary.
// So order them in anti-clockwise without duplicates and we have [1,3,4,2].
//
//
//
// Example 2
//
// Input:
//     ____1_____
//    /          \
//   2            3
//  / \          /
// 4   5        6
//    / \      / \
//   7   8    9  10
//
// Ouput:
// [1,2,4,7,8,9,10,6,3]
//
// Explanation:
// The left boundary are node 1,2,4. (4 is the left-most node according to definition)
// The leaves are node 4,7,8,9,10.
// The right boundary are node 1,3,6,10. (10 is the right-most node).
// So order them in anti-clockwise without duplicate nodes we have [1,2,4,7,8,9,10,6,3].
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
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }

        return method1(root);
    }

    private List<Integer> method1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        // add root
        result.add(root.val);
        // get left boundary
        getLeftmostPath(root.left, result);
        // get leaves， 不包括 root again
        getLeaves(root.left, result);
        getLeaves(root.right, result);
        // get right boundary
        // List<Integer> rights = new ArrayList<>(); // 在 recursion 中， 最后加 root 就可以不用单独一个 list 然后再 reverse
        getRightmostPath(root.right, result);
        // Collections.reverse(rights);
        // result.addAll(rights);
        return result;
    }
    private void getRightmostPath(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            // leaf
            return;
        }

        if (root.right == null) {
            getRightmostPath(root.left, list);
        } else {
            getRightmostPath(root.right, list);
        }
        list.add(root.val);
    }
    private void getLeaves(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            // leaf
            result.add(root.val);
            return;
        }
        getLeaves(root.left, result);
        getLeaves(root.right, result);
    }
    private void getLeftmostPath(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            // leaf
            return;
        }
        result.add(root.val);
        // 因为我们这个 recursion 的出口就有判断 null， 所以不需要提前判断 != null， 只有 left 为 null 才走右边， 不然就走左边
        if (root.left == null) {
            getLeftmostPath(root.right, result);
        } else {
            getLeftmostPath(root.left, result);
        }
        // if (root.left != null) {
        //     getLeftmostPath(root.left, result);
        // } else if (root.right != null) {
        //     getLeftmostPath(root.right, result);
        // }
    }
}
