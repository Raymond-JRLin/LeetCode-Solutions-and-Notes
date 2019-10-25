// 987. Vertical Order Traversal of a Binary Tree
// DescriptionHintsSubmissionsDiscussSolution
//
// Given a binary tree, return the vertical order traversal of its nodes values.
//
// For each node at position (X, Y), its left and right children respectively will be at positions (X-1, Y-1) and (X+1, Y-1).
//
// Running a vertical line from X = -infinity to X = +infinity, whenever the vertical line touches some nodes, we report the values of the nodes in order from top to bottom (decreasing Y coordinates).
//
// If two nodes have the same position, then the value of the node that is reported first is the value that is smaller.
//
// Return an list of non-empty reports in order of X coordinate.  Every report will have a list of values of nodes.
//
//
//
// Example 1:
//
// Input: [3,9,20,null,null,15,7]
// Output: [[9],[3,15],[20],[7]]
// Explanation:
// Without loss of generality, we can assume the root node is at position (0, 0):
// Then, the node with value 9 occurs at position (-1, -1);
// The nodes with values 3 and 15 occur at positions (0, 0) and (0, -2);
// The node with value 20 occurs at position (1, -1);
// The node with value 7 occurs at position (2, -2).
//
// Example 2:
//
// Input: [1,2,3,4,5,6,7]
// Output: [[4],[2],[1,5,6],[3],[7]]
// Explanation:
// The node with value 5 and the node with value 6 have the same position according to the given scheme.
// However, in the report "[1,5,6]", the node value of 5 comes first since 5 is smaller than 6.
//
//
//
// Note:
//
//     The tree will have between 1 and 1000 nodes.
//     Each node's value will be between 0 and 1000.
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
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }

        return mytry(root);
    }

    private List<List<Integer>> mytry(TreeNode root) {
        TreeMap<Integer, PriorityQueue<Node>> map = new TreeMap<>();
        recursion(root, map, 0, 0);
        List<List<Integer>> result = new ArrayList<>();
        for (int key : map.keySet()) {
            List<Integer> list = new ArrayList<>();
            PriorityQueue<Node> queue = map.get(key);
            while (!queue.isEmpty()) {
                list.add(queue.poll().val);
            }
            result.add(list);
        }
        return result;
    }
    private void recursion(TreeNode root, TreeMap<Integer, PriorityQueue<Node>> map, int x, int level) {
        if (root == null) {
            return;
        }
        recursion(root.left, map, x - 1, level + 1);
        PriorityQueue<Node> queue = map.computeIfAbsent(x, dummy -> (new PriorityQueue<>()));
        queue.offer(new Node(level, root.val));
        recursion(root.right, map, x + 1, level + 1);
    }
    private class Node implements Comparable<Node> {
        int level;
        int val;
        Node(int level, int val) {
            this.level = level;
            this.val = val;
        }

        @Override
        public int compareTo(Node o2) {
            if (this.level == o2.level) {
                return Integer.compare(this.val, o2.val);
            } else {
                return Integer.compare(this.level, o2.level);
            }
        }
    }
}
