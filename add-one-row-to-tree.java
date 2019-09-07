// 623. Add One Row to Tree
// DescriptionHintsSubmissionsDiscussSolution
// Given the root of a binary tree, then value v and depth d, you need to add a row of nodes with value v at the given depth d. The root node is at depth 1.
//
// The adding rule is: given a positive integer depth d, for each NOT null tree nodes N in depth d-1, create two tree nodes with value v as N's left subtree root and right subtree root. And N's original left subtree should be the left subtree of the new left subtree root, its original right subtree should be the right subtree of the new right subtree root. If depth d is 1 that means there is no depth d-1 at all, then create a tree node with value v as the new root of the whole original tree, and the original tree is the new root's left subtree.
//
// Example 1:
// Input:
// A binary tree as following:
//        4
//      /   \
//     2     6
//    / \   /
//   3   1 5
//
// v = 1
//
// d = 2
//
// Output:
//        4
//       / \
//      1   1
//     /     \
//    2       6
//   / \     /
//  3   1   5
//
// Example 2:
// Input:
// A binary tree as following:
//       4
//      /
//     2
//    / \
//   3   1
//
// v = 1
//
// d = 3
//
// Output:
//       4
//      /
//     2
//    / \
//   1   1
//  /     \
// 3       1
// Note:
// The given d is in range [1, maximum depth of the given tree + 1].
// The given binary tree has at least one tree node.


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
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if (d == 1) {
            TreeNode newNode = new TreeNode(v);
            newNode.left = root;
            return newNode;
        }

        // return my_try(root, v, d);
        // there is a another way directly using this main method, use 0/1 to clear left or right

        // 因为是一整层都要建立新的 node， 所以我一开始也想着 BFS 应该也能做，看了下答案要注意的是， queue 中最后留着的是要加入那一层的上一层
        return method2_BFS(root, v, d);

    }
    private TreeNode my_try(TreeNode root, int v, int d) {
        // use DFS/recursion to solve tree problem
        recursion(root, v, d, 1);
        return root;
    }
    private void recursion(TreeNode root, int val, int depth, int curr) {
        if (root == null) {
            return;
        }
        if (curr == depth - 1) {
            TreeNode left = root.left;
            TreeNode right = root.right;
            // TreeNode newNode = new TreeNode(val);
            // root.left = newNode;
            // root.right = newNode;
            // 不可以这么建立出新的 node 然后连上 root， 这样它们的对象地址是一样的， 会使得 2 个 newNode 的左右都连上原左右子树
            // 下面写 .left.left & .right.right 而不是 newNode.left newNode.right 也是因为这个
            root.left = new TreeNode(val);
            root.right = new TreeNode(val);
            root.left.left = left;
            root.right.right = right;
            return;
        }
        recursion(root.left, val, depth, curr + 1);
        recursion(root.right, val, depth, curr + 1);
    }

    private TreeNode method2_BFS(TreeNode root, int v, int d) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        for (int i = 0; i < d - 2; i++) {
            // d - 1 是上一层， root 又从 1 开始算， 再 - 1
            int size = queue.size(); // 注意这里是分层遍历了
            for (int j = 0; j < size; j++) {
                TreeNode curr = queue.poll();
                if (curr.left != null) {
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                }
            }
        }
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            TreeNode left = curr.left;
            TreeNode right = curr.right;
            curr.left = new TreeNode(v);
            curr.right = new TreeNode(v);
            curr.left.left = left;
            curr.right.right = right;
        }
        return root;
    }
}
