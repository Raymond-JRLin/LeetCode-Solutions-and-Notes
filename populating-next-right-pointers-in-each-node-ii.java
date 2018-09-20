// 117. Populating Next Right Pointers in Each Node II
// DescriptionHintsSubmissionsDiscussSolution
// Given a binary tree
//
// struct TreeLinkNode {
//   TreeLinkNode *left;
//   TreeLinkNode *right;
//   TreeLinkNode *next;
// }
// Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
//
// Initially, all next pointers are set to NULL.
//
// Note:
//
// You may only use constant extra space.
// Recursive approach is fine, implicit stack space does not count as extra space for this problem.
// Example:
//
// Given the following binary tree,
//
//      1
//    /  \
//   2    3
//  / \    \
// 4   5    7
// After calling your function, the tree should look like:
//
//      1 -> NULL
//    /  \
//   2 -> 3 -> NULL
//  / \    \
// 4-> 5 -> 7 -> NULL



/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void connect(TreeLinkNode root) {
        if (root == null) {
            return;
        }

        // mytry(root);

        method2(root);
    }

    private void method2(TreeLinkNode root) {
        // O(1) space iteration
        // so we cannot use queue to store whole level nodes, then we should use some pointer to catch those we need
        // ref: https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/discuss/37828/O(1)-space-O(n)-complexity-Iterative-Solution
        TreeLinkNode head = root; // head (leftmost) of next level
        TreeLinkNode prev = null; // prev node of next level
        TreeLinkNode curr = null; // curr node of current level
        while (head != null) {
            curr = head; // move to current node from last level
            prev = null; // reset prev pointer
            head = null; // reset head pointer
            // deal with current level
            while (curr != null) {
                // left child
                if (curr.left != null) {
                    if (prev != null) {
                        prev.next = curr.left;
                    } else {
                        // if prev is null, then we should make left child as next level head
                        head = curr.left;
                    }
                    prev = curr.left;
                }
                // right child
                if (curr.right != null) {
                    if (prev != null) {
                        prev.next = curr.right;
                    } else {
                        head = curr.right;
                    }
                    prev = curr.right;
                }
                // move to next node in this level
                curr = curr.next;
            }
        }

    }

    private void mytry(TreeLinkNode root) {
        // O(N) space
        Queue<TreeLinkNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            TreeLinkNode prev = queue.poll();
            if (prev.left != null) {
                queue.offer(prev.left);
            }
            if (prev.right != null) {
                queue.offer(prev.right);
            }
            for (int i = 1; i < size; i++) {
                TreeLinkNode curr = queue.poll();
                prev.next = curr;
                if (curr.left != null) {
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                }
                prev = curr;
            }
        }
    }
}
