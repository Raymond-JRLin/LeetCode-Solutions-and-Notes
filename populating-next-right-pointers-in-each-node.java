// 116. Populating Next Right Pointers in Each Node
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
// You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
// Example:
//
// Given the following perfect binary tree,
//
//      1
//    /  \
//   2    3
//  / \  / \
// 4  5  6  7
// After calling your function, the tree should look like:
//
//      1 -> NULL
//    /  \
//   2 -> 3 -> NULL
//  / \  / \
// 4->5->6->7 -> NULL


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
        if (root == null || root.left == null) {
            return;
        }

        // mytry(root);

        // mytry2(root);

        method2(root);

        // method2_2(root.left, root.right);

        // method3(root);
    }

    private void method3(TreeLinkNode root) {
        // iteration: O(1) space
        TreeLinkNode prev = root;
        while (prev != null) {
            TreeLinkNode curr = prev;
            while (curr != null) {
                if (curr.left != null) {
                    curr.left.next = curr.right;
                    if (curr.next != null) {
                        curr.right.next = curr.next.left;
                    }
                }
                // can put following in above if since if curr has left then it must has right because of perfect BT
                // if (curr.right != null && curr.next != null) {
                //     curr.right.next = curr.next.left;
                // }
                curr = curr.next;
            }
            prev = prev.left; // go next level
        }
    }

    private void method2_2(TreeLinkNode left, TreeLinkNode right) {
        // another version of recursion
        left.next = right;
        if (left.left != null) {
            method2_2(left.left, left.right);
            method2_2(left.right, right.left);
            method2_2(right.left, right.right);
        }
    }

    private void method2(TreeLinkNode root) {
        // recursion, but it's not guarantee a "real" O(1) space,  because many recursive solutions ignore that recursion management needs space.
        // ref: https://leetcode.com/problems/populating-next-right-pointers-in-each-node/discuss/37473/My-recursive-solution(Java)
        if (root == null) {
            return;
        }
        if (root.left != null) {
            root.left.next = root.right;
            if (root.next != null) {
                root.right.next = root.next.left;
            }
        }
        method2(root.left);
        method2(root.right);
    }

    private void mytry2(TreeLinkNode root) {
        // it is a perfect binary tree
        Queue<TreeLinkNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            TreeLinkNode curr = queue.poll();
            if (curr.left != null) {
                queue.offer(curr.left);
                queue.offer(curr.right);
            }
            for (int i = 1; i < size; i++) {
                TreeLinkNode next = queue.poll();
                curr.next = next;
                if (next.left != null) {
                    queue.offer(next.left);
                    queue.offer(next.right);
                }
                curr = next;
            }
        }
    }

    private void mytry(TreeLinkNode root) {
        // O(N) time and space
        Queue<TreeLinkNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            TreeLinkNode curr = queue.poll();
            if (curr.left != null) {
                queue.offer(curr.left);
            }
            if (curr.right != null) {
                queue.offer(curr.right);
            }
            for (int i = 1; i < size; i++) {
                TreeLinkNode next = queue.poll();
                curr.next = next;
                if (next.left != null) {
                    queue.offer(next.left);
                }
                if (next.right != null) {
                    queue.offer(next.right);
                }
                curr = next;
            }
        }
    }
}
