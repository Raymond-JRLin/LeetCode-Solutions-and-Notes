// 272. Closest Binary Search Tree Value II
// DescriptionHintsSubmissionsDiscussSolution
// Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.
//
// Note:
//
// Given target value is a floating point.
// You may assume k is always valid, that is: k ≤ total nodes.
// You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
// Example:
//
// Input: root = [4,2,5,1,3], target = 3.714286, and k = 2
//
//     4
//    / \
//   2   5
//  / \
// 1   3
//
// Output: [4,3]
// Follow up:
// Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?


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
    public List<Integer> closestKValues(TreeNode root, double target, int k) {

        // wrong: because target is double type, so Comparator may not get the diff accurately
        // return mytry(root, target, k);

        // 树的时候经常要考虑 inorder
        // return method1(root, target, k);

        // improved method1
        // return method2(root, target, k);

        return method3(root, target, k);
    }

    private List<Integer> method3(TreeNode root, double target, int k) {
        // O(N + k) time
        // 1. track predecessors and successors
        Stack<Integer> pre = new Stack<>();
        Stack<Integer> suc = new Stack<>();
        recursion(root, target, pre, true);
        recursion(root, target, suc, false);
        // 2. like merge sort, we compare and pick the closest one to the target and put it to the result list.
        List<Integer> result = new ArrayList<>();
        while (k > 0) {
            if (pre.isEmpty()) {
                result.add(suc.pop());
            } else if (suc.isEmpty()) {
                result.add(pre.pop());
            } else if (Math.abs(pre.peek() - target) < Math.abs(suc.peek() - target)) {
                result.add(pre.pop());
            } else {
                result.add(suc.pop());
            }
            k--;
        }
        return result;
    }
    private void recursion(TreeNode root, double target, Stack<Integer> stack, boolean isReverse) {
        // isReverse to control predeccessor or successor
        if (root == null) {
            return;
        }
        recursion(isReverse ? root.right : root.left, target, stack, isReverse);
        if (isReverse && root.val >= target || !isReverse && root.val < target) {
            stack.push(root.val);
        }
        recursion(isReverse ? root.left : root.right, target, stack, isReverse);
    }

    private List<Integer> method2(TreeNode root, double target, int k) {
        // improve method1: we can do in-order traversal as pick k closest values
        List<Integer> result = new ArrayList<>();
        inorder(root, target, k, result);
        return result;
    }
    private void inorder(TreeNode root, double target, int k, List<Integer> result) {
        if (root == null) {
            return;
        }
        // 开始 inorder traversal 并同时更新差距小的值
        inorder(root.left, target, k, result);
        if (result.size() < k) {
            result.add(root.val);
        } else {
            // compare the diff between target and 1st number and target and current root
            if (Math.abs(result.get(0) - target) > Math.abs(root.val - target)) {
                result.remove(0);
                result.add(root.val);
            } else {
                // return directly since we do as inorder, if diff of target and current root is already larget than diff of target and 1st node, then we don't need to go further because following nodes would get larger
                return;
            }
        }
        inorder(root.right, target, k, result);
    }

    private List<Integer> method1(TreeNode root, double target, int k) {
        // get the inorder of all nodes, and then BS to find the closest k
        List<Integer> nodes = getNodes(root);
        int start = 0;
        int end = nodes.size() - k;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (Math.abs(nodes.get(mid) - target) > Math.abs(nodes.get(mid + k) - target)) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            result.add(nodes.get(start++));
        }
        return result;
    }
    private List<Integer> getNodes(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        result.addAll(getNodes(root.left));
        result.add(root.val);
        result.addAll(getNodes(root.right));
        return result;
    }

    // private List<Integer> mytry(TreeNode root, double target, int k) {
    //     PriorityQueue<TreeNode> pq = new PriorityQueue<TreeNode>(new Comparator<TreeNode>() {
    //         @Override
    //         public int compare(TreeNode o1, TreeNode o2) {
    //             return (int) (Math.abs(o1.val - target) - Math.abs(o2.val - target));
    //         }
    //     });
    //     traverse(root, pq);
    //     List<Integer> result = new ArrayList<>();
    //     for (int i = 0; i < k; i++) {
    //         result.add(pq.poll().val);
    //     }
    //     return result;
    // }
    // private void traverse(TreeNode root, PriorityQueue<TreeNode> pq) {
    //     if (root == null) {
    //         return;
    //     }
    //     pq.offer(root);
    //     if (root.left != null) {
    //         traverse(root.left, pq);
    //     }
    //     if (root.right != null) {
    //         traverse(root.right, pq);
    //     }
    // }
}
