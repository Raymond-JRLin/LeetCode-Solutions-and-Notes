// 222. Count Complete Tree Nodes
// DescriptionHintsSubmissionsDiscussSolution
//
// Given a complete binary tree, count the number of nodes.
//
// Note:
//
// Definition of a complete binary tree from Wikipedia:
// In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
//
// Example:
//
// Input:
//     1
//    / \
//   2   3
//  / \  /
// 4  5 6
//
// Output: 6
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
    public int countNodes(TreeNode root) {

        // return mytry(root);

        return method2(root);
    }

    private int method2(TreeNode root) {
        // 这里计算高度的方法比较特殊， 总是往左走， 因为往右走并不知道最后一个 node 在哪边
        int h = getHeight(root); // 包括 root 在内的高度
        int result = 0;
        while (root != null) {
            // 由于树的特殊性， 只会有两种情况， 最后一个 node 在左子树或是右子树
            int rightHeight = getHeight(root.right); // 算右子树的高度
            if (rightHeight + 1 == h) {
                // 如果正好差 1 （即 root）， 那么说明至少左子树是 complete， 不知道右子树最后一层是否是 complete 所以继续往下查右子树
                result += 1 << h;
                root = root.right;
            } else {
                // 高度差超过 1， 就是为 2 （即 root 和最后一层）， 那么左子树就已经有空缺了， 往下查左子树
                result += 1 << (h - 1);
                root = root.left;
            }
            h--;
        }
        return result;
    }
    private int getHeight(TreeNode root) {
        // 当为 null 的时候， 设为 -1， 这样只有一个 node 的时候正好是 2 ^ 0 == 1
        if (root == null) {
            return -1;
        }
        // 而且要往左边走， 因为总是先填左边， 所以以左边的为准
        return 1 + getHeight(root.left);
    }

    private int mytry(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + mytry(root.left) + mytry(root.right);
    }
}
