// 314. Binary Tree Vertical Order Traversal

// Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).
//
// If two nodes are in the same row and column, the order should be from left to right.
//
// Examples 1:
//
// Input: [3,9,20,null,null,15,7]
//
//    3
//   /\
//  /  \
//  9  20
//     /\
//    /  \
//   15   7
//
// Output:
//
// [
//   [9],
//   [3,15],
//   [20],
//   [7]
// ]
// Examples 2:
//
// Input: [3,9,8,4,0,1,7]
//
//      3
//     /\
//    /  \
//    9   8
//   /\  /\
//  /  \/  \
//  4  01   7
//
// Output:
//
// [
//   [4],
//   [9],
//   [3,0,1],
//   [8],
//   [7]
// ]
// Examples 3:
//
// Input: [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5)
//
//      3
//     /\
//    /  \
//    9   8
//   /\  /\
//  /  \/  \
//  4  01   7
//     /\
//    /  \
//    5   2
//
// Output:
//
// [
//   [4],
//   [9,5],
//   [3,0,1],
//   [8,2],
//   [7]
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
    public List<List<Integer>> verticalOrder(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }

        return mytry(root);
    }

    private List<List<Integer>> mytry(TreeNode root) {
        // at the beginning, I tried to store Pairs in a pq, but finally found that we should use map to record along with BFS traversing, since in the same column, nodes in the upper level should be in front of nodes in the lower levels
        Map<Integer, List<Integer>> map = new HashMap<>(); // <col, list of node's val>
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root, 0));
        while (!queue.isEmpty()) {
            Pair curr = queue.poll();
            int col = curr.col;
            TreeNode node = curr.node;
            List<Integer> list = map.getOrDefault(col, new ArrayList<>());
            list.add(node.val);
            map.put(col, list);
            if (node.left != null) {
                queue.offer(new Pair(node.left, col - 1));
            }
            if (node.right != null) {
                queue.offer(new Pair(node.right, col + 1));
            }
        }

        List<Integer> cols = new ArrayList<>(map.keySet());
        // sort so we have cols from left to right, or we can record min and max of cols when do BFS
        Collections.sort(cols);
        List<List<Integer>> result = new ArrayList<>();
        for (int col : cols) {
            result.add(map.get(col));
        }
        return result;
    }
    private class Pair {
        private TreeNode node;
        private int col;
        public Pair(TreeNode n, int c) {
            node = n;
            col = c;
        }
    }
}
