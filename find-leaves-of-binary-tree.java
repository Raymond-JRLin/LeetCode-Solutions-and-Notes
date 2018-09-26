// 366. Find Leaves of Binary Tree
// DescriptionHintsSubmissionsDiscussSolution
// Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.
//
//
//
// Example:
//
// Input: [1,2,3,4,5]
//
//           1
//          / \
//         2   3
//        / \
//       4   5
//
// Output: [[4,5,3],[2],[1]]
//
//
// Explanation:
//
// 1. Removing the leaves [4,5,3] would result in this tree:
//
//           1
//          /
//         2
//
//
// 2. Now removing the leaf [2] would result in this tree:
//
//           1
//
//
// 3. Now removing the leaf [1] would result in the empty tree:
//
//           []



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
    public List<List<Integer>> findLeaves(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }

        return method1(root);
    }

    private List<List<Integer>> method1(TreeNode root) {
        // 因为要自底向上拿到 leaf， 所以肯定不能用 BFS 了， 那么只能是 DFS 做 recursion， 那么如何有的 leaf 比较“高”， 怎么样放到相同的 list 中？ 这里要考虑一个概念： height － “The height of a node is the number of edges from the node to the deepest leaf”。 所以从下往上数的话， 相同 height 的 leaf 都会被放进同一个 list 中， 靠 get() 拿到对应的 list
        List<List<Integer>> result = new ArrayList<>();
        dfs(result, root);
        return result;
    }
    private int dfs(List<List<Integer>> result, TreeNode root) {
        if (root == null) {
            // next level of leaf, since we set height of leaf as 0
            return -1;
        }
        int left = dfs(result, root.left);
        int right = dfs(result, root.right);
        int height = Math.max(left, right) + 1; // according to definition: node to the deepest leaf
        if (result.size() < height + 1) {
            // attention the relation of size and index
            result.add(new ArrayList<>());
        }
        result.get(height).add(root.val);
        root.left = null; // we can choose to do real "remove" or not, if we remove, then the original top root will be the only existed one
        root.right = null;
        return height;
    }
}
