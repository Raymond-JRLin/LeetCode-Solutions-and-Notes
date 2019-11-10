// 662. Maximum Width of Binary Tree
// DescriptionHintsSubmissionsDiscussSolution
//
// Given a binary tree, write a function to get the maximum width of the given tree. The width of a tree is the maximum width among all levels. The binary tree has the same structure as a full binary tree, but some nodes are null.
//
// The width of one level is defined as the length between the end-nodes (the leftmost and right most non-null nodes in the level, where the null nodes between the end-nodes are also counted into the length calculation.
//
// Example 1:
//
// Input:
//
//            1
//          /   \
//         3     2
//        / \     \
//       5   3     9
//
// Output: 4
// Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).
//
// Example 2:
//
// Input:
//
//           1
//          /
//         3
//        / \
//       5   3
//
// Output: 2
// Explanation: The maximum width existing in the third level with the length 2 (5,3).
//
// Example 3:
//
// Input:
//
//           1
//          / \
//         3   2
//        /
//       5
//
// Output: 2
// Explanation: The maximum width existing in the second level with the length 2 (3,2).
//
// Example 4:
//
// Input:
//
//           1
//          / \
//         3   2
//        /     \
//       5       9
//      /         \
//     6           7
// Output: 8
// Explanation:The maximum width existing in the fourth level with the length 8 (6,null,null,null,null,null,null,7).
//
//
// Note: Answer will in the range of 32-bit signed integer.
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
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return method1(root);
    }

    //         1
    //    2         3
    //  4   5     6   7
    // 8 9 N 11  N 13 N 15

    // 对于 binary tree， parent node 和 children node 之间的 index 关系是
    // Index(left child) = Index(parent) * 2, Index(right child) = Index(parent) * 2 + 1
    // 这样用把同一层的 rightmost 的 index 当作 end index， leftmost index 当作 start， 可以算出这一层的 width = end + 1 - start

    private int method2(TreeNode root) {
        // DFS
        return dfs(root, 1, 0, new ArrayList<>());
    }
    private int dfs(TreeNode root, int index, int level, List<Integer> leftMosts) {
        if (root == null) {
            return 0;
        }
        // leftMosts 记录的是每一层 leftmost node 的 index
        if (level >= leftMosts.size()) {
            leftMosts.add(index);
        }
        int left = dfs(root.left, index * 2, level + 1, leftMosts);
        int right = dfs(root.right, index * 2 + 1, level + 1, leftMosts);
        int currWidth = index + 1 - leftMosts.get(level);
        return Math.max(currWidth, Math.max(left, right));
    }

    private int method1(TreeNode root) {
        // BFS
        Queue<TreeNode> queue = new LinkedList<>();
        Map<TreeNode, Integer> map = new HashMap<>(); // <node, index>
        queue.offer(root);
        map.put(root, 1);
        int result = 0;
        while (!queue.isEmpty()) {
            int start = 0, end = 0;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                int index = map.get(curr);
                // BFS 层级遍历， 总是先左后右， 所以顺序会是从左到右， 这样可以拿到这一层的最左边和最右边
                if (i == 0) {
                    start = index;
                }
                if (i == size - 1) {
                    end = index;
                }
                if (curr.left != null) {
                    queue.offer(curr.left);
                    map.put(curr.left, index * 2);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                    map.put(curr.right, index * 2 + 1);
                }
            }
            result = Math.max(result, end + 1 - start);
        }
        return result;
    }
}
