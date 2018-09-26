// 108. Convert Sorted Array to Binary Search Tree
// DescriptionHintsSubmissionsDiscussSolution
// Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
//
// For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
//
// Example:
//
// Given the sorted array: [-10,-3,0,5,9],
//
// One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
//
//       0
//      / \
//    -3   9
//    /   /
//  -10  5


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
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        // divide & conquer
        // return recursion(nums, 0, nums.length - 1);

        // try iteration according to discussion
        return iteration(nums);
    }
    private TreeNode recursion(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = start + (end - start) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = recursion(nums, start, mid - 1);
        root.right = recursion(nums, mid + 1, end);
        return root;
    }
    private TreeNode iteration(int[] nums) {
        // if we wanna do iteration, we can use BFS, and use our own class to record the left and right index
        int start = 0;
        int end = nums.length - 1;
        int mid = start + (end - start) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(root, start, end));
        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            start = curr.left;
            end = curr.right;
            mid = start + (end - start) / 2;
            if (start != mid) {
                TreeNode left_child = new TreeNode(nums[start + (mid - 1 - start) / 2]);
                curr.node.left = left_child;
                queue.offer(new Node(left_child, start, mid - 1));
            }
            if (end != mid) {
                TreeNode right_child = new TreeNode(nums[mid + 1 + (end - mid - 1) / 2]);
                curr.node.right = right_child;
                queue.offer(new Node(right_child, mid + 1, end));
            }
        }
        return root;
    }
}
class Node {
    public TreeNode node;
    public int left;
    public int right;
    public Node(TreeNode node, int l, int r) {
        this.node = node;
        left = l;
        right = r;
    }
}
